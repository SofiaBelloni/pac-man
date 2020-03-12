package model;

/**
 * This interface represents an Entity that can be moved.
 */
public interface MobileEntity extends Entity {
    /**
     * @param position of the entity
     */
    void setPosition(Pair<Integer, Integer> position);

    /**
     * Set the mobile entity direction.
     * 
     * @param direction the new direction of the entity
     */
    void setDirection(Directions direction);

    /**
     * Set the mobile entity direction.
     * 
     * @return the direction of the entity
     */
    Directions getDirection();
}
