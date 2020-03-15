package model;

/**
 * This interface represents an Entity that can be moved.
 */
public interface MobileEntity extends Entity {
    /**
     * @param position of the entity
     */
    void setPosition(Pair<Integer, Integer> position);
}
