package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.Pair;
import utils.PairImpl;

import static model.Directions.UP;
import static model.Directions.DOWN;
import static model.Directions.RIGHT;
import static model.Directions.LEFT;

/**
 * this class implements a generic Ghost behaviour.
 *
 */
public abstract class GhostAbstractBehaviour implements GhostBehaviour {

    private final int xMapSize;
    private final int yMapSize;
    private Directions currentDirection;
    private Pair<Integer, Integer> currentPosition;
    private Map<Directions, Pair<Integer, Integer>> mapAdj;
    private boolean isInside;
    final List<Pair<Integer, Integer>> ghostHouse;

    public GhostAbstractBehaviour(final int xMapSize, final int yMapSize, final Pair<Integer, Integer> startPosition,
            final List<Pair<Integer, Integer>> ghostHouse) {
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.currentDirection = UP;
        this.currentPosition = startPosition;
        this.isInside = true;
        this.ghostHouse = ghostHouse;
    }

    protected final int getxMapSize() {
        return this.xMapSize;
    }

    protected final int getyMapSize() {
        return this.yMapSize;
    }

    @Override
    public final Directions getCurrentDirection() {
        return this.currentDirection;
    }

    @Override 
    public final void setCurrentDirection(final Directions newDirection) {
        this.currentDirection = newDirection;
    }

    @Override
    public final Pair<Integer, Integer> getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * this method is designed for extension.
     */
    @Override
    public void setCurrentPosition(final Pair<Integer, Integer> newPosition) {
        this.currentPosition = newPosition;
    }

    /**
     * Sets the adjacent position in the specified direction.
     * 
     * @param dir the direction of the adjacent position
     * @return the adjacent position
     */
    protected final Pair<Integer, Integer> getAdj(final Directions dir) {
       return mapAdj.get(dir);
    }

    /**
     * Sets the adjacent positions of the current position.
     * 
     * @param position
     */
    protected final void setAdj(final Pair<Integer, Integer> position) {
        this.mapAdj = new HashMap<>();
        this.mapAdj.put(UP, new PairImpl<>(position.getX(), position.getY() - 1));
        this.mapAdj.put(DOWN, new PairImpl<>(position.getX(), position.getY() + 1));
        this.mapAdj.put(RIGHT, new PairImpl<>(position.getX() + 1, position.getY()));
        this.mapAdj.put(LEFT, new PairImpl<>(position.getX() - 1, position.getY()));
    }

    protected final boolean checkIfInside(final Set<Pair<Integer, Integer>> setWall) {
        if (this.isInside && !this.ghostHouse.contains(this.getCurrentPosition())) {
            setWall.addAll(this.ghostHouse);
            this.isInside = false;
        }
        return this.isInside;
    }

    protected final void returnHome(final Pair<Integer, Integer> newPosition, final Set<Pair<Integer, Integer>> setWall) {
        this.isInside = true;
        this.setCurrentPosition(newPosition);
        this.setCurrentDirection(UP);
        setWall.removeAll(this.ghostHouse);
    }
}
