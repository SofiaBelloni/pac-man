package davideviewtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("PacMan");
        Pane root = (Pane) FXMLLoader.load(ClassLoader.getSystemResource("layouts/transition.fxml"));
        this.stage.setScene(new Scene(root));
        this.stage.show();
    }
}
