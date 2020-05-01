package view.controllers;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import view.View;

/**
 * This represent the Abstract Class that define different Menu scenes.
 */
public abstract class SceneController {

    private Controller controller;
    private View view;

    /**
     * Method that initialized the controller and the view.
     * @param controller
     *      the controller of the game.
     * @param view 
     *      the controller of the view.
     */
    public void init(final Controller controller, final View view) {
        this.controller = controller;
        this.view = view;
    }

    /**
     * Method getter of the Controller.
     * @return
     *      the controller.
     */
    protected Controller getController() {
        return this.controller;
    }

    /**
     * Method getter of the View.
     * @return
     *      the view.
     */
    protected View getView() {
        return this.view;
    }
    /**
     * Method that update the Scene.
     */
    public void render() {
    }

    /**
     * Event handler for when a key is pressed in this scene.
     * @param event
     *      the information about the event.
     */
    @FXML
    public void onKeyPressed(final KeyEvent event) {
        // Left empty to let subclasses free of not overriding this method.
    }
}
