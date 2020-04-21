package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface View {
    void setController(Controller controller);
    /**
     * set the view to visible.
     */
    void setVisible();
    /**
     * update the view.
     */
    void render();
    /**
     * @param scene
     * set the view Scene
     */
    void setScene(Scene scene);
    /**
     * get the stage.
     */
    void close();
}
