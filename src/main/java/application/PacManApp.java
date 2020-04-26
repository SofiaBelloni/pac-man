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
import view.View;
import view.controllers.GameViewController;
import view.controllers.MainMenuController;

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
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/game.fxml"));
        Scene scene = new Scene(root, 1920, 1080);
        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
        stage.setResizable(false);
    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
