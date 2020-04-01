package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class BraveAbstractBehaviour implements BraveBehaviour {

    private boolean isPathFound;
    private int i;
    private int j;
    private final Set<Pair<Integer, Integer>> setWall;
    private final Map<Pair<Integer, Integer>, Integer> map;
    protected final int xMap;
    protected final int yMap;
    protected Directions currentDirection;
    protected Pair<Integer, Integer> currentPosition;
    protected Pair<Integer, Integer> relaxTarget;
    protected Pair<Integer, Integer> up;
    protected Pair<Integer, Integer> down;
    protected Pair<Integer, Integer> left;
    protected Pair<Integer, Integer> right;

    public BraveAbstractBehaviour(final Set<Pair<Integer, Integer>> setWall, int xMap, int yMap) {
        this.map = new HashMap<>();
        this.setWall = setWall;
        this.xMap = xMap;
        this.yMap = yMap;
        this.j = 0;
        this.isPathFound = false;
        this.currentDirection = Directions.UP;
    }

    protected void setAdj(Pair<Integer, Integer> position) {
        up = new Pair<>(position.getX(), position.getY() + 1);
        down = new Pair<>(position.getX(), position.getY() - 1);
        left = new Pair<>(position.getX() - 1, position.getY());
        right = new Pair<>(position.getX() + 1, position.getY());
    }

    @Override
    public void chase(Pair<Integer, Integer> currentPosition, PacMan pacMan, Directions dir,
            Optional<Pair<Integer, Integer>> blinkyPosition) {
        // TODO Auto-generated method stub

    }

    @Override
    public void relax(Pair<Integer, Integer> currentPosition, Pair<Integer, Integer> targetPosition, Directions dir) {
        if (!isPathFound) {
            findPath(currentPosition, targetPosition, dir);
        }
        j++;
        move(currentPosition, targetPosition, j);
    }

    @Override
    public Directions getNewDirection() {
        return newDirection;
    }
    
    public Pair<Integer, Integer> getNewPosition() {
        return newPosition;
    }
    
    protected void findPath(Pair<Integer, Integer> currentPosition, Pair<Integer, Integer> targetPosition, Directions dir) {
        this.isPathFound = false;
        i = 0;
        for (int k = 0; k <= xMap; k++) {
            for (int j = 0; j <= yMap; j++) {
                map.put(new Pair<>(k,j), 10000);
            }
        }
        map.put(currentPosition, i++);
        setAdj(currentPosition);
        if (up.getY()<=yMap && !setWall.contains(up) && !dir.equals(Directions.DOWN)) {
            map.put(up, i);
        }
        if (down.getY()>=0 && !setWall.contains(down) && !dir.equals(Directions.UP)) {
            map.put(down, i);
        }
        if (right.getX()<=xMap && !setWall.contains(right) && !dir.equals(Directions.LEFT)) {
            map.put(right, i);
        }
        if (left.getX()>=0 && !setWall.contains(left) && !dir.equals(Directions.RIGHT)) {
            map.put(left, i);
        }
        if (map.get(targetPosition)<9000) {
            isPathFound=true;
        }
        while (!isPathFound) {
            for (Pair<Integer, Integer> p : map.keySet()) {
                if (map.get(p).equals(i)) {
                    setAdj(p);
                    if (((up.getY()<=yMap && !setWall.contains(up) && i < map.get(up)))) {
                        map.put(up, i + 1);
                        
                    }
                    if (((right.getX()<=xMap && !setWall.contains(right) && i < map.get(right)))) {
                        map.put(right, i + 1);
                        
                    } 
                    if (((left.getX()>=0 && !setWall.contains(left) && i < map.get(left)))) {
                        map.put(left, i + 1);
                        
                    } 
                    if (((down.getY()>=0 && !setWall.contains(down) && i < map.get(down)))) {
                        map.put(down, i + 1);
                        
                    }
                    if (map.get(targetPosition)<900) {
                        isPathFound=true;
                    }
                }
            }
            i++;
        }
    }
    
    protected void move(Pair<Integer, Integer> currentPosition, Pair<Integer, Integer> targetPosition, int counter) {
        newPosition = targetPosition;
        int i= map.get(newPosition);
        while (i > counter) {
            setAdj(newPosition);
            if (up.getY()<=yMap && !setWall.contains(up) && map.get(up).equals(i-1)) {
                newPosition = up;
                i = map.get(newPosition);
            } else if (right.getX()<=xMap && !setWall.contains(right) && map.get(right).equals(i-1)) {
                newPosition = right;
                i = map.get(newPosition);
            } else if (down.getY()>=0 && !setWall.contains(down) && map.get(down).equals(i-1)) {
                newPosition = down;
                i = map.get(newPosition);
            } else if (left.getY()>=0 && !setWall.contains(left) && map.get(left).equals(i-1)) {
                newPosition = left;
                i = map.get(newPosition);
            } 
        }
        setAdj(newPosition);
        if (currentPosition.equals(up)) {
            newDirection=Directions.DOWN;
        } else if (currentPosition.equals(right)) {
            newDirection=Directions.LEFT;
        } else if (currentPosition.equals(down)) {
            newDirection=Directions.UP;
        } else {
            newDirection=Directions.RIGHT;
        }
    }
    
}
