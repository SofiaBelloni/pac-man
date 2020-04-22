package view;

import java.util.Map;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.action.ActionComponent;
import com.almasb.fxgl.input.UserAction;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class GameViewFXGL extends GameApplication {

    private static final int PAC_MAN_SIZE = 40;
    private static final int GHOST_SIZE = 40;
    private static final int WALL_SIZE = 40;
    private static final int PILL_SIZE = 20;
    private static final int SPEED = 1;
    
    private Entity pacMan;
    private Entity inky;
    private Entity pinky;
    private Entity blinky;
    private Entity clyde;

    @Override
    protected final void initSettings(final GameSettings settings) {
        settings.setTitle("Pac-Man");
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
    }

    @Override
    protected final void initInput() {
        FXGL.getInput().addAction(new UserAction("Up 1") {
            @Override
            protected void onAction() {
                //controller.moveUp();
                pacMan.getComponent(PacManAnimationFXGL.class).moveUp();
            }
        }, KeyCode.W);

        FXGL.getInput().addAction(new UserAction("Down 1") {
            @Override
            protected void onAction() {
                //controller.moveDown();
                pacMan.getComponent(PacManAnimationFXGL.class).moveDown();
            }
        }, KeyCode.S);

        FXGL.getInput().addAction(new UserAction("Right 1") {
            @Override
            protected void onAction() {
                //controller.moveRight();
                pacMan.getComponent(PacManAnimationFXGL.class).moveRight();
            }
        }, KeyCode.D);

        FXGL.getInput().addAction(new UserAction("Left 1") {
            @Override
            protected void onAction() {
                //controller.moveLeft();
                pacMan.getComponent(PacManAnimationFXGL.class).moveLeft();
            }
        }, KeyCode.A);
    }

    @Override
    protected final void initGameVars(final Map<String, Object> vars) {
        vars.put("scores", 0); //controller.getscores();
        vars.put("level", 0); //controller.getLevel();
    }

    @Override
    protected final void initGame() {
        this.pacMan = spawnPacMan(0, 0);
        this.blinky = spawnGhost(100, 0,"inky/inky1.png");
        this.inky = spawnGhost(150, 100, "clyde/clyde1.png");
        this.clyde = spawnGhost(0, 100, "pinky/pinky1.png");
        this.pinky = spawnGhost(100, 150, "blinky/blinky1.png");
    }

    private Entity spawnPacMan(final double x, final double y) {
        return FXGL.entityBuilder()
                .at(x, y)
                //.viewWithBBox(new Rectangle(PAC_MAN_SIZE, PAC_MAN_SIZE))
                //.with(new AnimationComponent())
                .with(new PacManAnimationFXGL())
                .buildAndAttach();
    }

    private Entity spawnGhost(final double x, final double y, final String viewPath) {
        return FXGL.entityBuilder()
                .at(x, y)
                //.viewWithBBox(new Rectangle(GHOST_SIZE, GHOST_SIZE))
                .view(viewPath)
                .scale(0.5, 0.5)
                .with("velocity", new Point2D(SPEED, 0))
                .buildAndAttach();
    }

    @Override
    protected final void initUI() {
        Text textScore = FXGL.getUIFactoryService().newText("prova");
        Text textLevel = FXGL.getUIFactoryService().newText("prova2");
        textScore.setTranslateX(100);
        textScore.setTranslateY(100);
        textLevel.setTranslateX(0);
        textLevel.setTranslateY(0);
        FXGL.getGameScene().addUINodes(textScore, textLevel);
    }

    @Override
    protected final void onUpdate(final double tpf) {
        this.updateVelocity(this.blinky);
        this.updateVelocity(this.inky);
        this.updateVelocity(this.clyde);
        this.updateVelocity(this.pinky);
    }

    private void updateVelocity(final Entity entity) {
        Point2D velocity = entity.getObject("velocity");
        entity.translate(velocity);
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
