package model;

import utils.Pair;

/**
 * This interface represents a generic game entity (ghosts, walls, pills, etc.).
 */
public interface Entity {
    /**
     * @return the position of the entity
     */
    Pair<Integer, Integer> getPosition();
    /**
     * Move the entity to the next position.
     */
    void nextPosition();
    /**
     * PacMan return to the startPosition.
     */
    void returnToStartPosition();
}
