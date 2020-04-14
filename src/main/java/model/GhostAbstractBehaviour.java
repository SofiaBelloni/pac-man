package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * this class implements a generic Ghost behaviour.
 *
 */
public abstract class GhostAbstractBehaviour implements GhostBehaviour {

    private static final int UPPERBOUND = 10_000;

    /**
     * counter which is used to subtract the distance of the target in order to 
     * use the findPath method only one time in relax method.
     */
    private int j;
    private final Set<Pair<Integer, Integer>> setWall;
    private final Map<Pair<Integer, Integer>, Integer> map;
    private boolean isPathFound;
    private Pair<Integer, Integer> startPosition;
    private Pair<Integer, Integer> relaxTarget;
    private Pair<Integer, Integer> up;
    private Pair<Integer, Integer> down;
    private Pair<Integer, Integer> left;
    private Pair<Integer, Integer> right;
    private final int xMapSize;
    private final int yMapSize;
    private Directions currentDirection;
    private Pair<Integer, Integer> currentPosition;
    private final List<Pair<Integer, Integer>> ghostHouse;

    public GhostAbstractBehaviour(final Set<Pair<Integer, Integer>> setWall, final Set<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize) {
        this.map = new HashMap<>();
        this.setWall = setWall;
        this.ghostHouse = new ArrayList<>(ghostHouse);
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.j = 0;
        this.isPathFound = false;
        this.currentDirection = Directions.UP;
    }

    /**
     * Sets the adjacent boxes of the current position.
     * 
     * @param position
     */
    private void setAdj(final Pair<Integer, Integer> position) {
        up = new PairImpl<>(position.getX(), position.getY() + 1);
        down = new PairImpl<>(position.getX(), position.getY() - 1);
        left = new PairImpl<>(position.getX() - 1, position.getY());
        right = new PairImpl<>(position.getX() + 1, position.getY());
    }

    /** 
     * @param dir
     * @return the opposite direction of dir
     */
    private Directions oppositeDir(final Directions dir) {
        if (dir.equals(Directions.UP)) {
            return Directions.DOWN;
        } else if (dir.equals(Directions.LEFT)) {
            return Directions.RIGHT;
        } else if (dir.equals(Directions.DOWN)) {
            return Directions.UP;
        } else {
            return Directions.LEFT;
        }
    }

    /**
     * This method implements a version of Dijkstra algorithm which is used to find the shortest path 
     * from 2 nodes(in this case the nodes are the positions).
     * 
     * @param pair
     */
    protected final void findPath(final Pair<Integer, Integer> pair) {
        int distance = 0;
        this.isPathFound = false;
        distance = 0;
        for (int x = 0; x < this.xMapSize; x++) {
            for (int y = 0; y < this.yMapSize; y++) {
                this.map.put(new PairImpl<>(x, y), UPPERBOUND);
            }
        }
        this.map.put(this.currentPosition, distance++);
        this.setAdj(this.currentPosition);
        if (this.up.getY() < yMapSize && !this.setWall.contains(this.up) && !this.currentDirection.equals(Directions.DOWN)) {
            this.map.put(this.up, distance);
        }
        if (this.down.getY() >= 0 && !this.setWall.contains(this.down) && !this.currentDirection.equals(Directions.UP)) {
            this.map.put(this.down, distance);
        }
        if (this.right.getX() < xMapSize && !this.setWall.contains(this.right) && !this.currentDirection.equals(Directions.LEFT)) {
            this.map.put(this.right, distance);
        }
        if (this.left.getX() >= 0 && !this.setWall.contains(this.left) && !this.currentDirection.equals(Directions.RIGHT)) {
            this.map.put(this.left, distance);
        }
        if (this.map.get(pair) < UPPERBOUND) {
            this.isPathFound = true;
        }
        while (!this.isPathFound) {
            for (final Pair<Integer, Integer> p : this.map.keySet()) {
                if (this.map.get(p).equals(distance)) {
                    this.setAdj(p);
                    if (this.up.getY() < yMapSize && !this.setWall.contains(this.up) && distance < this.map.get(this.up)) {
                        this.map.put(this.up, distance + 1);
                    }
                    if (this.right.getX() < xMapSize && !this.setWall.contains(this.right) && distance < this.map.get(this.right)) {
                        this.map.put(this.right, distance + 1);
                    } 
                    if (this.left.getX() >= 0 && !this.setWall.contains(this.left) && distance < this.map.get(this.left)) {
                        this.map.put(this.left, distance + 1);
                    } 
                    if (this.down.getY() >= 0 && !this.setWall.contains(this.down) && distance < this.map.get(this.down)) {
                        this.map.put(this.down, distance + 1);
                    }
                    if (this.map.get(pair) < UPPERBOUND) {
                        this.isPathFound = true;
                    }
                }
            }
            distance++;
        }
    }

