package view.controllers;

import java.io.IOException;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.SceneLoader;
import view.View;

public final class GameOverController extends SceneController {

    @FXML
    private Label levelLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private TextField playerNameText;

    @FXML
    private Button saveButton;

    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.scoreLabel.setText("Level: " + String.valueOf(controller.getData().getLevel()));
        this.scoreLabel.setText("Score: " + String.valueOf(controller.getHighScore()));
    }

    @FXML
    void saveAndGoNextScene(final ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(SceneLoader.loadScene("mainmenu").getX());
        window.show();
    }
}
