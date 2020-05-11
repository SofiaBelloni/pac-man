package model;

import utils.Pair;

/**
 * This interface represents a generic game entity (ghosts, walls, pills, etc.).
 */
public interface Entity {
    /**
     * Calculates Entity next position.
     */
    void nextPosition();
    /**
     * Gets the position.
     *
     * @return the position of the entity
     */
    Pair<Integer, Integer> getPosition();
    /**
     * Return to the startPosition.
     */
    void returnToStartPosition();
}
