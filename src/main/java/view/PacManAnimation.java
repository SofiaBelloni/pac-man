package view;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.geometry.Point2D;
import javafx.util.Duration;

public class PacManAnimation extends Component {
    private int speedX = 0;
    private int speedY = 0;
    private AnimatedTexture texture;
    private AnimationChannel dead;
    private AnimationChannel eat;
    private AnimationChannel idle;
    
    public PacManAnimation() {
        this.eat = new AnimationChannel(FXGL.image("pacman/pacman.png"),
                4,
                128,
                128,
                Duration.seconds(0.3),
                0,
                3);
        this.idle = new AnimationChannel(FXGL.image("pacman/pacman.png"),
                4,
                128,
                128,
                Duration.seconds(1),
                0,
                0);
        texture = new AnimatedTexture(this.idle);
        texture.setScaleX(0.5);
        texture.setScaleY(0.5);
    }
    
    @Override
    public final void onAdded() {
        //entity.getTransformComponent().setScaleOrigin(new Point2D(64, 64));
        entity.getTransformComponent().setRotationOrigin(new Point2D(64, 64));
        entity.getViewComponent().addChild(texture);
    }
    
    @Override
    public final void onUpdate(final double tpf) {
        entity.translateX(speedX * tpf);
        entity.translateY(speedY * tpf);
        
        if (this.speedX != 0 || this.speedY != 0) {
            if (this.texture.getAnimationChannel() == (this.idle)) {
                texture.loopAnimationChannel(eat);
            }
        }
    }
    
    public final void moveRight() {
        speedX = 150;
        speedY = 0;

        getEntity().setRotation(90);
    }

    public final void moveLeft() {
        speedX = -150;
        speedY = 0;

        getEntity().setRotation(-90);
    }
    
    public final void moveUp() {
        speedY = -150;
        speedX = 0;

        getEntity().setRotation(0);
    }
    
    public final void moveDown() {
        speedY = 150;
        speedX = 0;

        getEntity().setRotation(180);
    }
}
