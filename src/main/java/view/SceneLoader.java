package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneLoader {
    private static final String LAYOUT_PATH = "layout/";
    private static final String FILE_EXTENSION = ".fxml";
    private final String sceneName;

    public SceneLoader(final String sceneName) {
        this.sceneName = sceneName;
    }

    public final Scene getScene() throws IOException {
        final String path = LAYOUT_PATH + sceneName + FILE_EXTENSION;
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource(path));
        return new  Scene(root);
    }
}
