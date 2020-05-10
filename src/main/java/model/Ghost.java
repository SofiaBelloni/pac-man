package model;

import utils.GhostUtils;

/**
 * The Interface Ghost.
 */
public interface Ghost extends Entity {

    /**
     * Creates the Ghost.
     */
    void create();

    /**
     * Checks if the ghost is eatable.
     *
     * @return true, if is eatable
     */
    boolean isEatable();

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
     *Notify Blinky's death.
     *
     */
    void blinkyIsDead();

    /**
     * Gets the id.
     *
     * @return the ghost id
     */
    int getId();

    /**
     * Gets the direction.
     *
     * @return the ghost direction
     */
    Directions getDirection();

    /**
     *
     * @return the ghostUtils
     */
    GhostUtils getMyUtils();

    /**
     * Sets the ghost name.
     *
     * @param name the new name
     */
    void setName(Ghosts name);

}
