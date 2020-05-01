package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import view.GameScene;
import view.SceneLoader;
import view.View;

import java.io.IOException;

import controller.Controller;

public class MainMenuController extends SceneController {

    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
    }

    @FXML
    private void onNewGameClick(final ActionEvent event) throws IOException {
        this.getView().setScene(GameScene.GAME);
    }

    @FXML
    private void onScoreClick(final ActionEvent event) throws IOException {
        this.getView().setScene(GameScene.SCORE);
    }

    @FXML
    private void onSettingsClick(final ActionEvent event) throws IOException {
        this.getView().setScene(GameScene.SETTINGS);
    }
}

