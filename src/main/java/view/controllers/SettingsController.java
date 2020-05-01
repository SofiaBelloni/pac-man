package view.controllers;

import java.io.IOException;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import view.GameScene;
import view.SceneLoader;
import view.View;

public class SettingsController extends SceneController {

    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
    }

    @FXML
    private void onMainMenuClick(final ActionEvent event) throws IOException {
        this.getView().setScene(GameScene.MAINMENU);
    }
}
