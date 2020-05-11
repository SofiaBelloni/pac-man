package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.GameScene;
import view.View;
import view.utils.SoundManager;
import view.utils.SoundManager.Sound;

import java.io.IOException;

import controller.Controller;

public class MainMenuController extends SceneController {

    @FXML
    private Button newGameButton;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.newGameButton.requestFocus();
    }

    @FXML
    private void onNewGameClick() throws IOException {
        SoundManager.getSoundManager().stopAll();
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.GAME);
    }

    @FXML
    private void onScoreClick() throws IOException {
        SoundManager.getSoundManager().stopAll();
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.SCORE);
    }

    @FXML
    private void onSettingsClick() throws IOException {
        SoundManager.getSoundManager().stopAll();
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.SETTINGS);
    }
}

