package model;

/**
 * The Interface Ghost.
 */
public interface Ghost extends Entity {
    /**
     * Creates the Ghost.
     */
    void create();
    /**
     * Next position.
     *
     * @param pacMan
     */
    void nextPosition(PacMan pacMan);
    /**
     * Makes the Ghost return home.
     */
    void returnHome();
    /**
     * Sets the eatable.
     *
     * @param eatable the new eatable
     */
    void setEatable(boolean eatable);
    /**
     * Gets the position.
     *
     * @return the position
     */
    PairImpl<Integer, Integer> getPosition();
    /**
     * Checks if is eatable.
     *
     * @return true, if is eatable
     */
    boolean isEatable();

}
