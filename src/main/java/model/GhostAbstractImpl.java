package model;

import java.util.Map;
import java.util.Set;

public abstract class GhostAbstractImpl implements Ghost {
    private static final int MAX = 20;
    private Map<Pair<Integer, Integer>, Integer> map;
    private Set<Pair<Integer, Integer>> setWall;
    protected Pair<Integer, Integer> target;
    private Pair<Integer, Integer> currentPosition;
    private Directions blinkyDir;
    private boolean isBlinkyRelaxed;

    public GhostAbstractImpl(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall) {
        currentPosition = new Pair<>(7, 6);
        blinkyDir = Directions.UP;
        isBlinkyRelaxed = true;
        this.map = map;
        this.setWall = setWall;
    }

    public Pair<Integer, Integer> getPosition() {
        return currentPosition;
    }

    public int nextPosition(Pair<Integer,Integer> pacManPosition) {
        for (Pair<Integer, Integer> p:map.keySet()) {
            map.put(p, 1000);
        }
            if (isBlinkyRelaxed) {
                isBlinkyRelaxed = relax();
                return 0;
            } else {
                findPath(currentPosition, pacManPosition);
            }
            if (currentPosition.equals(pacManPosition)) {
                return 1;
            }
            return 0;

    }
    public boolean relax() {
        findPath(currentPosition, target);
        if (currentPosition.equals(target)) {
            return false;
        }
        return true;
    }
    public void findPath(Pair<Integer,Integer> position,Pair<Integer,Integer> targetPosition) {
        if (position.equals(targetPosition)) {
            map.get(position);
            move(position);
            return;
        }
        if (position.equals(currentPosition)) {
            map.put(position, 0);
        }
        Pair<Integer, Integer> up = new Pair<>(position.getX(), position.getY() + 1);
        Pair<Integer, Integer> down = new Pair<>(position.getX(), position.getY() - 1);
        Pair<Integer, Integer> left = new Pair<>(position.getX() - 1, position.getY());
        Pair<Integer, Integer> right = new Pair<>(position.getX() + 1, position.getY());
        if (!setWall.contains(up) && up.getY() <= MAX && map.get(position) + 1 < map.get(up)) {
            map.put(up, map.get(position) + 1);
            findPath(up, targetPosition);
        }
        if (!setWall.contains(right) && right.getX() <= MAX && map.get(position) + 1 < map.get(right)) {
            map.put(right, map.get(position) + 1);
            findPath(right, targetPosition);
        }
        if (!setWall.contains(down) && down.getY() >= 0 && map.get(position) + 1 < map.get(down)) {
            map.put(down, map.get(position) + 1);
            findPath(down, targetPosition);
        }
        if (!setWall.contains(left) && left.getX() >= 0 && map.get(position) + 1 < map.get(left)) {
            map.put(left, map.get(position) + 1);
            findPath(left, targetPosition);
        }
    }
    public void move(Pair<Integer, Integer> appo) {

        Pair<Integer, Integer> up = new Pair<>(appo.getX(), appo.getY() + 1);
        Pair<Integer, Integer> down = new Pair<>(appo.getX(), appo.getY() - 1);
        Pair<Integer, Integer> left = new Pair<>(appo.getX() - 1, appo.getY());
        Pair<Integer, Integer> right = new Pair<>(appo.getX() + 1, appo.getY());
        if (map.get(appo) == 1) {
            currentPosition = appo;
            return;
        } else {
            if (!setWall.contains(up) && up.getY() <= MAX && map.get(up) == map.get(appo) - 1) {
                move(up);
            }
            if (!setWall.contains(down) && down.getY() >= 0 && map.get(down) == map.get(appo) - 1) {
                move(down);
            }
            if (!setWall.contains(left) && left.getX() >= 0 && map.get(left) == map.get(appo) - 1) {
                move(left);
            }
            if (!setWall.contains(right) && right.getX() <= MAX && map.get(right) == map.get(appo) - 1) {
                move(right);
            }
        }
    }

    @Override
    public boolean isEatable() {
        // TODO Auto-generated method stub
        return false;
    }
}

