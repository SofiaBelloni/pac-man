package view;

import java.util.List;
import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class AnimatedSprite {
    
    private static final double DEFAULT_DURATION = 0.100;
    private static final int DEFAULT_SPEED = 5;
    private static final int SPRITE_SIZE = 48;
    
    private final List<Image> frames;
    private final GraphicsContext gc;
    private final double duration;
    private int speedX;
    private int speedY;
    private int x;
    private int y;
    
    public AnimatedSprite(final List<Image> frames, final Optional<Double> duration, final Canvas canvas ) {
        this.frames = frames;
        this.speedX = 0;
        this.speedY = 0;
        this.x = 0;
        this.y = 0;
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
                gc.clearRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
                x += speedX;
                y += speedY;
                gc.drawImage(getFrame(t), x, y, SPRITE_SIZE, SPRITE_SIZE);
                //gc.drawImage(getFrame(t), x, y);
            }
        }.start();
    }

    private Image getFrame(final double time) {
        int index = (int) ((time % (this.frames.size() * duration)) / duration);
        return this.frames.get(index);
    }
    
    public final void moveUp() {
        this.speedX = 0;
        this.speedY = -DEFAULT_SPEED;
    }
    
    public final void moveDown() {
        this.speedX = 0;
        this.speedY = DEFAULT_SPEED;
    }
    
    public final void moveLeft() {
        this.speedX = -DEFAULT_SPEED;
        this.speedY = 0;
    }
    
    public final void moveRight() {
        this.speedX = DEFAULT_SPEED;
        this.speedY = 0;
    }
}
