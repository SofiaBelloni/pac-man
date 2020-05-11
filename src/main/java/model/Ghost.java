package model;

import utils.Directions;
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
     * 
     * @return the ghost id
     */
    int getId();

    /**
     * 
     * @return the ghost direction
     */
    Directions getDirection();

    GhostUtils getMyUtils();

    void setName(Ghosts name);

}
