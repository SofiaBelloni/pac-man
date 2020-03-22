package model;

/**
 * This interface represents an Entity that can be moved.
 */
public interface MobileEntity extends Entity {
    /*
     * Moves the Entity on the next position
     */
    int nextPosition();
}
