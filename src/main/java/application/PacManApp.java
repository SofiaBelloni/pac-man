package application;

import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import model.GameModel;
import model.GameModelImpl;
import model.Pair;
import view.SceneLoader;
import view.View;
import view.controllers.GameViewController;
import view.controllers.MainMenuController;
import view.controllers.SceneController;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class PacManApp extends Application {

    @Override
    public void start(final Stage stage) throws Exception {

//        final View view = new JavaFXView(stage);
//        final GameModel model = new GameModelImpl();
//        final ViewObserver controller = new ControllerImpl(model, view);
//        view.launch(controller);
//        stage.setTitle("Pac-Man");
//        stage.show();
//        /*GameViewController test = GameViewController(stage);*/
//        stage.setFullScreen(true);
        Pair<Scene, SceneController> gameScene = SceneLoader.loadScene("game");
        stage.setTitle("PacMan");
        stage.setScene(gameScene.getX());
        stage.show();
        stage.setFullScreen(true);
        stage.setResizable(false);
        gameScene.getY().init(null, null);
    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
