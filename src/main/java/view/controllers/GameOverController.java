package view.controllers;

import java.io.IOException;
import java.util.Optional;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.GameScene;
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
        this.levelLabel.setText("Level: " + String.valueOf(controller.getData().getLevel()));
        this.scoreLabel.setText("Score: " + String.valueOf(controller.getData().getCurrentScore()));
    }

    @FXML
    void saveAndGoNextScene(final ActionEvent event) throws IOException {
        this.getController().savePlayer(Optional.of(playerNameText.getText()).filter(t -> !t.isBlank()).orElse("Guest"));
        this.getView().setScene(GameScene.MAINMENU);
    }

}
