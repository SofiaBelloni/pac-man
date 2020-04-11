package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class GameModelImpl implements GameModel {

    private static final int LEVEL_TIME = 60;
    private static final int PAC_MAN_LIVES = 3;

    private final Set<Ghost> ghosts;
    private final GhostFactory ghostFactory;
    private final PacMan pacMan;
    private final GameMap gameMap;
    private int scores;
    private int levelNumber;
    private int levelTime;

    public GameModelImpl() {
        GameMapFactory mapFactory = new GameMapFactory();
        this.gameMap = mapFactory.createMap(Optional.empty());
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
    public final Map<Ghosts, List<Pair<Integer, Integer>>> getGhostsPositions() {
        final Map<Ghosts, List<Pair<Integer, Integer>>> ghostsPositions = new HashMap<>();
        ghostsPositions.put(Ghosts.BLINKY, this.getGhostPositions(Ghosts.BLINKY));
        ghostsPositions.put(Ghosts.CLYDE, this.getGhostPositions(Ghosts.CLYDE));
        ghostsPositions.put(Ghosts.INKY, this.getGhostPositions(Ghosts.INKY));
        ghostsPositions.put(Ghosts.PINKY, this.getGhostPositions(Ghosts.PINKY));
        return ghostsPositions;
    }

    private List<Pair<Integer, Integer>> getGhostPositions(final Ghosts ghost) {
        final List<Pair<Integer, Integer>> positions = new ArrayList<>();
        this.ghosts.stream().filter(x -> x.getName().equals(ghost))
        .forEach(x -> positions.add(x.getPosition()));
        return positions;
    }

    @Override
    public final void moveEntitiesNextPosition() {
        this.pacMan.nextPosition();
        this.ghosts.forEach(x -> x.nextPosition(this.pacMan));
        if (this.ghosts.stream().anyMatch(x -> x.getPosition().equals(this.pacMan.getPosition()))) {
            this.pacMan.kill();
            this.ghosts.forEach(x -> x.returnHome());
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
        this.ghosts.forEach(x -> x.returnHome());
        this.gameMap.restorePills();
        this.pacMan.returnToStartPosition();
    }
}
