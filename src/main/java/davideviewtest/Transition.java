package davideviewtest;

import java.io.IOException;

import controller.Controller;
import javafx.animation.PathTransition;
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
    private void onRightPressed(final ActionEvent event) throws IOException {
        Ghosts ghost = Ghosts.BLINKY;
        Directions dir = Directions.RIGHT;
        blinky.setImage(new Image("textures/" + ghost.toString() + "/" + dir.toString() + ".png"));
        PathTransition p = new PathTransition();
        p.setNode(blinky);
        p.setDuration(Duration.seconds(0.5));
        if (dir.equals(Directions.RIGHT)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() * 3 / 2, blinky.getY() + blinky.getFitHeight() / 2));
            blinky.setX(blinky.getX() +  blinky.getFitWidth()); 
        } else   if (dir.equals(Directions.UP)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() / 2, blinky.getY() - blinky.getFitHeight()));
            blinky.setY(blinky.getY() -  blinky.getFitHeight() * 3 / 2); 
        } else   if (dir.equals(Directions.LEFT)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() - blinky.getFitWidth(), blinky.getY() + blinky.getFitHeight() / 2));
            blinky.setX(blinky.getX() -  blinky.getFitWidth() * 3 / 2); 
        } else {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() * 3 / 2));
            blinky.setY(blinky.getY() +  blinky.getFitHeight());
        }
        p.setCycleCount(1);
        p.play();
        
    }

    @FXML
    private void onLeftPressed(final ActionEvent event) throws IOException {
        Ghosts ghost = Ghosts.BLINKY;
        Directions dir = Directions.LEFT;
        blinky.setImage(new Image("textures/" + ghost.toString() + "/" + dir.toString() + ".png"));
        PathTransition p = new PathTransition();
        p.setNode(blinky);
        p.setDuration(Duration.seconds(0.5));
        if (dir.equals(Directions.RIGHT)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() * 3 / 2, blinky.getY() + blinky.getFitHeight() / 2));
            blinky.setX(blinky.getX() +  blinky.getFitWidth()); 
        } else   if (dir.equals(Directions.UP)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() / 2, blinky.getY() - blinky.getFitHeight()));
            blinky.setY(blinky.getY() -  blinky.getFitHeight() * 3 / 2); 
        } else   if (dir.equals(Directions.LEFT)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() - blinky.getFitWidth(), blinky.getY() + blinky.getFitHeight() / 2));
            blinky.setX(blinky.getX() -  blinky.getFitWidth() * 3 / 2); 
        } else {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() * 3 / 2));
            blinky.setY(blinky.getY() +  blinky.getFitHeight());
        }
        p.setCycleCount(1);
        p.play();
        
    }

    @FXML
    private void onUpPressed(final ActionEvent event) throws IOException {
        Ghosts ghost = Ghosts.BLINKY;
        Directions dir = Directions.UP;
        blinky.setImage(new Image("textures/" + ghost.toString() + "/" + dir.toString() + ".png"));
        PathTransition p = new PathTransition();
        p.setNode(blinky);
        p.setDuration(Duration.seconds(0.5));
        if (dir.equals(Directions.RIGHT)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() * 3 / 2, blinky.getY() + blinky.getFitHeight() / 2));
            blinky.setX(blinky.getX() +  blinky.getFitWidth()); 
        } else   if (dir.equals(Directions.UP)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() / 2, blinky.getY() - blinky.getFitHeight()));
            blinky.setY(blinky.getY() -  blinky.getFitHeight() * 3 / 2); 
        } else   if (dir.equals(Directions.LEFT)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() - blinky.getFitWidth(), blinky.getY() + blinky.getFitHeight() / 2));
            blinky.setX(blinky.getX() -  blinky.getFitWidth() * 3 / 2); 
        } else {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() * 3 / 2));
            blinky.setY(blinky.getY() +  blinky.getFitHeight());
        }
        p.setCycleCount(1);
        p.play();
    }

    @FXML
    private void onDownPressed(final ActionEvent event) throws IOException {
        Ghosts ghost = Ghosts.BLINKY;
        Directions dir = Directions.DOWN;
        boolean eatable = false;
        if (eatable) {
            blinky.setImage(new Image("textures/ghost/eatable.png"));
        } else {
            blinky.setImage(new Image("textures/" + ghost.toString() + "/" + dir.toString() + ".png"));
        }
        PathTransition p = new PathTransition();
        p.setNode(blinky);
        p.setDuration(Duration.seconds(0.5));
        if (dir.equals(Directions.RIGHT)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() * 3 / 2, blinky.getY() + blinky.getFitHeight() / 2));
            blinky.setX(blinky.getX() +  blinky.getFitWidth()); 
        } else   if (dir.equals(Directions.UP)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() / 2, blinky.getY() - blinky.getFitHeight()));
            blinky.setY(blinky.getY() -  blinky.getFitHeight() * 3 / 2); 
        } else   if (dir.equals(Directions.LEFT)) {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() - blinky.getFitWidth(), blinky.getY() + blinky.getFitHeight() / 2));
            blinky.setX(blinky.getX() -  blinky.getFitWidth() * 3 / 2); 
        } else {
            p.setPath(new Line(blinky.getX() + blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() / 2,
                    blinky.getX() +  blinky.getFitWidth() / 2, blinky.getY() + blinky.getFitHeight() * 3 / 2));
            blinky.setY(blinky.getY() +  blinky.getFitHeight());
        }
        p.setCycleCount(1);
        p.play();
    }
}
