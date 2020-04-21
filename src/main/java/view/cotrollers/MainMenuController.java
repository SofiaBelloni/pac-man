package view.cotrollers;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import view.View;

public class MainMenuController extends SceneController {

    @FXML
    private Button newGameButton;

    @FXML
    private Button scoreButton;

    @FXML
    private Button settingsButton;

    public MainMenuController(final Controller controller, final View view) {
        super(controller, view);
        this.newGameButton = new Button("New Game");
        this.scoreButton = new Button("Score");
        this.settingsButton = new Button("Settings");
    }

    @FXML
    public final void handleNewGameAction(final View gameView) {
        this.newGameButton.setOnMouseClicked(e -> {
        this.getView().close();
        });
    }
}
