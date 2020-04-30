package davideviewtest;

import java.io.IOException;

import controller.Controller;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.Directions;
import model.Ghosts;
import view.View;
import view.controllers.SceneController;

public class Transition  extends SceneController {

    @FXML
    private ImageView blinky = new ImageView();

    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
    }


    @FXML
    private void onMove(final ActionEvent event) throws IOException {
        Ghosts ghost = Ghosts.BLINKY;
        Directions dir = Directions.DOWN;
        boolean eatable = false;
        if (eatable) {
            blinky.setImage(new Image("textures/ghost/eatable.png"));
        } else {
            blinky.setImage(new Image("textures/" + ghost.toString() + "/" + dir.toString() + ".png"));
        }
        TranslateTransition p = new TranslateTransition();
        p.setNode(blinky);
        p.setDuration(Duration.seconds(0.5));
        if (dir.equals(Directions.RIGHT)) {
            p.setFromX(blinky.getLayoutX());
            p.setFromY(blinky.getLayoutY());
            p.setToX(blinky.getLayoutX() + 40);
            p.setToY(blinky.getLayoutY());
        }
        p.setCycleCount(1);
        p.play();
    }
}
