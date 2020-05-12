package view.controllers;

import controller.Controller;
import controller.GameMaps;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import view.GameScene;
import view.View;
import view.utils.SoundManager;
import view.utils.SoundManager.Sound;

public class SettingsController extends SceneController {

    @FXML
    private ComboBox<String> gameMaps;

    @FXML
    private RadioButton volumeOn;

    @FXML
    private RadioButton volumeOff;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        if (SoundManager.getSoundManager().getSoundEnabled()) {
            this.volumeOn.setSelected(true);
        } else {
            this.volumeOff.setSelected(true);
        }
        for (final GameMaps e : GameMaps.values()) {
            this.gameMaps.getItems().add(e.getName());
        }
    }
    /**
     *
     */
    @FXML
    private void onResetRankingClick() {
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getController().resetRanking();
    }
    /**
     *
     */
    @FXML
    private void setGameMap() {
        this.getController().setGameMap(this.gameMaps.getSelectionModel().getSelectedItem());
    }
    /**
     *
     */
    @FXML
    private void onBackClick() {
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.MAINMENU);
    }
    /**
     *
     */
    @FXML
    private void volumeOffSelected() {
        SoundManager.getSoundManager().setSoundEnabled(false);
    }
    /**
     *
     */
    @FXML
    private void volumeOnSelected() {
        SoundManager.getSoundManager().setSoundEnabled(true);
    }
}
