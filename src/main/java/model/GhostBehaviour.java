package model;

import utils.Directions;
import utils.Pair;

/**
 * The Interface GhostBehaviour.
 */
public interface GhostBehaviour {

    /**
     * Gets the current direction.
     *
     * @return the current direction
     */
    Directions getCurrentDirection();

    /**
     * Sets the current direction.
     *
     * @param newDirection the new current direction
     */
    void setCurrentDirection(Directions newDirection);

    /**
     * Gets the current position.
     *
     * @return the current position
     */
    Pair<Integer, Integer> getCurrentPosition();

    /**
     * Sets the current position.
     *
     * @param newPosition the new position
     */
    void setCurrentPosition(Pair<Integer, Integer> newPosition);

    /**
     * Calculate the next position of the ghost.
     *
     * @param eatable the eatable
     * @param name the ghost name
     * @param timeToTurn the time to turn
     */
    void nextPosition(boolean eatable, boolean timeToTurn, Ghosts name);

    /**
     * Makes the ghosts return in ghostHouse.
     *
     * @param newPosition the new position
     */
    void returnHome(Pair<Integer, Integer> newPosition);

    /**
     * Checks if the ghost is inside the house.
     *
     */
    void checkIfInside();

}
