package view;

import java.io.IOException;
import java.util.Locale;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import utils.Pair;
import utils.PairImpl;
import view.controllers.SceneController;

public abstract class SceneLoader {
    private static final String SCENE_PATH = "layouts/";
    private static final String FILE_EXTENSION = ".fxml";
    private static final double RESOLUTION_MULTIPLIER = 0.8;
    private static final double WIDTH = 16;
    private static final double HEIGHT = 9;

    public static final Pair<Scene, SceneController> loadScene(final GameScene sceneName) throws IOException {
        double width = Screen.getPrimary().getBounds().getWidth();
        double height = Screen.getPrimary().getBounds().getHeight();
        width = width * RESOLUTION_MULTIPLIER;
        height = width * HEIGHT / WIDTH;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClassLoader.getSystemResource(SCENE_PATH
                + sceneName.toString().toLowerCase(Locale.ROOT)
                + FILE_EXTENSION));
        return new PairImpl<Scene, SceneController>(
        new  Scene(loader.load(), width, height),
        loader.getController());
    }
}
