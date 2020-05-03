package view.controllers;

import java.util.HashMap;
import java.util.Map;

import controller.Controller;
import javafx.animation.PathTransition;
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
import view.View;

public class GameViewController extends SceneController {

    private static final int BLINKY_X_START_POSITION = 12;

    private static final int PINKY_X_START_POSITION = 13;

    private static final int INKY_X_START_POSITION = 14;

    private static final int CLYDE_X_START_POSITION = 15;

    private static final int GHOST_Y_START_POSITION = 14;
    
    private static final int LIFE_ICON_DIMENSION = 50;

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
    private ProgressBar timer;

    @FXML
    private HBox livesContainer;

    private int squareSize;

    private final Map<Integer, ImageView> ghostView = new HashMap<>();
    private final Map<Pair<Integer, Integer>, ImageView> gameMap = new HashMap<>();
    private ImageView pacmanImage;
    private Pair<Integer, Integer> pacmanPosition;
    private final Image pill = new Image("textures/pill/pill.png");

    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        // Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        // squareSize = (int) (screenBounds.getHeight() /
        // controller.getData().getyMapSize());
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
        //start the gameLoop
        this.getController().startGame();
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        switch (event.getCode()) {
        case UP:
        case W:
            this.pacmanImage.setRotate(0);
            this.getController().newPacManDirection(Directions.UP);
            break;
        case DOWN:
        case S:
            this.pacmanImage.setRotate(180);
            this.getController().newPacManDirection(Directions.DOWN);
            break;
        case LEFT:
        case A:
            this.pacmanImage.setRotate(270);
            this.getController().newPacManDirection(Directions.LEFT);
            break;
        case RIGHT:
        case D:
            this.pacmanImage.setRotate(90);
            this.getController().newPacManDirection(Directions.RIGHT);
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
        // TODO
    }

    public final void ghostSpawn() {
        for (int id : this.getController().getData().getGhostsDirections().keySet()) {
            final Ghosts name = this.getController().getData().getGhostsTypes().get(id);
            if (!this.ghostView.containsKey(id)) {
                ImageView ghost = new ImageView();
                ghost.setFitWidth(this.squareSize);
                ghost.setFitHeight(this.squareSize);
                this.ghostView.put(id, ghost);
                ghost.setY(this.squareSize * GHOST_Y_START_POSITION);
                if (name.equals(Ghosts.BLINKY)) {
                    this.entityPane.getChildren().add(this.ghostView.get(id));
                    ghost.setX(this.squareSize * BLINKY_X_START_POSITION);
                } else if (name.equals(Ghosts.PINKY)) {
                    this.entityPane.getChildren().add(this.ghostView.get(id));
                    ghost.setX(this.squareSize * PINKY_X_START_POSITION);
                } else if (name.equals(Ghosts.INKY)) {
                    this.entityPane.getChildren().add(this.ghostView.get(id));
                    ghost.setX(this.squareSize * INKY_X_START_POSITION);
                } else {
                    this.entityPane.getChildren().add(this.ghostView.get(id));
                    ghost.setX(this.squareSize * CLYDE_X_START_POSITION);
                }
                if (this.getController().getData().isGameInverted()) {
                    this.ghostView.get(id).setImage(new Image("textures/ghost/eatable.png"));
                } else {
                    this.ghostView.get(id).setImage(new Image("textures/" + name.toString() + "/RIGHT.png"));
                }
            }
        }
    }

    public final void ghostRender() {
        for (int id : this.ghostView.keySet()) {
            final Directions dir = this.getController().getData().getGhostsDirections().get(id);
            final ImageView ghostImage = this.ghostView.get(id);
            if (this.getController().getData().isGameInverted()) {
                ghostImage.setImage(new Image("textures/ghost/eatable.png"));
            } else {
                ghostImage.setImage(new Image("textures/" + this.getController().getData().getGhostsTypes().get(id).toString() + "/" +
            dir.toString() + ".png"));
            }
            PathTransition p = new PathTransition();
            p.setNode(ghostImage);
            p.setDuration(Duration.seconds(0.3333));
            if (dir.equals(Directions.RIGHT)) {
                p.setPath(new Line(ghostImage.getX() + this.squareSize / 2, ghostImage.getY() + this.squareSize / 2,
                        ghostImage.getX() + this.squareSize * 3 / 2, ghostImage.getY() + this.squareSize / 2));
                ghostImage.setX(ghostImage.getX() + this.squareSize);
            } else if (dir.equals(Directions.UP)) {
                p.setPath(new Line(ghostImage.getX() + this.squareSize / 2, ghostImage.getY() + this.squareSize / 2,
                        ghostImage.getX() + this.squareSize / 2, ghostImage.getY() - this.squareSize / 2));
                ghostImage.setY(ghostImage.getY() - this.squareSize);
            } else if (dir.equals(Directions.LEFT)) {
                p.setPath(new Line(ghostImage.getX() + this.squareSize / 2, ghostImage.getY() + this.squareSize / 2,
                        ghostImage.getX() - this.squareSize / 2, ghostImage.getY() + this.squareSize / 2));
                ghostImage.setX(ghostImage.getX() - this.squareSize);
            } else {
                p.setPath(new Line(ghostImage.getX() + this.squareSize / 2, ghostImage.getY() + this.squareSize / 2,
                        ghostImage.getX() + this.squareSize / 2, ghostImage.getY() + this.squareSize * 3 / 2));
                ghostImage.setY(ghostImage.getY() + this.squareSize);
            }
            p.setCycleCount(1);
            p.play();
            /*if (returnHome) {
                this.ghostView.remove(ghostImage);
                this.ghostSpawn(value, name, eatable);
            } else if (dead) {
                this.entityPane.getChildren().remove(ghostImage);
            }*/
        }
    }

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
        this.level.setText(String.valueOf(this.getController().getData().getLevel()));
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

    private void transition(final ImageView image, final Pair<Integer, Integer> startPos, final Pair<Integer, Integer> destPos) {
        PathTransition p = new PathTransition();
        p.setNode(image);
        p.setDuration(Duration.seconds(0.3333));
        p.setPath(new Line((this.squareSize * startPos.getX()) + (this.squareSize / 2),
                (this.squareSize * startPos.getY()) + (this.squareSize / 2),
                (this.squareSize * destPos.getX()) + (this.squareSize / 2),
                (this.squareSize * destPos.getY()) + (this.squareSize / 2)));
        image.setY(this.squareSize * destPos.getY());
        p.setCycleCount(1);
        p.play();
    }

}
