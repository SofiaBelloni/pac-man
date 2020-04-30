package view.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import controller.Controller;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Directions;
import model.Ghost;
import model.Ghosts;
import utils.Pair;
import utils.PairImpl;
import view.AnimatedSprite;
import view.SpritesFactory;
import view.View;
import view.utils.EntityTextureIterator;
import view.utils.PacManTextureIterator;

public class GameViewController extends SceneController {
    
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
    private Label lives;

    private HBox livesContainer;

    private Pair<Integer, Integer> pacManPosition;

    int squareSize;

    private final Map<Integer, ImageView> ghostView = new HashMap<>();
    
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        squareSize = (int) (screenBounds.getHeight() / controller.getData().getyMapSize());
        int width = squareSize * controller.getData().getxMapSize();
        int height = squareSize * controller.getData().getyMapSize();
        EntityTextureIterator pacManImage = new PacManTextureIterator();
        this.gamePane.setMinSize(width, height);
        this.gamePane.setMaxSize(width, height);
        HBox.setHgrow(this.labelBox, Priority.SOMETIMES);
        GridPane gridPane = new GridPane();
        this.gamePane.getChildren().add(gridPane);
        for (Pair<Integer, Integer> e : this.getController().getData().getWallsPositions()) {
            ImageView image = new ImageView("textures/wall/wall.png");
            image.setFitWidth(squareSize);
            image.setFitHeight(squareSize);
            gridPane.add(image, e.getX(), e.getY());
        }
        
        for (Pair<Integer, Integer> e : this.getController().getData().getPillsPositions()) {
            ImageView image = new ImageView("textures/pill/pill.png");
            image.setFitWidth(squareSize);
            image.setFitHeight(squareSize);
            gridPane.add(image, e.getX(), e.getY());
        }
        entityPane = new Pane();
        this.gamePane.getChildren().add(entityPane);
        
        //test iteratore immagini pacman
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        System.out.println(pacManImage.nextImage());
        ImageView image = new ImageView(pacManImage.nextImage());
        image.setRotate(90); //al posto di 90 chiamare metodo getAngle che restituisce i gradi in base alla direzione
        image.setFitWidth(squareSize);
        image.setFitHeight(squareSize);
        gridPane.add(image, this.getController().getData().getPacManXPosition(), this.getController().getData().getPacManYPosition());
        this.ghostSpawn(1, Ghosts.BLINKY, false);
        this.ghostSpawn(2, Ghosts.INKY, false);
        this.ghostSpawn(3, Ghosts.PINKY, false);
        this.ghostSpawn(4, Ghosts.CLYDE, false);
    
        
        
