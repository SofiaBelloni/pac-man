package model;

/**
 * A factory for creating Ghost objects.
 */
public interface GhostFactory {
    /**
     * Blinky.
     *
     *@param id the ghost id
     * @return the ghost
     */
    Ghost blinky(int id);
    /**
     * Pinky.
     *
     *@param id the ghost id
     * @return the ghost
     */
    Ghost pinky(int id);
    /**
     * Inky.
     *
     *@param id the ghost id
     * @param blinky 
     * @return the ghost
     */
    Ghost inky(Ghost blinky, int id);
    /**
     * Clyde.
     *
     *@param id the ghost id
     * @return the ghost
     */
    Ghost clyde(int id);
}
