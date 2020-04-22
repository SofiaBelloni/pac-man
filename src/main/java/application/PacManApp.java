package application;

import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameModel;
import model.GameModelImpl;
import view.View;
import view.cotrollers.GameViewController;

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
        
        stage.setTitle("Pac-Man");
        stage.setMaximized(true);
        stage.show();
        GameViewController test = new GameViewController(stage);
    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
