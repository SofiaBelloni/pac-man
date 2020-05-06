package view.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.google.common.collect.Iterables;

import controller.Controller;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.Directions;
import model.Ghosts;
import utils.Pair;
import utils.PairImpl;
import view.GameScene;
import view.View;
import view.utils.GameState;

public class GameViewController extends SceneController {

    private static final int LIFE_ICON_DIMENSION = 50;
    private static final int COUNTDOWN = 3;

    @FXML
    private HBox rootBox;
    @FXML
    private VBox labelBox;
    @FXML
    private StackPane gamePane;
    @FXML
    private Pane entityPane;
    @FXML
    private Label highScore;
    @FXML
    private Label score;
    @FXML
    private Label level;
    @FXML
    private Label countDown;
    @FXML
    private ProgressBar timer;
    @FXML
    private HBox livesContainer;
    @FXML
    private Label gameStateLabel;

    private int squareSize;
    private final Map<Integer, ImageView> ghostView = new HashMap<>();
    private final Map<Integer, Pair<Integer, Integer>> ghostPositions = new HashMap<>();
    private final Map<Integer, Integer> ghostLevels = new HashMap<>();
    private final Map<Integer, Ghosts> ghostTypes = new HashMap<>();
    private final Map<Pair<Integer, Integer>, ImageView> gameMap = new HashMap<>();
    private ImageView pacmanImage;
    private Pair<Integer, Integer> pacmanPosition;
    private final Image pill = new Image("textures/pill/pill.png");
    private int currentLevel;
    private AnimationTimer entitiesAnimationTimer;
    private final List<Image> pacmanImagesList = new ArrayList<>();
    private long startNanoTime = System.nanoTime();
    private Iterator<Image> pacmanImagesIterator;
    private final Timer countdownTimer = new Timer();
    private final GameState gameState = new GameState();

    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.currentLevel = 1;
        this.squareSize = (int) (this.rootBox.getHeight() / controller.getData().getyMapSize());
        int width = squareSize * controller.getData().getxMapSize();
        int height = squareSize * controller.getData().getyMapSize();
        this.gamePane.setMinSize(width, height);
        this.gamePane.setMaxSize(width, height);
        HBox.setHgrow(this.labelBox, Priority.SOMETIMES);
        GridPane mapGrid = new GridPane();
        this.gamePane.getChildren().add(mapGrid);
        for (Pair<Integer, Integer> e : this.getController().getData().getWallsPositions()) {
            ImageView image = new ImageView("textures/wall/wall.png");
            image.setFitWidth(squareSize);
            image.setFitHeight(squareSize);
            mapGrid.add(image, e.getX(), e.getY());
            this.gameMap.put(e, image);
        }

        for (Pair<Integer, Integer> e : this.getController().getData().getPillsPositions()) {
            ImageView image = new ImageView("textures/pill/pill.png");
            image.setFitWidth(squareSize);
            image.setFitHeight(squareSize);
            mapGrid.add(image, e.getX(), e.getY());
            this.gameMap.put(e, image);
        }
        entityPane = new Pane();
        this.gamePane.getChildren().add(entityPane);
        this.ghostSpawn();
        this.pacmanSpawn();

        // Inizialize HUD
        this.highScore.setText(String.valueOf(controller.getHighScore()));
        this.level.setText(String.valueOf(controller.getData().getLevel()));
        for (int i = 0; i < controller.getData().getLives(); i++) {
            this.livesContainer.getChildren().add(this.lifeIcon());
        }

        this.countDown.toFront();

