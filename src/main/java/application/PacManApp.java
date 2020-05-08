package application;

import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameModel;
import model.GameModelImpl;
import view.View;
import view.ViewImpl;

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
