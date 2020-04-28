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
    void saveAndGoNextScene(final ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Hai premuto il bottone SAVE");
        alert.setHeaderText(null);
        alert.setContentText("QUI PASSARE ALLA SCHERMATA LEADERBOARD!");

        alert.showAndWait();
    }
}
