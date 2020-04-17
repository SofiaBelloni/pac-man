package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class GameModelImpl implements GameModel {

    private static final int PAC_MAN_LIVES = 3;
    private static final int LEVEL_DURATION = 60;
    private static final int INVERTED_GAME_DURATION = 10;
    private final Set<Ghost> ghosts;
    private final GhostFactory ghostFactory;
    private final PacMan pacMan;
    private final GameMap gameMap;
    private final LevelManager levelManager;

    public GameModelImpl() {
        final GameMapFactory mapFactory = new GameMapFactory();
        this.gameMap = mapFactory.createMap(Optional.empty());
        this.levelManager = new LevelManagerImpl(LEVEL_DURATION,
                INVERTED_GAME_DURATION,
                this.gameMap.getPillsPositions().size() * this.gameMap.getPillScore());
        this.ghosts = new HashSet<>();
        this.ghostFactory = new GhostFactoryImpl.Builder()
                            .walls(this.gameMap.getWallsPositions())
                            .ghostHouse(this.gameMap.getGhostHousePosition())
                            .mapSize(this.gameMap.getxMapSize(), this.gameMap.getyMapSize())
                            .build();
        this.pacMan = new PacManImpl.Builder()
                            .currentDirection(Directions.LEFT)
                            .mapSize(this.gameMap.getxMapSize(), this.gameMap.getxMapSize())
                            .lives(PAC_MAN_LIVES)
                            .noWalls(this.gameMap.getNoWallsPositions())
                            .startPosition(this.gameMap.getPacManStartPosition())
                            .build();
        this.createGhost(Ghosts.CLYDE);
        this.createGhost(Ghosts.INKY);
        this.createGhost(Ghosts.PINKY);
    }

    private void createGhost(final Ghosts ghostName) {
        Ghost ghost;
        if (ghostName.equals(Ghosts.BLINKY)) {
            ghost = this.ghostFactory.blinky();
        } else if (ghostName.equals(Ghosts.PINKY)) {
            ghost = this.ghostFactory.pinky();
        } else if (ghostName.equals(Ghosts.INKY)) {
            final Ghost blinky = this.ghostFactory.blinky();
            ghost = this.ghostFactory.inky(blinky);
            this.ghosts.add(blinky);
        } else {
            ghost = this.ghostFactory.clyde();
        }
        this.ghosts.add(ghost);
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
        if (this.checkPacmanGhostCollision()) {
            if (this.levelManager.isGameInverted()) {
                this.ghosts.removeIf(x ->
                x.getPosition().equals(this.pacMan.getPosition()));
            } else {
                this.pacMan.kill();
                this.ghosts.forEach(x -> x.returnHome());
            }
        } else {
            if (this.checkPillCollision()) {
                final boolean oldIsGameInverted = this.levelManager.isGameInverted();
                this.levelManager.incScores(this.gameMap.getPillScore());
                if (!oldIsGameInverted && this.levelManager.isGameInverted()) {
                    this.ghosts.forEach(x -> x.setEatable(true));
                }
            }
        }
    }

    private boolean checkPillCollision() {
        return this.gameMap.isPill(this.getPacManPosition());
    }

    private boolean checkPacmanGhostCollision() {
        return this.ghosts.stream().anyMatch(x -> x.getPosition().equals(this.pacMan.getPosition()));
    }

    @Override
    public final void decLevelTime() {
        if (this.levelManager.getLevelTime() == 0) {
            this.nextLevel();
        } else {
            final boolean oldIsGameInverted = this.levelManager.isGameInverted();
            this.levelManager.decLevelTime();
            if (oldIsGameInverted && !this.levelManager.isGameInverted()) {
                this.ghosts.forEach(x -> x.setEatable(false));
            }
        }
    }

    @Override
    public final int getScores() {
        return this.levelManager.getScores();
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
        return this.levelManager.getLevelNumber();
    }

    @Override
    public final int getLevelTime() {
        return this.levelManager.getLevelTime();
    }

    private void nextLevel() {
        this.levelManager.nextLevel();
        this.ghosts.forEach(x -> x.returnHome());
        this.ghosts.forEach(x -> x.setEatable(false));
        this.gameMap.restorePills();
        this.pacMan.returnToStartPosition();
        this.createGhost(Ghosts.CLYDE);
        this.createGhost(Ghosts.INKY);
        this.createGhost(Ghosts.PINKY);
    }

    @Override
    public final Boolean isGameEnded() {
        return this.pacMan.getLives() == 0;
    }
}
