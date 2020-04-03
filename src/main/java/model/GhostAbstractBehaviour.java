package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public abstract class GhostAbstractBehaviour implements GhostBehaviour {

    private boolean isPathFound;
    private int i;
    private int j;
    private final Set<PairImpl<Integer, Integer>> setWall;
    private final Map<PairImpl<Integer, Integer>, Integer> map;
    protected final int xMap;
    protected final int yMap;
    protected Directions currentDirection;
    protected PairImpl<Integer, Integer> currentPosition;
    protected PairImpl<Integer, Integer> relaxTarget;
    protected PairImpl<Integer, Integer> up;
    protected PairImpl<Integer, Integer> down;
    protected PairImpl<Integer, Integer> left;
    protected PairImpl<Integer, Integer> right;

    public GhostAbstractBehaviour(final Set<PairImpl<Integer, Integer>> setWall, int xMap, int yMap) {
        this.map = new HashMap<>();
        this.setWall = setWall;
        this.xMap = xMap;
        this.yMap = yMap;
        this.j = 0;
        this.isPathFound = false;
        this.currentDirection = Directions.UP;
    }

    protected void setAdj(Pair<Integer, Integer> position) {
        up = new PairImpl<>(position.getX(), position.getY() + 1);
        down = new PairImpl<>(position.getX(), position.getY() - 1);
        left = new PairImpl<>(position.getX() - 1, position.getY());
        right = new PairImpl<>(position.getX() + 1, position.getY());
    }

    public void runAway() {
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

    public void relax() {
        if (!this.isPathFound) {
            this.findPath(this.relaxTarget);
        }
        this.j++;
        this.move(this.relaxTarget, this.j);
    }

    protected void findPath(Pair<Integer, Integer> targetPosition) {
        this.isPathFound = false;
        this.i = 0;
        for (int k = 0; k < this.xMap; k++) {
            for (int j = 0; j < this.yMap; j++) {
                this.map.put(new PairImpl<>(k, j), 10000);
            }
        }
        this.map.put(this.currentPosition, this.i++);
        this.setAdj(this.currentPosition);
        if (this.up.getY() < yMap && !this.setWall.contains(this.up) && !this.currentDirection.equals(Directions.DOWN)) {
            this.map.put(this.up, this.i);
        }
        if (this.down.getY() >= 0 && !this.setWall.contains(this.down) && !this.currentDirection.equals(Directions.UP)) {
            this.map.put(this.down, this.i);
        }
        if (this.right.getX() < xMap && !this.setWall.contains(this.right) && !this.currentDirection.equals(Directions.LEFT)) {
            this.map.put(this.right, this.i);
        }
        if (this.left.getX() >= 0 && !this.setWall.contains(this.left) && !this.currentDirection.equals(Directions.RIGHT)) {
            this.map.put(this.left, this.i);
        }
        if (this.map.get(targetPosition)<9000) {
            this.isPathFound = true;
        }
        while (!this.isPathFound) {
            for (Pair<Integer, Integer> p : this.map.keySet()) {
                if (this.map.get(p).equals(this.i)) {
                    this.setAdj(p);
                    if (this.up.getY() < yMap && !this.setWall.contains(this.up) && this.i < this.map.get(this.up)) {
                        this.map.put(this.up, this.i + 1);
                    }
                    if (this.right.getX() < xMap && !this.setWall.contains(this.right) && this.i < this.map.get(this.right)) {
                        this.map.put(this.right, this.i + 1);
                        
                    } 
                    if (this.left.getX() >= 0 && !this.setWall.contains(this.left) && this.i < this.map.get(this.left)) {
                        this.map.put(this.left, this.i + 1);
                        
                    } 
                    if (this.down.getY() >= 0 && !this.setWall.contains(this.down) && this.i < this.map.get(this.down)) {
                        this.map.put(this.down, this.i + 1);
                        
                    }
                    if (this.map.get(targetPosition) < 9000) {
                        this.isPathFound = true;
                    }
                }
            }
            this.i++;
        }
    }

    protected void move(PairImpl<Integer, Integer> targetPosition, int counter) {
        Pair<Integer, Integer> lastPosition = this.currentPosition;
        this.currentPosition = targetPosition;
        int distance = this.map.get(this.currentPosition);
        while (distance > counter) {
            setAdj(this.currentPosition);
            if (this.up.getY() < yMap && !this.setWall.contains(this.up) && this.map.get(this.up).equals(distance - 1)) {
                this.currentPosition = this.up;
            } else if (this.right.getX() < xMap && !this.setWall.contains(this.right) && this.map.get(this.right).equals(distance - 1)) {
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

    public PairImpl<Integer, Integer> getPosition() {
        return this.currentPosition;
    }

    public void setPosition(PairImpl<Integer, Integer> initialPosition) {
        this.currentPosition = initialPosition;
    }

    public Directions getDirection() {
        return this.currentDirection;
    }

    public Directions turnAround(Directions dir) {
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
}
