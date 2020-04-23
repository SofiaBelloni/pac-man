package view;

import java.util.Optional;

import javafx.scene.canvas.Canvas;

public class SpritesFactory {
    
    public SpritesFactory() {
    }
    
    public final AnimatedSprite pacMan(final Canvas canvas) {
        return new AnimatedSprite(
                TextureLoader.loadAnimatedSprite("pac_man", 4), Optional.empty(), canvas);
    }
    
    public final AnimatedSprite blinky(final Canvas canvas) {
        return new AnimatedSprite(
                TextureLoader.loadAnimatedSprite("blinky", 4), Optional.empty(), canvas);
    }
}
