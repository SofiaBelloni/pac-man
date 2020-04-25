package view;

import java.util.Optional;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class SpritesFactory {
    
    public SpritesFactory() {
    }
    
    public final Image wall() {
        return TextureLoader.loadTexture("wall");
    }
    
    public final Image pill() {
        return TextureLoader.loadTexture("pill");
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
