package view.controllers;

import java.io.IOException;

import controller.Controller;
import controller.FileManager;
import controller.FileManagerImpl;
import controller.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.View;

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

    private FileManager fileManager = new FileManagerImpl();

    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.score.setCellValueFactory(new PropertyValueFactory<>("score"));
        this.level.setCellValueFactory(new PropertyValueFactory<>("level"));
        this.table.getItems().addAll(this.fileManager.getAllPlayers());
    }

    @FXML
    void goBack(final ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("PASSARE AL MENU' PRINCIPALE!");
        alert.showAndWait();
        // TODO passare alla schermata di pausa
    }

}