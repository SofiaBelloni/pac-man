package view.controllers;

import controller.Controller;
import javafx.fxml.FXML;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import view.AnimatedSprite;
import view.SpritesFactory;
import view.View;

public class GameViewController extends SceneController {
//    private final Stage stage;
//    private final Scene gameScene;
    //private final AnimatedSprite pacMan;
    //private final SpritesFactory spritesFactory;
    
    @FXML
    private HBox rootBox;
    
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
        this.highScore.setText(String.valueOf(controller.getHighScore()));
//        this.stage = stage;
//        Group root = new Group();
//        this.gameScene = new Scene(root);
//        this.stage.setScene(gameScene);
        //spritesFactory = new SpritesFactory();
        
        //Canvas canvas = new Canvas(1920, 1080);
        //root.getChildren().add(canvas);
        //this.pacMan = spritesFactory.pacMan(canvas);
        
        //this.pacMan.startAnimation();
        GridPane grid = new GridPane();
        for (int row = 0; row < 31; row++) {
            for (int col = 0; col < 28; col++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(2);
                rec.setHeight(2);
                rec.setFill(Color.AQUA);
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                grid.getChildren().addAll(rec);
            }
        }
        
        this.gamePane.getChildren().add(grid);
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
    
    public void updateScore(int currentScore) {
        this.score.setText(String.valueOf(currentScore));
    }
    
    public void updateLevel(int level) {
        this.level.setText(String.valueOf(level));
    }
        
    public void setTimer(double value) {
        this.timer.setProgress(value);
    }
    
    public void setLive(int livesNumber) {
        this.lives.setText(String.valueOf(livesNumber));
    }
}
