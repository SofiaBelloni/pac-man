package view;


import java.io.IOException;

import controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utils.Pair;
import view.controllers.SceneController;
/**
 * This class represents the implementation of the view interface.
 */
public class ViewImpl implements View {

    private static final String TITLE = "PacMan";

    private Controller controller;
    private SceneController sceneController;
    private final Stage stage;
    /**
     * Constructor.
     * @param stage
     *      the stage of the application.
     */
    public ViewImpl(final Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
        //this.stage.setFullScreen(true);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        this.stage.setHeight(screenBounds.getHeight() * 90 / 100);
        this.stage.setWidth(screenBounds.getWidth() * 90 / 100);
        this.stage.setResizable(false);
        this.stage.setTitle(TITLE);
        this.setScene(GameScene.GAME);
    }

    @Override
    public void setVisible() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render() {
        this.sceneController.render();

    }

    @Override
    public void setScene(final GameScene scene) {
        try {
            Pair<Scene, SceneController> gameScene = SceneLoader.loadScene(scene);
            gameScene.getX().getRoot().requestFocus();
            gameScene.getX().getRoot().setOnKeyPressed(gameScene.getY()::onKeyPressed);
            stage.setScene(gameScene.getX());
            stage.show();
            gameScene.getY().init(controller, this);
            this.sceneController = gameScene.getY();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
