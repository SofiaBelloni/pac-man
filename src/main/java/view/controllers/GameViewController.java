package view.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import utils.GhostUtils;
import utils.Pair;
import utils.PairImpl;
import view.GameScene;
import view.View;
import view.utils.GameState;

public class GameViewController extends SceneController {

    private static final int LIFE_ICON_DIMENSION = 50;
    private static final int COUNTDOWN = 3;
    private static final double TRANSITION_SECONDS = 0.333;
    private static final long ANIMATION_IMAGE_CHANGE = (long) 1.0e8;

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
    private final Map<Integer, ImageView> ghostImages = new HashMap<>();
    private final Map<Pair<Integer, Integer>, ImageView> gameMap = new HashMap<>();
    private ImageView pacmanImage;
    private Pair<Integer, Integer> pacmanPosition;
    private final Image pill = new Image("textures/pill/pill.png");
    private AnimationTimer entitiesAnimationTimer;
    private final List<Image> pacmanImagesList = new ArrayList<>();
    private long startNanoTime = System.nanoTime();
    private Iterator<Image> pacmanImagesIterator;
    private final Timer countdownTimer = new Timer();
    private final GameState gameState = new GameState();
    private int currentLevel;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.currentLevel = 1;
        this.squareSize = (int) (this.rootBox.getHeight() / controller.getData().getyMapSize());
        final int width = squareSize * controller.getData().getxMapSize();
        final int height = squareSize * controller.getData().getyMapSize();
        this.gamePane.setMinSize(width, height);
        this.gamePane.setMaxSize(width, height);
        HBox.setHgrow(this.labelBox, Priority.SOMETIMES);
        final GridPane mapGrid = new GridPane();
        this.gamePane.getChildren().add(mapGrid);
        for (final Pair<Integer, Integer> e : this.getController().getData().getWallsPositions()) {
            final ImageView image = new ImageView("textures/wall/wall.png");
            image.setFitWidth(squareSize);
            image.setFitHeight(squareSize);
            mapGrid.add(image, e.getX(), e.getY());
            this.gameMap.put(e, image);
        }

