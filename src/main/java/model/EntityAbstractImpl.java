
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

    public EntityAbstractImpl(final int xMapSize, final int yMapSize) {
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
    }


    protected final int getxMapSize() {
        return xMapSize;
    }

    protected final int getyMapSize() {
        return yMapSize;
    }

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
