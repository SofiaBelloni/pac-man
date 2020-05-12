package view.controllers;

import controller.Controller;
import controller.GameMaps;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import view.GameScene;
import view.View;
import view.utils.SoundManager;
import view.utils.SoundManager.Sound;

import java.io.IOException;

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
        for (GameMaps e : GameMaps.values()) {
            this.gameMaps.getItems().add(e.getName());
        }
    }
    /**
     *
     * @param event
     * Resets the ranking
     */
    @FXML
    private void onResetRankingClick(final ActionEvent event) throws IOException {
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getController().resetRanking();
    }
    /**
     *
     * @param event
     * Sets the game map
     */
    @FXML
    void setGameMap(final ActionEvent event) {
        this.getController().setGameMap(this.gameMaps.getSelectionModel().getSelectedItem());
    }
    /**
     *
     * @param event
     * Sets the main menu scene
     */
    @FXML
    void onBackClick(final ActionEvent event) {
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.MAINMENU);
    }
    /**
     *
     * @param event
     * Sets the volume off
     */
    @FXML
    void volumeOffSelected(final ActionEvent event) {
        SoundManager.getSoundManager().setSoundEnabled(false);
    }
    /**
     *
     * @param event
     * Sets the volume on
     */
    @FXML
    void volumeOnSelected(final ActionEvent event) {
        SoundManager.getSoundManager().setSoundEnabled(true);
    }
}
