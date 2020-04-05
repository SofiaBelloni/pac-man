package model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameModelImpl implements GameModel {

    private static final int X_MAP_SIZE = 28;
    private static final int Y_MAP_SIZE = 31;
    private static final int LEVEL_TIME = 60;
    private static final int PAC_MAN_LIVES = 3;

    private final Set<Ghost> ghosts;
    private final PacMan pacMan;
    private final GameMap gameMap;
    private int scores;
    private int levelNumber;
    private int levelTime;

    public GameModelImpl() {
        this.gameMap = new GameMapImpl.Builder().build();
        this.ghosts = new HashSet<>();
        this.pacMan = new PacManImpl.Builder()
                            .currentDirection(Directions.LEFT)
                            .mapSize(this.gameMap.getxMapSize(), this.gameMap.getxMapSize())
                            .lives(PAC_MAN_LIVES)
                            .noWalls(this.gameMap.getNoWallsPositions())
                            .startPosition(this.gameMap.getPacManStartPosition())
                            .build();

        this.levelNumber = 1;
        this.levelTime = LEVEL_TIME;
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
        this.ghosts.forEach(x -> x.nextPosition(this.pacMan));
        if (this.ghosts.stream().anyMatch(x -> x.getPosition().equals(this.pacMan.getPosition()))) {
            this.pacMan.kill();
        }
        if (this.gameMap.isPill(this.getPacManPosition())) {
            this.gameMap.removePill(this.pacMan.getPosition());
            this.scores = this.scores + this.gameMap.getPillScore();
        }
    }

    @Override
    public final void decLevelTime() {
        this.levelTime = this.levelTime - 1;
        if (this.levelTime == 0) {
            this.levelTime = GameModelImpl.LEVEL_TIME;
            this.nextLevel();
        }
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
        return this.gameMap.getWallsPositions();
    }

    @Override
    public final Set<Pair<Integer, Integer>> getPillsPositions() {
        return this.gameMap.getPillsPositions();
    }

    @Override
    public final int getLevelNumber() {
        return this.levelNumber;
    }

    @Override
    public final int getLevelTime() {
        return this.levelTime;
    }

    private void nextLevel() {
        this.levelNumber = this.levelNumber + 1;
    }
}
