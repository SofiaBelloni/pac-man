package application;

import controller.Controller;
import controller.ControllerImpl;
import controller.GameMapLoader;
import controller.GameMapLoaderImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import model.GameMap;
import model.GameMapImpl;
import model.GameModel;
import model.GameModelImpl;
import utils.Pair;
import view.GameScene;
import view.SceneLoader;
import view.View;
import view.ViewImpl;
import view.controllers.GameViewController;
import view.controllers.MainMenuController;
import view.controllers.SceneController;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class PacManApp extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        final GameModel model = new GameModelImpl();
        View view = new ViewImpl(stage);
        Controller controller = new ControllerImpl(model, view);
        view.setController(controller);
    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
