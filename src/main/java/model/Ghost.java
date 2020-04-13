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
     * Gets the name.
     *
     * @return the name
     */
    Ghosts getName();

    /**
     * Checks if blinky is dead.
     *
     */
    void blinkyIsDead();

}
