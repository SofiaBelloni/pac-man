package view;


import java.io.IOException;

import controller.Controller;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Pair;
import view.controllers.SceneController;
import view.utils.SoundManager;
import view.utils.SoundManager.Sound;
/**
 * This class represents the implementation of the view interface.
 */
public class ViewImpl implements View {

    private static final String TITLE = "PacMan";

    private Controller controller;
    private SceneController sceneController;
    private final Stage stage;
    private boolean viewStarted;
    /**
     * Constructor.
     * @param stage
     *      the stage of the application.
     */
    public ViewImpl(final Stage stage) {
        this.stage = stage;
        this.viewStarted = false;
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
        this.stage.setResizable(false);
        this.stage.setTitle(TITLE);
        this.setScene(GameScene.MAINMENU);
        SoundManager.getSoundManager().play(Sound.START);
    }

    @Override
    public void setVisible() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render() {
        Platform.runLater(() -> this.sceneController.render());

    }

    @Override
    public void setScene(final GameScene scene) {
        try {
            Pair<Scene, SceneController> gameScene = SceneLoader.loadScene(scene);
            gameScene.getX().getRoot().requestFocus();
            gameScene.getX().getRoot().setOnKeyPressed(gameScene.getY()::onKeyPressed);
            stage.setScene(gameScene.getX());
            if (!this.viewStarted) {
                this.stage.show();
                this.viewStarted = true;
            }
            gameScene.getY().init(controller, this);
            this.sceneController = gameScene.getY();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