        this.initializeAnimations();

    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        switch (event.getCode()) {
        case UP:
        case W:
            if (this.gameState.getState().equals(GameState.State.RUNNING)) {
                this.pacmanImage.setRotate(0);
                this.getController().newPacManDirection(Directions.UP);
            }
            break;
        case DOWN:
        case S:
            if (this.gameState.getState().equals(GameState.State.RUNNING)) {
                this.pacmanImage.setRotate(180);
                this.getController().newPacManDirection(Directions.DOWN);
            }
            break;
        case LEFT:
        case A:
            if (this.gameState.getState().equals(GameState.State.RUNNING)) {
                this.pacmanImage.setRotate(270);
                this.getController().newPacManDirection(Directions.LEFT);
            }
            break;
        case RIGHT:
        case D:
            if (this.gameState.getState().equals(GameState.State.RUNNING)) {
                this.pacmanImage.setRotate(90);
                this.getController().newPacManDirection(Directions.RIGHT);
            }
            break;
        case SPACE:
            this.changeGameState();
            break;
        case ESCAPE:
            this.endGame();
            break;
        default:
            break;
        }
    }

    @Override
    public final void render() {

        this.hudRender();
        this.ghostRender();
        this.pacmanRender();
        this.gameMapRender();
        if (this.getController().getData().isGameEnded()) {
            this.endGame();
        }
    }

    private void ghostSpawn() {
        for (int id : this.getController().getData().getGhostsPositions().keySet()) {
            if (!this.ghostView.containsKey(id)) {
                this.ghostLevels.put(id, this.currentLevel);
                this.ghostTypes.put(id, this.getController().getData().getGhostsTypes().get(id));
                this.ghostPositions.put(id, new PairImpl<>(this.getController()
                        .getData().getGhostsPositions().get(id).getX(),
                        this.getController()
                        .getData().getGhostsPositions().get(id).getY()));
                final ImageView ghostImage = new ImageView();
                ghostImage.setFitWidth(this.squareSize);
                ghostImage.setFitHeight(this.squareSize);
                ghostImage.setX(this.squareSize * this.ghostPositions.get(id).getX());
                ghostImage.setY(this.squareSize * this.ghostPositions.get(id).getY());
                this.ghostView.put(id, ghostImage);
                this.entityPane.getChildren().add(ghostImage);
                if (this.getController().getData().isGameInverted()) {
                    ghostImage.setImage(new Image("textures/ghost/eatable.png"));
                } else {
                    ghostImage.setImage(new Image("textures/" + this.ghostTypes.get(id).toString() + "/RIGHT.png"));
                }
            }
        }
    }

    private void ghostRender() {
        if (this.getController().getData().getLevel() > this.currentLevel) {
            this.currentLevel = this.getController().getData().getLevel();
            this.ghostSpawn();
        }
        for (int id : this.ghostPositions.keySet()) {
            if (!this.getController().getData().getGhostsPositions().containsKey(id)) {
                this.entityPane.getChildren().remove(this.ghostView.get(id));
                this.ghostView.remove(id);
            } else {
                Pair<Integer, Integer> newPosition = new PairImpl<>(
                        this.getController()
                        .getData().getGhostsPositions().get(id).getX(),
                        this.getController()
                        .getData().getGhostsPositions().get(id).getY());
                if (this.getController().getData().isGameInverted()) {
                    this.ghostView.get(id).setImage(new Image("textures/ghost/eatable.png"));
                } else {
                    if (this.ghostLevels.get(id) < this.currentLevel) {
                        this.ghostTypes.put(id, Ghosts.OLDLEVEL);
                    }
                    switch (this.getController().getData().getGhostsDirections().get(id)) {
                    case UP:
                        this.ghostView.get(id).setImage(new Image("textures/" + this.ghostTypes.get(id).toString() + "/UP.png"));
                        break;
                    case DOWN:
                        this.ghostView.get(id).setImage(new Image("textures/" + this.ghostTypes.get(id).toString() + "/DOWN.png"));
                        break;
                    case LEFT:
                        this.ghostView.get(id).setImage(new Image("textures/" + this.ghostTypes.get(id).toString() + "/LEFT.png"));
                        break;
                    case RIGHT:
                        this.ghostView.get(id).setImage(new Image("textures/" + this.ghostTypes.get(id).toString() + "/RIGHT.png"));
                        break;
                    default:
                        break;
                    }
                }
                if (!this.ghostPositions.get(id).equals(newPosition)) {
                    this.transition(this.ghostView.get(id), this.ghostPositions.get(id), newPosition);
                    this.ghostPositions.put(id, newPosition);
                }
            }
        }
    }

    /**
     * Method that create the imageView of PacMan and draw it.
     */
    private void pacmanSpawn() {
        this.pacmanPosition = new PairImpl<>(this.getController().getData().getPacManXPosition(),
                this.getController().getData().getPacManYPosition());
        this.pacmanImage = new ImageView();
        this.pacmanImage.setFitWidth(this.squareSize);
        this.pacmanImage.setFitHeight(this.squareSize);
        this.pacmanImage.setX(this.squareSize * this.pacmanPosition.getX());
        this.pacmanImage.setY(this.squareSize * this.pacmanPosition.getY());
        this.entityPane.getChildren().add(this.pacmanImage);
        this.pacmanImage.setImage(new Image("textures/pac_man/pac_man2.png"));
        this.pacmanImage.setRotate(270);
    }

    /**
     * Call this method to draw the current position of PacMan.
     */
    private void pacmanRender() {
        Pair<Integer, Integer> newPosition = new PairImpl<>(this.getController().getData().getPacManXPosition(),
                this.getController().getData().getPacManYPosition());
        if (!this.pacmanPosition.equals(newPosition)) {
            this.transition(this.pacmanImage, this.pacmanPosition, newPosition);
            this.pacmanPosition = newPosition;
        }
    }

    private void gameMapRender() {
        this.gameMap.keySet().forEach(x -> {
            if (this.getController().getData().getPillsPositions().contains(x)) {
                this.gameMap.get(x).setImage(this.pill);
            } else {
                if (!this.getController().getData().getWallsPositions().contains(x)) {
                    this.gameMap.get(x).setImage(null);
                }
            }
        });
    }
    /**
     * Method that update the HUD data value.
     */
    private void hudRender() {
        this.score.setText(String.valueOf(this.getController().getData().getCurrentScore()));
        this.timer.setProgress(this.getController().getData().getLevelTimePercentage()); 
        if (this.currentLevel != this.getController().getData().getLevel()) {
            this.level.setText(String.valueOf(this.getController().getData().getLevel()));
            this.pauseGame();
            this.countdownTimer.scheduleAtFixedRate(new TimerTask() {
                private int value = COUNTDOWN + 1;
                @Override
                public void run() {
                    if (value > 1) {
                        Platform.runLater(() -> countDown.setText(String.valueOf(this.value)));
                        this.value--;
                    } else {
                        Platform.runLater(() -> countDown.setText(""));
                        resumeGame();
                        this.cancel();
                    } 
                }
            }, 0, 1000);
        }
        if (this.livesContainer.getChildren().size() != this.getController().getData().getLives()) {
            this.livesContainer.getChildren().clear();
            for (int i = 0; i < this.getController().getData().getLives(); i++) {
                this.livesContainer.getChildren().add(this.lifeIcon());
            }
        }
    }

    private Node lifeIcon() {
        // TODO forse meglio se si crea una factory
        Image image = new Image("textures/pac_man/pac_man2.png");
        final ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setRotate(90);
        imageView.setFitWidth(LIFE_ICON_DIMENSION);
        imageView.setFitHeight(LIFE_ICON_DIMENSION);
        return imageView;
    }

    /**
     * Method to move an ImageView from a startPos to a destPos, using PathTransition.
     * @param image the ImageView to move
     * @param startPos the start Pair<Integer, Integer>
     * @param destPos the destination Pair<Integer, Integer>
     */
    private void transition(final ImageView image, final Pair<Integer, Integer> startPos, final Pair<Integer, Integer> destPos) {
        final PathTransition p = new PathTransition();
        p.setNode(image);
        p.setDuration(Duration.seconds(0.3333));
        p.setPath(new Line(this.squareSize * startPos.getX() + this.squareSize / 2,
                this.squareSize * startPos.getY() + this.squareSize / 2,
                this.squareSize * destPos.getX() + this.squareSize / 2,
                this.squareSize * destPos.getY() + this.squareSize / 2));
        image.setY(this.squareSize * destPos.getY());
        p.setCycleCount(1);
        p.play();
    }

    private void changeGameState() {
        switch (this.gameState.getState()) {
        case FINISHED:
            if (!this.gameState.isStarting()) {
                gameState.setStarting(true);
                this.countdownTimer.scheduleAtFixedRate(new TimerTask() {
                    private int value = COUNTDOWN + 1;
                    @Override
                    public void run() {
                        if (value > 1) {
                            Platform.runLater(() -> countDown.setText(String.valueOf(this.value)));
                            this.value--;
                        } else {
                            Platform.runLater(() -> countDown.setText(""));
                            startGame();
                            gameState.setStarting(false);
                            this.cancel();
                        } 
                    }
                }, 0, 1000);
            }
            break;
        case RUNNING:
            this.pauseGame();
            break;
        case PAUSE:
            this.resumeGame();
            break;
        default:
            break;
        }
    }

    private void startGame() {
        this.getController().startGame();
        this.entitiesAnimationTimer.start();
        gameState.setState(GameState.State.RUNNING);
        Platform.runLater(() -> this.gameStateLabel.setText("Pause Game"));
    }
    private void pauseGame() {
        this.getController().pauseGame();
        this.entitiesAnimationTimer.stop();
        this.gameState.setState(GameState.State.PAUSE);
        Platform.runLater(() -> this.gameStateLabel.setText("Resume Game"));
    }
    private void resumeGame() {
        this.getController().resumeGame();
        this.entitiesAnimationTimer.start();
        this.gameState.setState(GameState.State.RUNNING);
        Platform.runLater(() -> this.gameStateLabel.setText("Pause Game"));
    }
    private void endGame() {
        this.getController().stopGame();
        this.entitiesAnimationTimer.stop();
        this.gameState.setState(GameState.State.FINISHED);
        this.getView().setScene(GameScene.GAMEOVER);
        Platform.runLater(() -> this.gameStateLabel.setText("Start Game"));
    }

    /**
     * Call this method to initialize the timer that changes the entity images to make it animate.
     */
    private void initializeAnimations() {
        this.pacmanImagesList.add(new Image("textures/pac_man/pac_man0.png"));
        this.pacmanImagesList.add(new Image("textures/pac_man/pac_man1.png"));
        this.pacmanImagesList.add(new Image("textures/pac_man/pac_man2.png"));
        this.pacmanImagesList.add(new Image("textures/pac_man/pac_man3.png"));

        //This iterator is cyclic. example: with list[1,2,3] calling next() returns 1 2 3 1 2 3 1 2 3...
        this.pacmanImagesIterator = Iterables.cycle(this.pacmanImagesList).iterator();

        this.entitiesAnimationTimer = new AnimationTimer() {
            @Override
            public void handle(final long currentNanoTime) {
                //Change image every 0.1 seconds
                if (currentNanoTime - startNanoTime >= 1.0e8) {
                    pacmanImage.setImage(pacmanImagesIterator.next());
                    startNanoTime = System.nanoTime();
                }
            }
        };
    }
}
