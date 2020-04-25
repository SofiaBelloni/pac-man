package view.controllers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.View;

public final class GameOverController extends SceneController {

    @FXML
    private TextField textUserName;

    @FXML
    private Button buttonSave;

    @FXML
    private Label labelScoreHere;

    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.labelScoreHere.setText(String.valueOf(controller.getHighScore()));
    }

    @FXML
    void saveAndGoNextScene(final ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Hai premuto il bottone SAVE");
        alert.setHeaderText(null);
        alert.setContentText("QUI PASSARE ALLA SCHERMATA LEADERBOARD!");

        alert.showAndWait();
    }
}
