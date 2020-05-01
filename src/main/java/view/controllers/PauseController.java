package view.controllers;

import java.io.IOException;
import controller.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import view.GameScene;
import view.SceneLoader;
import view.View;

public class PauseController extends SceneController {

    @FXML
    private Button exit = new Button("");

    @FXML
    private Button restart = new Button("");

    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
    }

    @FXML
    public final void initialize() {
        this.exit.setGraphic(new ImageView(new Image("textures/images/exit.png", 100, 100, true, true)));
        this.restart.setGraphic(new ImageView(new Image("textures/images/restart.png", 100, 100, true, true)));
    }

    @FXML
    public final void onExitClick(final Event event) throws IOException {
        this.getView().setScene(GameScene.MAINMENU);
    }

    @FXML
    public final void onRestartClick(final Event event) throws IOException {
        this.getView().setScene(GameScene.GAME);
    }

    @FXML
    public final void onResumeClick(final Event event) throws IOException {
        this.getView().setScene(GameScene.GAME);
    }
}
