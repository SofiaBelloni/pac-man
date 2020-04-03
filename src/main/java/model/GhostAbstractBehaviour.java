package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public abstract class GhostAbstractBehaviour implements GhostBehaviour {

    private static final int UPPERBOUND = 10000;

    private boolean isPathFound;
    private int i;
    private int j;
    private final Set<PairImpl<Integer, Integer>> setWall;
    private final Map<PairImpl<Integer, Integer>, Integer> map;
    private PairImpl<Integer, Integer> relaxTarget;
    private PairImpl<Integer, Integer> up;
    private PairImpl<Integer, Integer> down;
    private PairImpl<Integer, Integer> left;
    private PairImpl<Integer, Integer> right;
    private final int xMapSize;
    private final int yMapSize;
    private Directions currentDirection;
    private PairImpl<Integer, Integer> currentPosition;

    public GhostAbstractBehaviour(final Set<PairImpl<Integer, Integer>> setWall, final int xMapSize, final int yMapSize) {
        this.map = new HashMap<>();
        this.setWall = setWall;
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.j = 0;
        this.isPathFound = false;
        this.currentDirection = Directions.UP;
    }

    private void setAdj(final Pair<Integer, Integer> position) {
        up = new PairImpl<>(position.getX(), position.getY() + 1);
        down = new PairImpl<>(position.getX(), position.getY() - 1);
        left = new PairImpl<>(position.getX() - 1, position.getY());
        right = new PairImpl<>(position.getX() + 1, position.getY());
    }

    protected final void findPath(final Pair<Integer, Integer> targetPosition) {
        this.isPathFound = false;
        this.i = 0;
        for (int x = 0; x < this.xMapSize; x++) {
            for (int y = 0; y < this.yMapSize; y++) {
                this.map.put(new PairImpl<>(x, y), UPPERBOUND);
            }
        }
        this.map.put(this.currentPosition, this.i++);
        this.setAdj(this.currentPosition);
        if (this.up.getY() < yMapSize && !this.setWall.contains(this.up) && !this.currentDirection.equals(Directions.DOWN)) {
            this.map.put(this.up, this.i);
        }
        if (this.down.getY() >= 0 && !this.setWall.contains(this.down) && !this.currentDirection.equals(Directions.UP)) {
            this.map.put(this.down, this.i);
        }
        if (this.right.getX() < xMapSize && !this.setWall.contains(this.right) && !this.currentDirection.equals(Directions.LEFT)) {
            this.map.put(this.right, this.i);
        }
        if (this.left.getX() >= 0 && !this.setWall.contains(this.left) && !this.currentDirection.equals(Directions.RIGHT)) {
            this.map.put(this.left, this.i);
        }
        if (this.map.get(targetPosition) < UPPERBOUND) {
            this.isPathFound = true;
        }
        while (!this.isPathFound) {
            for (Pair<Integer, Integer> p : this.map.keySet()) {
                if (this.map.get(p).equals(this.i)) {
                    this.setAdj(p);
                    if (this.up.getY() < yMapSize && !this.setWall.contains(this.up) && this.i < this.map.get(this.up)) {
                        this.map.put(this.up, this.i + 1);
                    }
                    if (this.right.getX() < xMapSize && !this.setWall.contains(this.right) && this.i < this.map.get(this.right)) {
                        this.map.put(this.right, this.i + 1);
                    } 
                    if (this.left.getX() >= 0 && !this.setWall.contains(this.left) && this.i < this.map.get(this.left)) {
                        this.map.put(this.left, this.i + 1);
                    } 
                    if (this.down.getY() >= 0 && !this.setWall.contains(this.down) && this.i < this.map.get(this.down)) {
                        this.map.put(this.down, this.i + 1);
                    }
                    if (this.map.get(targetPosition) < UPPERBOUND) {
                        this.isPathFound = true;
                    }
                }
            }
            this.i++;
        }
    }

    protected final void move(final PairImpl<Integer, Integer> targetPosition, final int counter) {
        Pair<Integer, Integer> lastPosition = this.currentPosition;
        this.currentPosition = targetPosition;
        int distance = this.map.get(this.currentPosition);
        while (distance > counter) {
            setAdj(this.currentPosition);
            if (this.up.getY() < yMapSize && !this.setWall.contains(this.up) && this.map.get(this.up).equals(distance - 1)) {
                this.currentPosition = this.up;
            } else if (this.right.getX() < xMapSize && !this.setWall.contains(this.right) && this.map.get(this.right).equals(distance - 1)) {
                this.currentPosition = this.right;
            } else if (this.down.getY() >= 0 && !this.setWall.contains(this.down) && this.map.get(this.down).equals(distance - 1)) {
                this.currentPosition = this.down;
            } else if (this.left.getX() >= 0 && !this.setWall.contains(this.left) && this.map.get(this.left).equals(distance - 1)) {
                this.currentPosition = this.left;
            } 
            distance = this.map.get(this.currentPosition);
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

    public final void runAway() {
        Pair<Integer, Integer> oldPosition = this.currentPosition;
        this.setAdj(this.currentPosition);
        Map<Directions, PairImpl<Integer, Integer>> map = new HashMap<>();
        map.put(Directions.UP, this.up);
        map.put(Directions.RIGHT, this.right);
        map.put(Directions.DOWN, this.down);
        map.put(Directions.LEFT, this.left);
        Map<Directions, PairImpl<Integer, Integer>> map2;
        while (this.currentPosition.equals(oldPosition)) {
            for (Directions dir : map.keySet()) {
                if (this.currentDirection.equals(dir)) {
                    map2 = new HashMap<>(map);
                    map2.remove(this.turnAround(dir));
                    List<Directions> list = new ArrayList<>(map2.keySet());
                    Random r = new Random();
                    Directions randomDir = list.get(r.nextInt(3));
                    if (!this.setWall.contains(map2.get(randomDir))) {
                        this.currentPosition = map2.get(randomDir);
                        this.currentDirection = randomDir;
                    }
                }
            }
        }
    }

    public final void relax() {
        if (!this.isPathFound) {
            this.findPath(this.relaxTarget);
        }
        this.j++;
        this.move(this.relaxTarget, this.j);
    }

    public final Directions turnAround(final Directions dir) {
        if (dir.equals(Directions.UP)) {
            return Directions.DOWN;
        } else if (dir.equals(Directions.RIGHT)) {
            return Directions.LEFT;
        } else if (dir.equals(Directions.DOWN)) {
            return Directions.UP;
        } else {
            return Directions.RIGHT;
        }
    }

    protected final PairImpl<Integer, Integer> getRelaxTarget() {
        return relaxTarget;
    }

    protected final void setRelaxTarget(final PairImpl<Integer, Integer> relaxTarget) {
        this.relaxTarget = relaxTarget;
    }

    protected final int getxMapSize() {
        return xMapSize;
    }

    protected final int getyMapSize() {
        return yMapSize;
    }

    public final Directions getCurrentDirection() {
        return currentDirection;
    }

    public final PairImpl<Integer, Integer> getCurrentPosition() {
        return currentPosition;
    }

    public final void setCurrentPosition(final PairImpl<Integer, Integer> newPosition) {
        this.currentPosition = newPosition;
    }

}
