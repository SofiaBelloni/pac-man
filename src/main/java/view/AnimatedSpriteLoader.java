package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

abstract class AnimatedSpriteLoader {

    private static final String SPRITES_PATH = "sprites";
    private static final String SEPARATOR = "/";
    private static final String FILE_TYPE = ".png";

    public static List<Image> loadAnimatedSprite(final String spriteName, final int size) {
        final List<Image> frames = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            frames.add(new Image(SPRITES_PATH 
                    + SEPARATOR 
                    + spriteName 
                    + SEPARATOR 
                    + spriteName + i + FILE_TYPE));
        }
        return frames;
    }
}
