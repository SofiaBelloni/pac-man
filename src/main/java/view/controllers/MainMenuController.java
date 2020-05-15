package view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.GameScene;
import view.View;
import view.utils.SoundManager;
import view.utils.SoundManager.Sound;

import java.io.IOException;

import controller.Controller;

/**
 *this class represents the controller of the main menu scene.
 */
public class MainMenuController extends SceneController {

    @FXML
    private Button newGameButton;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.newGameButton.requestFocus();
    }

    @FXML
    public final void onNewGameClick() throws IOException {
        SoundManager.getSoundManager().stopAll();
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.GAME);
    }

    @FXML
    public final void onScoreClick() throws IOException {
        SoundManager.getSoundManager().stopAll();
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.SCORE);
    }

    @FXML
    public final void onSettingsClick() throws IOException {
        SoundManager.getSoundManager().stopAll();
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.SETTINGS);
    }

    @FXML
    public final void onInstructionsClick() {
        SoundManager.getSoundManager().stopAll();
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.INSTRUCTIONS);
    }
}