        for (final Pair<Integer, Integer> e : this.getController().getData().getPillsPositions()) {
            final ImageView image = new ImageView("textures/pill/pill.png");
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

    /**
     * {@inheritDoc}
     */
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
            this.endGame(GameScene.MAINMENU);
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
            this.endGame(GameScene.GAMEOVER);
        }
    }

    private void ghostSpawn() {
        final Map<Integer, GhostUtils> ghosts = this.getController().getData().getGhosts();
        for (final int id : ghosts.keySet()) {
            if (!this.ghostImages.containsKey(id)) {
                final ImageView ghostImage = new ImageView();
                ghostImage.setFitWidth(this.squareSize);
                ghostImage.setFitHeight(this.squareSize);
                ghostImage.setX(this.squareSize * ghosts.get(id).getGhostPosition().getX());
                ghostImage.setY(this.squareSize * ghosts.get(id).getGhostPosition().getY());
                this.entityPane.getChildren().add(ghostImage);
                this.ghostImages.put(id, ghostImage);
                if (this.getController().getData().isGameInverted()) {
                    ghostImage.setImage(new Image("textures/ghost/eatable.png"));
                } else {
                    ghostImage.setImage(new Image("textures/" + ghosts.get(id).getGhostName().toString() + "/RIGHT.png"));
                }
            }
        }
    }

    private void ghostRender() {
        final Map<Integer, GhostUtils> ghosts = this.getController().getData().getGhosts();
        final Map<Integer, ImageView> ghostImagesCopy = new HashMap<>(this.ghostImages);
        for (final int id : ghosts.keySet()) {
            if (!this.ghostImages.containsKey(id)) {
                this.ghostSpawn();
            }
        }
        for (final int id : ghostImagesCopy.keySet()) {
            if (!ghosts.containsKey(id)) {
                this.entityPane.getChildren().remove(this.ghostImages.get(id));
                this.ghostImages.remove(id);
            } else {
                if (this.getController().getData().isGameInverted()) {
                    this.ghostImages.get(id).setImage(new Image("textures/ghost/eatable.png"));
                } else {
                    switch (ghosts.get(id).getGhostDirection()) {
                    case UP:
                        this.ghostImages.get(id).setImage(new Image("textures/" + ghosts.get(id).getGhostName().toString() + "/UP.png"));
                        break;
                    case DOWN:
                        this.ghostImages.get(id).setImage(new Image("textures/" + ghosts.get(id).getGhostName().toString() + "/DOWN.png"));
                        break;
                    case LEFT:
                        this.ghostImages.get(id).setImage(new Image("textures/" + ghosts.get(id).getGhostName().toString() + "/LEFT.png"));
                        break;
                    case RIGHT:
                        this.ghostImages.get(id).setImage(new Image("textures/" + ghosts.get(id).getGhostName().toString() + "/RIGHT.png"));
                        break;
                    default:
                        break;
                    }
                }
                this.transition(this.ghostImages.get(id), ghosts.get(id).getGhostOldPosition(), ghosts.get(id).getGhostPosition());
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
        final Pair<Integer, Integer> newPosition = new PairImpl<>(this.getController().getData().getPacManXPosition(),
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
            this.currentLevel = this.getController().getData().getLevel();
            this.pauseGame();
            this.gameState.setStarting(true);
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
                        gameState.setStarting(false);
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
        final Image image = new Image("textures/pac_man/pac_man2.png");
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
        p.setDuration(Duration.seconds(TRANSITION_SECONDS));
        p.setPath(new Line(this.squareSize * startPos.getX() + this.squareSize / 2,
                this.squareSize * startPos.getY() + this.squareSize / 2,
                this.squareSize * destPos.getX() + this.squareSize / 2,
                this.squareSize * destPos.getY() + this.squareSize / 2));
        image.setY(this.squareSize * destPos.getY());
        p.setCycleCount(1);
        p.play();
    }

    /**
     * This method call startGame, pauseGame or resumeGame basing on current game state.
     */
    private void changeGameState() {
        if (!this.gameState.isStarting()) {
            switch (this.gameState.getState()) {
            case FINISHED:
                this.gameState.setStarting(true);
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
    }

    /**
     * Start the game.
     */
    private void startGame() {
        this.getController().startGame();
        this.entitiesAnimationTimer.start();
        gameState.setState(GameState.State.RUNNING);
        Platform.runLater(() -> this.gameStateLabel.setText("Pause Game"));
    }
    /**
     * Pause the current game.
     * Call when game is running.
     */
    private void pauseGame() {
        this.getController().pauseGame();
        this.entitiesAnimationTimer.stop();
        this.gameState.setState(GameState.State.PAUSE);
        Platform.runLater(() -> this.gameStateLabel.setText("Resume Game"));
    }
    /**
     * Resume the paused game.
     * Call when game is in pause.
     */
    private void resumeGame() {
        this.getController().resumeGame();
        this.entitiesAnimationTimer.start();
        this.gameState.setState(GameState.State.RUNNING);
        Platform.runLater(() -> this.gameStateLabel.setText("Pause Game"));
    }
    /**
     * Terminate the current game and go to selected scene.
     * @param nextScene the scene you want to go.
     */
    private void endGame(final GameScene nextScene) {
        if (!this.gameState.isStarting()) {
            this.getController().stopGame();
            this.entitiesAnimationTimer.stop();
            this.gameState.setState(GameState.State.FINISHED);
            Platform.runLater(() -> this.gameStateLabel.setText("Start Game"));
            this.getView().setScene(nextScene);
        }
    }

    /**
     * Call this method to initialize the timer that changes the entity images to make them animate.
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
                if (currentNanoTime - startNanoTime >= ANIMATION_IMAGE_CHANGE) {
                    pacmanImage.setImage(pacmanImagesIterator.next());
                    startNanoTime = System.nanoTime();
                }
            }
        };
    }
}
