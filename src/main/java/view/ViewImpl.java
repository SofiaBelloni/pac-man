package view;


import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ViewImpl extends Application implements View {

    private Stage stage;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.stage = primaryStage;
    }

    @Override
    public void setVisible() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setScene(final Scene scene) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setController(final Controller controller) {
        // TODO Auto-generated method stub
    }

    @Override
    public final void close() {
        this.stage.close();
    }
}
