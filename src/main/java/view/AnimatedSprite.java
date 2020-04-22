package view;

import java.util.List;
import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AnimatedSprite {
    
    private static final double DEFAULT_DURATION = 0.100;
    private static final double DEFAULT_SPEED = 5;
    
    private final List<Image> frames;
    private final GraphicsContext gc;
    private final double duration;
    private double x;
    private double y;
    
    public AnimatedSprite(final List<Image> frames, final Optional<Double> duration, final Canvas canvas ) {
        this.frames = frames;
        this.x = 400;
        this.y = 400;
        this.gc = canvas.getGraphicsContext2D();
        if (duration.isEmpty()) {
            this.duration = DEFAULT_DURATION;
        } else {
            this.duration = duration.get();
        }
    }
    
    public final void startAnimation() {
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(final long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
//                double x = 232 + 128 * Math.cos(t);
//                double y = 232 + 128 * Math.sin(t);
                gc.drawImage(getFrame(t), x, y);
            }
        }.start();
    }

    private Image getFrame(final double time) {
        int index = (int) ((time % (this.frames.size() * duration)) / duration);
        return this.frames.get(index);
    }
    
    public final void moveUp() {
        this.x = 0;
        this.y = -DEFAULT_SPEED;
    }
    
    public final void moveDown() {
        this.x = 0;
        this.y = DEFAULT_SPEED;
    }
    
    public final void moveLeft() {
        this.x = -DEFAULT_SPEED;
        this.y = 0;
    }
    
    public final void moveRight() {
        this.x = DEFAULT_SPEED;
        this.y = 0;
    }
}
