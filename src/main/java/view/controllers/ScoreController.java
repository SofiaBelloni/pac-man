package view.controllers;

import java.io.IOException;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.Player;
import view.GameScene;
import view.View;
/**
 * This class represents the controller for the leaderboard scene.
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
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.score.setCellValueFactory(new PropertyValueFactory<>("score"));
        this.level.setCellValueFactory(new PropertyValueFactory<>("level"));
        this.table.getItems().addAll(controller.getAllPlayers());
    }

    @FXML
    void goBack(final ActionEvent event) throws IOException {
        this.getView().setScene(GameScene.MAINMENU);
    }

}