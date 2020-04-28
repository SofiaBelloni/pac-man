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
import model.Pair;
import model.PairImpl;
import view.AnimatedSprite;
import view.SpritesFactory;
import view.View;

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
    private Label lives;
    
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
        int squareSize = (int) (screenBounds.getHeight() / controller.getyMapSize());
        int width = squareSize * controller.getxMapSize();
        int height = squareSize * controller.getyMapSize();
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
        for (Pair<Integer, Integer> e : this.getController().getWallsPositions()) {
            ImageView image = new ImageView("textures/wall/wall.png");
            image.setFitWidth(squareSize);
            image.setFitHeight(squareSize);
            gridPane.add(image, e.getX(), e.getY());
        }
        
        for (Pair<Integer, Integer> e : this.getController().getPillsPositions()) {
            ImageView image = new ImageView("textures/pill/pill.png");
            image.setFitWidth(squareSize);
            image.setFitHeight(squareSize);
            gridPane.add(image, e.getX(), e.getY());
        }
        //this.gamePane.resize(width, this.gamePane.getHeight());
        
        //Inizialize HUD
        this.highScore.setText(String.valueOf(controller.getHighScore()));
        this.lives.setText(String.valueOf(controller.getData().getLives()));
        this.level.setText(String.valueOf(controller.getData().getLevel()));
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
        this.lives.setText(String.valueOf(this.getController().getData().getLives()));
        this.level.setText(String.valueOf(this.getController().getData().getLevel()));
    }

//    public final void updateScore(int currentScore) {
//        this.score.setText(String.valueOf(currentScore));
//    }
//    
//    public final void updateLevel(int level) {
//        this.level.setText(String.valueOf(level));
//    }
//        
//    public final void setTimer(double value) {
//        this.timer.setProgress(value);
//    }
//    
//    public final void setLive(int livesNumber) {
//        this.lives.setText(String.valueOf(livesNumber));
//    }
}
