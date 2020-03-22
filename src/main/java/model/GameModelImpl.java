package model;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class GameModelImpl implements GameModel {

    private final int xMapSize;
    private final int yMapSize;
    private final Set<Pair<Integer, Integer>> walls;
    private final Set<Pair<Integer, Integer>> pills;
    private final Set<Ghost> ghosts;
    private final PacMan pacMan;
    private int scores;
    private int levelNumber;
    private int levelTime;

    public GameModelImpl(final int xMapSize, final int yMapSize, final Set<Pair<Integer, Integer>> walls, final Set<Pair<Integer, Integer>> pills,
            final Pair<Integer, Integer> pacManInitialPosition, final Pair<Integer, Integer> ghostsHouse) {
        this.ghosts = null;
        this.pacMan = null;
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.walls = walls;
        this.pills = pills;
        this.levelNumber = 1;
        this.levelTime = 0;
        this.scores = 0;
    }

    @Override
    public final void setPacManDirection(final Directions direction) {
        this.pacMan.setDirection(Optional.of(direction));
    }

    @Override
    public final Map<Ghost, Pair<Integer, Integer>> getGhostsPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final void moveEntitiesNextPosition() {
        this.pacMan.nextPosition();
    }

    @Override
    public void addEntity(final Entities entity, final Pair<Integer, Integer> position) {
        // TODO Auto-generated method stub
    }

    @Override
    public final void incLevelTime() {
        this.levelTime = this.levelTime + 1;
    }

    @Override
    public final int getScores() {
        return this.scores;
    }

    @Override
    public final int getPacManLives() {
        return this.pacMan.getLives();
    }

    @Override
    public final Pair<Integer, Integer> getPacManPosition() {
        return this.pacMan.getPosition();
    }

    @Override
    public final Set<Pair<Integer, Integer>> getWallsPositions() {
        return Set.copyOf(this.walls);
    }

    @Override
    public final Set<Pair<Integer, Integer>> getPillsPositions() {
        return Set.copyOf(this.pills);
    }

    @Override
    public final void incLevelNumber() {
        this.levelNumber = this.levelNumber + 1;
    }

    @Override
    public final int getLevelNumber() {
        return this.levelNumber;
    }

    @Override
    public final int getLevelTime() {
        return this.levelTime;
    }
}
