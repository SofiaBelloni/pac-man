package view.controllers;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Label highScore;

    @FXML
    private Label score;

    @FXML
    private Label level;

    @FXML
    private Label time;

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
}
