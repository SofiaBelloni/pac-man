package model;

import java.util.Set;

/**
 * this class implements a generic Entity that can be moved.
 *
 */
public abstract class EntityAbstractImpl implements Entity {

    private Pair<Integer, Integer> position;
    private final Set<Pair<Integer, Integer>> noWalls;
    private final int xMapSize;
    private final int yMapSize;

    public EntityAbstractImpl(final int xMapSize, final int yMapSize, final Pair<Integer, Integer> startPosition, final Set<Pair<Integer, Integer>> noWalls) {
        this.position = startPosition;
        this.noWalls = noWalls;
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
    }

    @Override
    public final Pair<Integer, Integer> getPosition() {
        return this.position;
    }
    protected final void setPosition(final Pair<Integer, Integer> position) {
        this.position = position;
    }

    protected final Set<Pair<Integer, Integer>> getNoWalls() {
        return noWalls;
    }

    protected final int getxMapSize() {
        return xMapSize;
    }

    protected final int getyMapSize() {
        return yMapSize;
    }

    protected Pair<Integer, Integer> convertToToroidal(final Pair<Integer, Integer> position) {
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
        return new Pair<Integer, Integer>(newX, newY);
    }

}
