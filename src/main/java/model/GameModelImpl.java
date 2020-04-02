package model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameModelImpl implements GameModel {

    private static final int X_MAP_SIZE = 40;

    private static final int Y_MAP_SIZE = 40;

    private static final int PAC_MAN_LIVES = 3;

    private final Set<Ghost> ghosts;
    private final PacMan pacMan;
    private final GameMap gameMap;
    private int scores;
    private int levelNumber;
    private int levelTime;

    public GameModelImpl() {
        this.gameMap = new GameMapImpl.Builder(X_MAP_SIZE, Y_MAP_SIZE).build();
        this.ghosts = new HashSet<>();
        this.pacMan = new PacManImpl.Builder()
                            .currentDirection(Directions.LEFT)
                            .mapSize(X_MAP_SIZE, Y_MAP_SIZE)
                            .lives(PAC_MAN_LIVES)
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
    public final Map<Ghosts, Set<PairImpl<Integer, Integer>>> getGhostsPositions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final void moveEntitiesNextPosition() {
        this.pacMan.nextPosition();
        this.ghosts.forEach(x -> x.nextPosition(this.pacMan));
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
    public final Set<PairImpl<Integer, Integer>> getWallsPositions() {
        return this.gameMap.getWallsPositions();
    }

    @Override
    public final Set<PairImpl<Integer, Integer>> getPillsPositions() {
        return this.gameMap.getPillsPositions();
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
