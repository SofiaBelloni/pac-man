package model;

import java.util.Set;

/**
 * this class implements a generic Entity that can be moved.
 *
 */
public abstract class MobileEntityAbstractImpl implements MobileEntity {

    private Pair<Integer, Integer> position;
    private final Set<Pair<Integer, Integer>> noWalls;
    private final int xMapSize;
    private final int yMapSize;

    public MobileEntityAbstractImpl(final int xMapSize, final int yMapSize, final Pair<Integer, Integer> startPosition, final Set<Pair<Integer, Integer>> noWalls) {
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
}
