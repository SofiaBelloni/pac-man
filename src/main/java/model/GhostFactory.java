package model;

/**
 * A factory for creating Ghost objects.
 */
public interface GhostFactory{
    /**
     * Blinky.
     *
     * @return the ghost
     */
    Ghost blinky();
    /**
     * Pinky.
     *
     * @return the ghost
     */
    Ghost pinky();
    /**
     * Inky.
     *
     * @param blinky the blinky
     * @return the ghost
     */
    Ghost inky(Ghost blinky);
    /**
     * Clyde.
     *
     * @return the ghost
     */
    Ghost clyde();
}
