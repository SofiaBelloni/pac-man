package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
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
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(SceneLoader.loadScene("game").getX());
        window.show();
    }

    @FXML
    private void onScoreClick(final ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(SceneLoader.loadScene("score").getX());
        window.show();
    }

    @FXML
    private void onSettingsClick(final ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(SceneLoader.loadScene("settings").getX());
        window.show();
    }
}

