package view.controllers;

import controller.Controller;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
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
    private Label highScore;

    @FXML
    private Label score;

    @FXML
    private Label level;

    @FXML
    private ProgressBar timer;

    @FXML
    private HBox livesContainer;

    private Pair<Integer, Integer> pacManPosition;
    
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
//        this.stage = stage;
//        Group root = new Group();
//        this.gameScene = new Scene(root);
//        this.stage.setScene(gameScene);
        //spritesFactory = new SpritesFactory();
        
        //Canvas canvas = new Canvas(1920, 1080);
        //root.getChildren().add(canvas);
        //this.pacMan = spritesFactory.pacMan(canvas);
        
        //this.pacMan.startAnimation();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        int squareSize = (int) (screenBounds.getHeight() / controller.getData().getyMapSize());
        int width = squareSize * controller.getData().getxMapSize();
        int height = squareSize * controller.getData().getyMapSize();
        EntityTextureIterator pacManImage = new PacManTextureIterator();
        this.gamePane.setMinSize(width, height);
        this.gamePane.setMaxSize(width, height);
        HBox.setHgrow(this.labelBox, Priority.SOMETIMES);
        GridPane gridPane = new GridPane();
        this.gamePane.getChildren().add(gridPane);
//        for (int i = 0; i < controller.getyMapSize(); i++) {
//            for (int j = 0; j < controller.getxMapSize(); j++) {
//                ImageView image = new ImageView();
//                image.setFitWidth(squareSize);
//                image.setFitHeight(squareSize);
//                gridPane.add(image, j, i);
//            }
//        }
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
        //this.gamePane.resize(width, this.gamePane.getHeight());
        
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
    
        
        
        //Inizialize HUD
        this.highScore.setText(String.valueOf(controller.getHighScore()));
        this.level.setText(String.valueOf(controller.getData().getLevel()));
        for (int i = 0; i < controller.getData().getLives(); i++) {
            this.livesContainer.getChildren().add(this.lifeIcon());
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
