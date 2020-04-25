package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

abstract class TextureLoader {

    private static final String TEXTURE_PATH = "textures";
    private static final String SEPARATOR = "/";
    private static final String FILE_TYPE = ".png";

    public static List<Image> loadAnimatedSprite(final String textureName, final int size) {
        final List<Image> frames = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            frames.add(new Image(TEXTURE_PATH 
                    + SEPARATOR 
                    + textureName 
                    + SEPARATOR 
                    + textureName + i + FILE_TYPE));
        }
        return frames;
    }
    
    public static Image loadTexture(final String textureName) {
        return new Image(TEXTURE_PATH 
                + SEPARATOR 
                + textureName 
                + SEPARATOR 
                + textureName + FILE_TYPE);
    }
}
