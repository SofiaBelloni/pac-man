package view.controllers;

import java.io.IOException;

import com.sun.tools.javac.Main;

import application.Launcher;
import application.PacManApp;
import controller.Controller;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import view.View;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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

    private final Stage stage;

    public MainMenuController(final Controller controller, final View view, final Stage stage) throws IOException {
        super(controller, view);
        this.stage = stage;
    }

    @FXML
    private void onNewGameClick(final ActionEvent event) throws IOException {
       
    }

    @FXML
    private void onScoreClick(final ActionEvent event) throws IOException {
    }

    @FXML
    private void onSettingsClick(final ActionEvent event) throws IOException {

    }
}

