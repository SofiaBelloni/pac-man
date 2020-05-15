package view.controllers;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.GameScene;
import view.View;
import view.utils.SoundManager;
import view.utils.SoundManager.Sound;
/**
 * This class represents the controller for the instructions scene.
 */
public class InstructionsController extends SceneController {

    @FXML
    private Button backButton;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
    }

    @FXML
    public final void goBack() {
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.MAINMENU);

    }

}
