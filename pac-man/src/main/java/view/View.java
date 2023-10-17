package view;

import controller.Controller;
/**
 * This interface represents the main class of the view.
 */
public interface View {
    /**
     * Sets the controller to be used and makes the view start.
     * @param controller  the {@link Controller} to use.
     */
    void setController(Controller controller);
    /**
     * Updates the view.
     */
    void render();
    /**
     * Sets the view Scene.
     * @param scene  the name of the scene to be loaded.
     * 
     */
    void setScene(GameScene scene);
}
