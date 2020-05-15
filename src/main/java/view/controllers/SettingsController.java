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
/*
 * This class manages settings view
 */
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
    /*
     *Resets the ranking
     */
    @FXML
    public final void onResetRankingClick() {
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getController().resetRanking();
    }
    /*
     *Sets the game map
     */
    @FXML
    public final void setGameMap() {
        this.getController().setGameMap(this.gameMaps.getSelectionModel().getSelectedItem());
    }
    /*
     *Sets the main menu view
     */
    @FXML
    public final void onBackClick() {
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.MAINMENU);
    }
    /*
     *Sets the volume off
     */
    @FXML
    public final void volumeOffSelected() {
        SoundManager.getSoundManager().setSoundEnabled(false);
    }
    /*
     *Sets the volume on
     */
    @FXML
    public final void volumeOnSelected() {
        SoundManager.getSoundManager().setSoundEnabled(true);
    }
}
