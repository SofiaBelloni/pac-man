package view.cotrollers;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.View;

public final class GameOverController extends SceneController {

    @FXML
    private Label labelName;

    @FXML
    private Label labelScore;

    public GameOverController(final Controller controller, final View view) {
        super(controller, view);
        this.labelScore.setText("Name: "); // + controller.getUser().map(u -> u.getName()).orElse("Guest"));
        this.labelName.setText("Score: "); // + controller.getCurrentTotalScore());
    }

    @FXML
    void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            // QUI PASSARE ALLA SCHERMATA LEADERBOARD
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Hai premuto il tasto ENTER!");

            alert.showAndWait();
        }
    }
}
