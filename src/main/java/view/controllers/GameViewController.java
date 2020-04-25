package view.controllers;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import view.AnimatedSprite;
import view.SpritesFactory;

public class GameViewController {
    private final Stage stage;
    private final Scene gameScene;
    private final AnimatedSprite pacMan;
    private final SpritesFactory spritesFactory;
    
    public GameViewController(final Stage stage) {
        this.stage = stage;
        Group root = new Group();
        this.gameScene = new Scene(root);
        this.stage.setScene(gameScene);
        spritesFactory = new SpritesFactory();
        
        Canvas canvas = new Canvas(1920, 1080);
        root.getChildren().add(canvas);
        this.pacMan = spritesFactory.pacMan(canvas);
        
        this.pacMan.startAnimation();
        
        this.gameScene.setOnKeyPressed(x -> {
            if (x.getCode().equals(KeyCode.W)) {
                this.pacMan.moveUp();
            }
            
            if (x.getCode().equals(KeyCode.S)) {
                this.pacMan.moveDown();
            }
            
            if (x.getCode().equals(KeyCode.A)) {
                this.pacMan.moveLeft();
            }
            
            if (x.getCode().equals(KeyCode.D)) {
                this.pacMan.moveRight();
            }
        });
    }
}
