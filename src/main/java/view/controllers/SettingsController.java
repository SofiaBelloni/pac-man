package view.controllers;

import java.io.IOException;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import view.SceneLoader;
import view.View;

public class SettingsController extends SceneController {

    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
    }

    @FXML
    private void onMainMenuClick(final ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(SceneLoader.loadScene("mainmenu").getX());
        window.show();
    }
}
