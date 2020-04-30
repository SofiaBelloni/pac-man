package view;


import java.io.IOException;

import controller.Controller;
import javafx.scene.Scene;
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
    }

    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
        this.stage.setFullScreen(true);
        this.stage.setResizable(false);
        this.stage.setTitle(TITLE);
        this.setScene(GameScene.MAINMENU);
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
            stage.setScene(gameScene.getX());
            stage.show();
            gameScene.getY().init(controller, this);
            this.sceneController = gameScene.getY();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
