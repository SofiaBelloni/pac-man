package view.controllers;

import java.io.IOException;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import view.GameScene;
import view.View;
import view.utils.SoundManager;
import view.utils.SoundManager.Sound;

public class SettingsController extends SceneController {

    @FXML
    private Button volume;

    @FXML
    private ImageView mapNumber;

    private int mapCounter;

    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.mapCounter = 1;
    }

    @FXML
    private void onVolumeClick(final ActionEvent event) throws IOException {
        if (this.volume.getText().equals("Volume On")) {
            this.volume.setText("Volume Off");
        } else {
            this.volume.setText("Volume On");
        }
    }

    @FXML
    private void onChangeMapClick(final ActionEvent event) throws IOException {
        this.mapNumber.setImage(null);
    }

    @FXML
    private void onResetRankingClick(final ActionEvent event) throws IOException {
        //this.getController().
    }

    @FXML
    private void onMainMenuClick(final ActionEvent event) throws IOException {
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.MAINMENU);
    }
}
