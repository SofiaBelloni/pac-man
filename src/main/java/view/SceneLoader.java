package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import utils.Pair;
import utils.PairImpl;
import view.controllers.SceneController;

public abstract class SceneLoader {
    private static final String SCENE_PATH = "layouts/";
    private static final String FILE_EXTENSION = ".fxml";

    public static final Pair<Scene, SceneController> loadScene(final String sceneName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClassLoader.getSystemResource(SCENE_PATH
                + sceneName
                + FILE_EXTENSION));
        return new PairImpl<Scene, SceneController>(
        new  Scene(loader.load()),
        loader.getController());
    }
}