        //Inizialize HUD
        this.highScore.setText(String.valueOf(controller.getHighScore()));
        this.level.setText(String.valueOf(controller.getData().getLevel()));
        for (int i = 0; i < controller.getData().getLives(); i++) {
            /*this.livesContainer.getChildren().add(this.lifeIcon());*/
        }
    }
    
    @FXML
    void onKeyPressed(final KeyEvent event) {
//        if (x.getCode().equals(KeyCode.W)) {
//            this.pacMan.moveUp();
//        }
//        
//        if (x.getCode().equals(KeyCode.S)) {
//            this.pacMan.moveDown();
//        }
//        
//        if (x.getCode().equals(KeyCode.A)) {
//            this.pacMan.moveLeft();
//        }
//        
//        if (x.getCode().equals(KeyCode.D)) {
//            this.pacMan.moveRight();
//        }
        }

    /**
     * Method that update the HUD data value.
     */
    public final void update() {
        this.score.setText(String.valueOf(this.getController().getData().getCurrentScore()));
        this.timer.setProgress(this.getController().getData().getLevelTimePercentage());
        this.level.setText(String.valueOf(this.getController().getData().getLevel()));
        if (this.livesContainer.getChildren().size() != this.getController().getData().getLives()) {
            for (int i = 0; i < this.getController().getData().getLives(); i++) {
                this.livesContainer.getChildren().add(this.lifeIcon());
            }
        }
    }

    public final void ghostSpawn(final int value, final Ghosts name, final boolean eatable) {
        if (!ghostView.containsKey(value)) {
            ImageView ghost = new ImageView();
            ghost.setFitWidth(squareSize);
            ghost.setFitHeight(squareSize);
            ghostView.put(value, ghost);
            ghost.setY(squareSize * 14);
            if (name.equals(Ghosts.BLINKY)) {
                entityPane.getChildren().add(ghostView.get(value));
                ghost.setX(squareSize * 12);
            } else if (name.equals(Ghosts.PINKY)) {
                entityPane.getChildren().add(ghostView.get(value));
                ghost.setX(squareSize * 13);
            } else if (name.equals(Ghosts.INKY)) {
                entityPane.getChildren().add(ghostView.get(value));
                ghost.setX(squareSize * 14);
            } else {
                entityPane.getChildren().add(ghostView.get(value));
                ghost.setX(squareSize * 15);
            }
        }
        if (eatable) {
            ghostView.get(value).setImage(new Image("textures/ghost/eatable.png"));
        } else {
            ghostView.get(value).setImage(new Image("textures/" + name.toString() + "/RIGHT.png"));
        }
    }

    public final void ghostRender(final Ghosts name, final Directions dir, final boolean eatable, final boolean returnHome, final int value) {
            final ImageView ghostImage = this.ghostView.get(value);
            if (returnHome) {
                this.ghostSpawn(value, name, eatable);
            } else {
                if (eatable) {
                    ghostImage.setImage(new Image("textures/ghost/eatable.png"));
                } else {
                    ghostImage.setImage(new Image("textures/" + name.toString() + "/" + dir.toString() + ".png"));
                }
                PathTransition p = new PathTransition();
                p.setNode(ghostImage);
                p.setDuration(Duration.seconds(0.5));
                if (dir.equals(Directions.RIGHT)) {
                    p.setPath(new Line(ghostImage.getX() + this.squareSize / 2, ghostImage.getY() + this.squareSize / 2,
                            ghostImage.getX() +  this.squareSize * 3 / 2, ghostImage.getY() + this.squareSize / 2));
                    ghostImage.setX(ghostImage.getX() + this.squareSize); 
                } else   if (dir.equals(Directions.UP)) {
                    p.setPath(new Line(ghostImage.getX() + this.squareSize / 2, ghostImage.getY() + this.squareSize / 2,
                            ghostImage.getX() + this.squareSize / 2, ghostImage.getY() - this.squareSize));
                    ghostImage.setY(ghostImage.getY() -  this.squareSize * 3 / 2); 
                } else   if (dir.equals(Directions.LEFT)) {
                    p.setPath(new Line(ghostImage.getX() + this.squareSize / 2, ghostImage.getY() + this.squareSize / 2,
                            ghostImage.getX() - this.squareSize, ghostImage.getY() + this.squareSize / 2));
                    ghostImage.setX(ghostImage.getX() - this.squareSize * 3 / 2); 
                } else {
                    p.setPath(new Line(ghostImage.getX() + this.squareSize / 2, ghostImage.getY() + this.squareSize / 2,
                            ghostImage.getX() +  this.squareSize / 2, ghostImage.getY() + this.squareSize * 3 / 2));
                    ghostImage.setY(ghostImage.getY() + this.squareSize);
                }
                p.setCycleCount(1);
                p.play();
            }
        }
    /**
     * Method that show PacMan by getting his exact position from the controller.
     */


    private Node lifeIcon() { 
        // TODO forse meglio se si crea una factory
        Image image = new Image("textures/pac_man/pac_man2.png"); 
        final ImageView imageView = new ImageView(); 
        imageView.setImage(image);
        imageView.setRotate(90);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        return imageView; 
  }

}
