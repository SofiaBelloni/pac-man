package view.controllers;


import java.io.IOException;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.Player;
import view.GameScene;
import view.View;
import view.utils.SoundManager;
import view.utils.SoundManager.Sound;
/**
 * This class represents the controller for the ranking scene.
 */
public class ScoreController extends SceneController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Player> table;

    @FXML
    private TableColumn<Player, String> name;

    @FXML
    private TableColumn<Player, String> score;

    @FXML
    private TableColumn<Player, String> level;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.score.setCellValueFactory(new PropertyValueFactory<>("score"));
        this.level.setCellValueFactory(new PropertyValueFactory<>("level"));
        this.table.getItems().addAll(controller.getAllPlayers());
    }

    @FXML
    public final void goBack() throws IOException {
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.MAINMENU);
    }

}
