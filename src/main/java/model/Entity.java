package model;

/**
 * This interface represents a generic game entity (ghosts, walls, pills, etc.).
 */
public interface Entity {
    /**
     * @return the position of the entity
     */
    Pair<Integer, Integer> getPosition();
}
