package view.cotrollers;

import java.io.IOException;

import controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.SceneLoader;
import view.View;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainMenuController extends SceneController {


    @FXML
    private ImageView title;

    @FXML
    private Button newGameButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button scoreButton;

    private SceneLoader sceneLoader;

    public MainMenuController(final Controller controller, final View view) {
        super(controller, view);
        this.newGameButton = new Button("New Game");
        this.scoreButton = new Button("Score");
        this.settingsButton = new Button("Settings");
    }

    @FXML
    private void onNewGameClick(final ActionEvent event) throws IOException {
        this.sceneLoader = new SceneLoader("GameViewFXGL");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(this.sceneLoader.getScene());
        window.show();
    }

    @FXML
    private void onScoreClick(final ActionEvent event) throws IOException {
        this.sceneLoader = new SceneLoader("ScoreView");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(this.sceneLoader.getScene());
        window.show();
    }

    @FXML
    private void onSettingsClick(final ActionEvent event) throws IOException {
        this.sceneLoader = new SceneLoader("SettingsView");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(this.sceneLoader.getScene());
        window.show();
    }
}

