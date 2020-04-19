package view;

import controller.Controller;
import javafx.scene.Scene;

public interface View {
    /**
     * @param controller
     * set the controller of the view
     */
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
}
