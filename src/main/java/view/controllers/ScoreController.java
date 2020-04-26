package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class ScoreController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> score;

    @FXML
    private TableColumn<?, ?> level;

    @FXML
    void goBack(ActionEvent event) {

    }

}