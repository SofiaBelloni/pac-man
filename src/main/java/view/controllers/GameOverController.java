package view.controllers;

import java.io.IOException;
import java.util.Optional;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import view.GameScene;
import view.View;
import view.utils.SoundManager;
import view.utils.SoundManager.Sound;
/**
 * Describes the controller for the Game Over scene.
 */
public final class GameOverController extends SceneController {

    @FXML
    private Label levelLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private TextField playerNameText;

    @FXML
    private Button saveButton;

    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.levelLabel.setText("Level: " + controller.getData().getLevel());
        this.scoreLabel.setText("Score: " + controller.getData().getCurrentScore());
        SoundManager.getSoundManager().play(Sound.GAME_OVER);
    }

    @FXML
    void saveAndGoNextScene(final ActionEvent event) throws IOException {
        this.nextScene();
    }

    @Override
    public void onKeyPressed(final KeyEvent event) {
        switch (event.getCode()) {
        case ENTER:
            this.nextScene();
            break;
        default:
            break;
        }
    }

    /**
     * this method is called when the user want to go to the next scene.
     */
    private void nextScene() {
        this.getController().savePlayer(Optional.of(playerNameText.getText()).filter(t -> !t.isBlank()).orElse("Guest"));
        SoundManager.getSoundManager().stopAll();
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.MAINMENU);
    }

}
