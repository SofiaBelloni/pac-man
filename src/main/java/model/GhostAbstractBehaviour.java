package model;

import java.util.HashMap;
import java.util.HashSet;
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
    private boolean inside;
    private final List<Pair<Integer, Integer>> ghostHouse;
    private final Set<Pair<Integer, Integer>> walls;

    public GhostAbstractBehaviour(final int xMapSize, final int yMapSize, final Pair<Integer, Integer> startPosition,
            final List<Pair<Integer, Integer>> ghostHouse, final Set<Pair<Integer, Integer>> walls) {
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.currentDirection = UP;
        this.currentPosition = startPosition;
        this.inside = true;
        this.ghostHouse = ghostHouse;
        this.walls = new HashSet<>(walls);
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

    /**
     * this method is designed for extension.
     */
    @Override 
    public void setCurrentDirection(final Directions newDirection) {
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

    /**
     * this method is designed for extension.
     */
    @Override
    public void checkIfInside(final boolean eatable) {
        if (this.inside && !this.ghostHouse.contains(this.getCurrentPosition())) {
            this.walls.addAll(this.ghostHouse);
            this.inside = false;
        }
    }

    /**
     * this method is designed for extension.
     */
    @Override
    public void returnHome(final Pair<Integer, Integer> newPosition) {
        this.inside = true;
        this.setCurrentPosition(newPosition);
        this.setCurrentDirection(UP);
        this.walls.removeAll(this.ghostHouse);
    }

    protected final Set<Pair<Integer, Integer>> getWalls() {
        return this.walls;
    }

    protected final boolean isInside() {
        return this.inside;
    }


}
