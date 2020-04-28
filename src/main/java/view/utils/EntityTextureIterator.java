package view.utils;

/**
 * An interface modeling a simple iterator of Strings.
 * Provides an infinite stream of Strings, cycling from the first to the last.
 */
public interface EntityTextureIterator {
    /**
     * @return a string containing the address of the image to be loaded.
     */
    String nextImage();

}
