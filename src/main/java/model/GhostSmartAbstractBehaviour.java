package model;

import static utils.Directions.DOWN;
import static utils.Directions.LEFT;
import static utils.Directions.RIGHT;
import static utils.Directions.UP;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.Directions;
import utils.Pair;
import utils.PairImpl;

public abstract class GhostSmartAbstractBehaviour extends GhostAbstractBehaviour implements GhostSmartBehaviour {

    private static final int UPPERBOUND = 10_000;

    private final Map<Pair<Integer, Integer>, Integer> mapDijkstra;
    private final Set<Pair<Integer, Integer>> walls;
    private final int xMapSize;
    private final int yMapSize;
    private final PacMan pacMan;
    private final GhostBehaviour rBehaviour;
    private boolean isPathFound;
    private Pair<Integer, Integer> relaxTarget;
    private boolean isBlinkyDead;
    private boolean relaxed;
    private final Pair<Integer, Integer> outsideTarget;

    public GhostSmartAbstractBehaviour(final Set<Pair<Integer, Integer>> walls, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> startPosition) {
        super(xMapSize, yMapSize, startPosition, ghostHouse, walls);
        this.mapDijkstra = new HashMap<>();
        this.isPathFound = false;
        this.isBlinkyDead = false;
        this.relaxed = true;
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.walls = this.getWalls();
        this.pacMan = pacMan;
        this.outsideTarget = new PairImpl<>(startPosition.getX(), startPosition.getY() - 3);
        this.rBehaviour = new GhostRandomBehaviourImpl(walls, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setCurrentPosition(startPosition);
    }

    protected final void relax(final Ghosts name, final boolean eatable) {
        if (this.isInside() && (name.equals(Ghosts.OLDLEVEL) || eatable)) {
            this.relaxTarget = this.outsideTarget;
        }
        this.findPath(this.relaxTarget);
        this.move(this.relaxTarget);
        if (this.getCurrentPosition().equals(this.relaxTarget)) {
            this.relaxed = false;
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
        for (int x = 0; x < this.xMapSize; x++) {
            for (int y = 0; y < this.yMapSize; y++) {
                this.mapDijkstra.put(new PairImpl<>(x, y), UPPERBOUND);
            }
        }
        this.mapDijkstra.put(this.getCurrentPosition(), distance++);
        this.setAdj(this.getCurrentPosition());
        if (this.getAdj(UP).getY() >= 0 && !this.walls.contains(this.getAdj(UP)) && !this.getCurrentDirection().equals(DOWN)) {
            this.mapDijkstra.put(this.getAdj(UP), distance);
        }
        if (this.getAdj(DOWN).getY() < yMapSize && !this.walls.contains(this.getAdj(DOWN)) && !this.getCurrentDirection().equals(UP)) {
            this.mapDijkstra.put(this.getAdj(DOWN), distance);
        }
        if (this.getAdj(RIGHT).getX() < xMapSize && !this.walls.contains(this.getAdj(RIGHT)) && !this.getCurrentDirection().equals(LEFT)) {
            this.mapDijkstra.put(this.getAdj(RIGHT), distance);
        }
        if (this.getAdj(LEFT).getX() >= 0 && !this.walls.contains(this.getAdj(LEFT)) && !this.getCurrentDirection().equals(RIGHT)) {
            this.mapDijkstra.put(this.getAdj(LEFT), distance);
        }
        if (this.mapDijkstra.get(pair) < UPPERBOUND) {
            this.isPathFound = true;
        }
        while (!this.isPathFound) {
            for (final Pair<Integer, Integer> p : this.mapDijkstra.keySet()) {
                if (this.mapDijkstra.get(p).equals(distance)) {
                    this.setAdj(p);
                    if (this.getAdj(UP).getY() >= 0 && !this.walls.contains(this.getAdj(UP)) && distance < this.mapDijkstra.get(this.getAdj(UP))) {
                        this.mapDijkstra.put(this.getAdj(UP), distance + 1);
                    }
                    if (this.getAdj(RIGHT).getX() < xMapSize && !this.walls.contains(this.getAdj(RIGHT)) && distance < this.mapDijkstra.get(this.getAdj(RIGHT))) {
                        this.mapDijkstra.put(this.getAdj(RIGHT), distance + 1);
                    } 
                    if (this.getAdj(LEFT).getX() >= 0 && !this.walls.contains(this.getAdj(LEFT)) && distance < this.mapDijkstra.get(this.getAdj(LEFT))) {
                        this.mapDijkstra.put(this.getAdj(LEFT), distance + 1);
                    } 
                    if (this.getAdj(DOWN).getY() < yMapSize && !this.walls.contains(this.getAdj(DOWN)) && distance < this.mapDijkstra.get(this.getAdj(DOWN))) {
                        this.mapDijkstra.put(this.getAdj(DOWN), distance + 1);
                    }
                    if (this.mapDijkstra.get(pair) < UPPERBOUND) {
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
     */
    protected final void move(final Pair<Integer, Integer> targetPosition) {
        final Pair<Integer, Integer> lastPosition = this.getCurrentPosition();
        this.setCurrentPosition(targetPosition);
        int i = this.mapDijkstra.get(this.getCurrentPosition());
        while (i > 1) {
            setAdj(this.getCurrentPosition());
            if (this.getAdj(UP).getY() >= 0 && !this.walls.contains(this.getAdj(UP)) && this.mapDijkstra.get(this.getAdj(UP)).equals(i - 1)) {
                this.setCurrentPosition(this.getAdj(UP));
            } else if (this.getAdj(RIGHT).getX() < xMapSize && !this.walls.contains(this.getAdj(RIGHT)) && this.mapDijkstra.get(this.getAdj(RIGHT)).equals(i - 1)) {
                this.setCurrentPosition(this.getAdj(RIGHT));
            } else if (this.getAdj(DOWN).getY() < yMapSize && !this.walls.contains(this.getAdj(DOWN)) && this.mapDijkstra.get(this.getAdj(DOWN)).equals(i - 1)) {
                this.setCurrentPosition(this.getAdj(DOWN));
            } else if (this.getAdj(LEFT).getX() >= 0 && !this.walls.contains(this.getAdj(LEFT)) && this.mapDijkstra.get(this.getAdj(LEFT)).equals(i - 1)) {
                this.setCurrentPosition(this.getAdj(LEFT));
            } 
            i = this.mapDijkstra.get(this.getCurrentPosition());
        }
        this.setAdj(this.getCurrentPosition());
        if (lastPosition.equals(this.getAdj(UP))) {
            this.setCurrentDirection(DOWN);
        } else if (lastPosition.equals(this.getAdj(RIGHT))) {
            this.setCurrentDirection(LEFT);
        } else if (lastPosition.equals(this.getAdj(DOWN))) {
            this.setCurrentDirection(UP);
        } else {
            this.setCurrentDirection(RIGHT);
        }
    }

    /**
     * This method makes the ghost move without using Dijkstra algorithm in case he is stuck.
     * 
     * @return true if the ghost is stuck and can go in only one direction
     */
    protected final boolean moveIfStuck() {
        this.setAdj(this.getCurrentPosition());
        final Map<Directions, Pair<Integer, Integer>> mapAdj = new HashMap<>();
        mapAdj.put(UP, this.getAdj(UP));
        mapAdj.put(RIGHT, this.getAdj(RIGHT));
        mapAdj.put(DOWN, this.getAdj(DOWN));
        mapAdj.put(LEFT, this.getAdj(LEFT));
        mapAdj.remove(this.oppositeDirection(this.getCurrentDirection()));
        final Map<Directions, Pair<Integer, Integer>> mapAdjCopy = new HashMap<>(mapAdj);
        for (final Directions dir : mapAdj.keySet()) {
            if (this.walls.contains(this.getAdj(dir))) {
                mapAdjCopy.remove(dir);
            }
        }
        if (mapAdjCopy.size() == 1) {
            for (final Directions dir : mapAdjCopy.keySet()) {
                this.setCurrentPosition(this.getAdj(dir));
                this.setCurrentDirection(dir);
            }
            if (this.getCurrentPosition().equals(this.relaxTarget)) {
                this.relaxed = false;
            }
            return true;
        }
        return false;
    }

    @Override
    public final void setCurrentPosition(final Pair<Integer, Integer> newPosition) {
        super.setCurrentPosition(newPosition);
        this.rBehaviour.setCurrentPosition(newPosition);
    }

    @Override 
    public final void setCurrentDirection(final Directions newDirection) {
        super.setCurrentDirection(newDirection);
        this.rBehaviour.setCurrentDirection(newDirection);
    }

    @Override
    public final Pair<Integer, Integer> getRelaxTarget() {
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

    protected final PacMan getPacMan() {
        return this.pacMan;
    }

    protected final GhostBehaviour getRandomBehaviour() {
        return this.rBehaviour;
    }

    protected final boolean isBlinkyDead() {
        return this.isBlinkyDead;
    }

    @Override
    public final void setBlinkyDead() {
        this.isBlinkyDead = true;
    }

    @Override
    public final void returnHome(final Pair<Integer, Integer> newPosition) {
        super.returnHome(newPosition);
        this.rBehaviour.returnHome(newPosition);
        this.relaxed = true;
    }

    @Override
    public final void checkIfInside() {
        super.checkIfInside();
        this.rBehaviour.checkIfInside();
    }


    protected final boolean isRelaxed() {
        return this.relaxed;
    }

}
