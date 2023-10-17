
package model;

import utils.Pair;
import utils.PairImpl;

/**
 * this class implements a generic Entity that can be moved.
 *
 */
public abstract class EntityAbstractImpl implements Entity {

    private final int xMapSize;
    private final int yMapSize;

    /**
     * Initializes default data for this entity.
     * @param xMapSize
     *      the x game map size.
     * @param yMapSize
     *      the y game map size.
     */
    public EntityAbstractImpl(final int xMapSize, final int yMapSize) {
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
    }

    /**
     * 
     * @return the x game map size.
     */
    protected final int getxMapSize() {
        return xMapSize;
    }

    /**
     * 
     * @return the y game map size.
     */
    protected final int getyMapSize() {
        return yMapSize;
    }
    /**
     * Method to move the entity in a toroidal world. if the entity want to move to a point outside the map this 
     * method return a position at the opposite of the map (where there is the tunnel)
     * @param position where the entity want to move
     * @return the correct position of the entity
     */
    protected final PairImpl<Integer, Integer> convertToToroidal(final Pair<Integer, Integer> position) {
        int newX = position.getX();
        int newY = position.getY();
        if (newX >= this.getxMapSize()) {
            newX = 0;
        }
        if (newY >= this.getyMapSize()) {
            newY = 0;
        }
        if (newX < 0) {
            newX = this.getxMapSize() - 1;
        }
        if (newY < 0) {
            newY = this.getyMapSize() - 1;
        }
        return new PairImpl<Integer, Integer>(newX, newY);
    }

}
