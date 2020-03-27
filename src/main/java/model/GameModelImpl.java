package model;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class GameModelImpl implements GameModel {

    private static final int XMAPSIZE = 40;

    private static final int YMAPSIZE = 40;

    private static final int PACMANLIVES = 3;

    private final Set<Ghost> ghosts;
    private final PacMan pacMan;
    private final GameMap maze;
    private int scores;
    private int levelNumber;
    private int levelTime;

    public GameModelImpl() {
        this.maze = new GameMapImpl(20, 40, null, null, null);
        this.ghosts = new HashSet<>();
        this.pacMan = new PacManImpl.Builder()
                            .currentDirection(Directions.LEFT)
                            .mapSize(XMAPSIZE, YMAPSIZE)
                            .lives(PACMANLIVES)
                            .noWalls(null)
                            .startPosition(null)
                            .build();

        this.levelNumber = 1;
        this.levelTime = 0;
        this.scores = 0;
    }

    @Override
    public final void setPacManDirection(final Directions direction) {
        this.pacMan.setDirection(direction);
    }

    @Override
    public final Map<Ghosts, Set<Pair<Integer, Integer>>> getGhostsPositions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final void moveEntitiesNextPosition() {
        this.pacMan.nextPosition();
        this.ghosts.forEach(x -> x.nextPosition(this.pacMan.getPosition()));
    }

    @Override
    public final void decLevelTime() {
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
        return this.maze.getWallsPositions();
    }

    @Override
    public final Set<Pair<Integer, Integer>> getPillsPositions() {
        return this.maze.getPillsPositions();
    }

    @Override
    public final void nextLevel() {
        this.levelNumber = this.levelNumber + 1;
        this.levelTime = 0;
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