    /**
     * This method moves the ghost in the position specified by the findPath method.
     * 
     * @param targetPosition
     * @param counter
     */
    protected final void move(final Pair<Integer, Integer> targetPosition, final int counter) {
        final Pair<Integer, Integer> lastPosition = this.currentPosition;
        this.currentPosition = targetPosition;
        int i = this.map.get(this.currentPosition);
        while (i > counter) {
            setAdj(this.currentPosition);
            if (this.up.getY() < yMapSize && !this.setWall.contains(this.up) && this.map.get(this.up).equals(i - 1)) {
                this.currentPosition = this.up;
            } else if (this.right.getX() < xMapSize && !this.setWall.contains(this.right) && this.map.get(this.right).equals(i - 1)) {
                this.currentPosition = this.right;
            } else if (this.down.getY() >= 0 && !this.setWall.contains(this.down) && this.map.get(this.down).equals(i - 1)) {
                this.currentPosition = this.down;
            } else if (this.left.getX() >= 0 && !this.setWall.contains(this.left) && this.map.get(this.left).equals(i - 1)) {
                this.currentPosition = this.left;
            } 
            i = this.map.get(this.currentPosition);
        }
        this.setAdj(this.currentPosition);
        if (lastPosition.equals(this.up)) {
            this.currentDirection = Directions.DOWN;
        } else if (lastPosition.equals(this.right)) {
            this.currentDirection = Directions.LEFT;
        } else if (lastPosition.equals(this.down)) {
            this.currentDirection = Directions.UP;
        } else {
            this.currentDirection = Directions.RIGHT;
        }
    }

    @Override
    public final void runAway() {
        final Random r = new Random();
        final Pair<Integer, Integer> oldPosition = this.currentPosition;
        this.setAdj(this.currentPosition);
        final Map<Directions, Pair<Integer, Integer>> map = new HashMap<>();
        Map<Directions, Pair<Integer, Integer>> map2;
        map.put(Directions.UP, this.up);
        map.put(Directions.RIGHT, this.right);
        map.put(Directions.DOWN, this.down);
        map.put(Directions.LEFT, this.left);
        while (this.currentPosition.equals(oldPosition)) {
            for (final Directions dir : map.keySet()) {
                if (this.currentDirection.equals(dir)) {
                    map2 = new HashMap<>(map);
                    map2.remove(this.oppositeDir(dir));
                    final List<Directions> list = new ArrayList<>(map2.keySet());
                    final Directions randomDir = list.get(r.nextInt(3));
                    if (!this.setWall.contains(map2.get(randomDir))) {
                        this.currentPosition = map2.get(randomDir);
                        this.currentDirection = randomDir;
                    }
                }
            }
        }
    }

    @Override
    public final void relax() {
        if (!this.isPathFound) {
            this.findPath(this.relaxTarget);
        }
        this.j++;
        this.move(this.relaxTarget, this.j);
    }

    /**
     * This method makes the ghost move without using Dijkstra algorithm in case he is stuck.
     * 
     * @return true if the ghost is stuck and can go in only one direction
     */
    protected final boolean moveIfStuck() {
        setAdj(this.currentPosition);
        if (this.currentDirection.equals(Directions.UP) 
                && this.setWall.contains(this.left) && this.setWall.contains(this.right)) {
            this.setCurrentPosition(this.up);
            return true;
        }
        if (this.currentDirection.equals(Directions.DOWN) 
                && this.setWall.contains(this.left) && this.setWall.contains(this.right)) {
            this.setCurrentPosition(this.down);
            return true;
        }
        if (this.currentDirection.equals(Directions.UP) 
                && this.setWall.contains(this.up) && this.setWall.contains(this.down)) {
            this.setCurrentPosition(this.left);
            return true;
        }
        if (this.currentDirection.equals(Directions.UP) 
                && this.setWall.contains(this.up) && this.setWall.contains(this.down)) {
            this.setCurrentPosition(this.right);
            return true;
        }
        return false;
    }

    @Override
    public final void turnAround() {
        this.setAdj(this.currentPosition);
        if (this.getCurrentDirection().equals(Directions.UP)) {
                this.setCurrentPosition(this.down);
                this.currentDirection = Directions.DOWN;
        } else if (this.getCurrentDirection().equals(Directions.RIGHT)) {
            this.setCurrentPosition(this.left);
            this.currentDirection = Directions.LEFT;
        } else if (this.getCurrentDirection().equals(Directions.DOWN)) {
            this.setCurrentPosition(this.up);
            this.currentDirection = Directions.UP;
        } else {
            this.setCurrentPosition(this.right);
            this.currentDirection = Directions.RIGHT;
        }
    }

    /**
     * 
     * @return the target of the Ghost in relax mode
     */
    protected final Pair<Integer, Integer> getRelaxTarget() {
        return relaxTarget;
    }

    /**
     * Sets the target of the Ghost in relax mode.
     * 
     * @param relaxTarget
     */
    protected final void setRelaxTarget(final Pair<Integer, Integer> relaxTarget) {
        this.relaxTarget = relaxTarget;
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
    public final Pair<Integer, Integer> getCurrentPosition() {
        return this.currentPosition;
    }

    @Override
    public final void setCurrentPosition(final Pair<Integer, Integer> right2) {
        this.currentPosition = right2;
    }

    @Override
    public final Pair<Integer, Integer> getStartPosition() {
        return this.startPosition;
    }

    protected final void setStartPosition(final Pair<Integer, Integer> startPosition) {
        this.currentPosition = startPosition;
        this.startPosition = startPosition;
    }

    protected final List<Pair<Integer, Integer>> getGhostHouse() {
        return this.ghostHouse;
    }

}
