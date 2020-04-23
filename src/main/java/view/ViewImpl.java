package view;


import controller.Controller;
import javafx.scene.Scene;

public abstract class ViewImpl implements View {

    private Scene scene;

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
        this.scene = scene;
    }

    @Override
    public void setController(final Controller controller) {
        // TODO Auto-generated method stub
    }
}
