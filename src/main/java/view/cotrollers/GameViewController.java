package view.cotrollers;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
        
        Canvas canvas = new Canvas(128, 128);
        root.getChildren().add(canvas);
        this.pacMan = spritesFactory.pacMan(canvas);
        
        this.pacMan.startAnimation();
    }
}
