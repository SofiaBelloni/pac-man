package model;

import java.util.*;

import utils.Pair;

public class GameModelImpl implements GameModel {

    private static final int PAC_MAN_LIVES = 3;
    private static final int LEVEL_DURATION = 60;
    private static final int INVERTED_GAME_DURATION = 10;
    private final Set<Ghost> ghosts;
    private final GhostFactory ghostFactory;
    private final PacMan pacMan;
    private Optional<GameMap> gameMap = Optional.empty();
    private final LevelManager levelManager;

    public GameModelImpl() {
        if(this.gameMap.isEmpty()){
            throw new IllegalStateException();
        }
        this.levelManager = new LevelManagerImpl(LEVEL_DURATION,
                INVERTED_GAME_DURATION,
                this.gameMap.get().getPillsPositions().size() * this.gameMap.get().getPillScore());
        this.ghosts = new HashSet<>();
        this.pacMan = new PacManImpl.Builder()
                            .currentDirection(Directions.LEFT)
                            .mapSize(this.gameMap.get().getxMapSize(), this.gameMap.get().getxMapSize())
                            .lives(PAC_MAN_LIVES)
                            .noWalls(this.gameMap.get().getNoWallsPositions())
                            .startPosition(this.gameMap.get().getPacManStartPosition())
                            .build();
        this.ghostFactory = new GhostFactoryImpl.Builder()
                .walls(this.gameMap.get().getWallsPositions())
                .ghostHouse(this.gameMap.get().getGhostHousePosition())
                .pacMan(pacMan)
                .mapSize(this.gameMap.get().getxMapSize(), this.gameMap.get().getyMapSize())
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
            blinky.create();
            ghost = this.ghostFactory.inky(blinky);
            this.ghosts.add(blinky);
        } else {
            ghost = this.ghostFactory.clyde();
        }
        ghost.create();
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
        this.ghosts.forEach(x -> x.nextPosition());
        if (this.checkPacmanGhostCollision()) {
            if (this.levelManager.isGameInverted()) {
                this.ghosts.removeIf(x ->
                x.getPosition().equals(this.pacMan.getPosition()));
            } else {
                this.pacMan.kill();
                this.ghosts.forEach(x -> x.returnToStartPosition());
            }
        } else {
            if (this.checkPillCollision()) {
                final boolean oldIsGameInverted = this.levelManager.isGameInverted();
                this.levelManager.incScores(this.gameMap.get().getPillScore());
                if (!oldIsGameInverted && this.levelManager.isGameInverted()) {
                    this.ghosts.forEach(x -> x.setEatable(true));
                }
            }
        }
    }

    private boolean checkPillCollision() {
        return this.gameMap.get().isPill(this.getPacManPosition());
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
        return this.gameMap.get().getWallsPositions();
    }

    @Override
    public final Set<Pair<Integer, Integer>> getPillsPositions() {
        return this.gameMap.get().getPillsPositions();
    }

    @Override
    public final int getLevelNumber() {
        return this.levelManager.getLevelNumber();
    }

    @Override
    public final int getLevelTime() {
        return this.levelManager.getLevelTime();
    }

    public void setGameMap(final GameMap gameMap){
        if (this.gameMap.isEmpty()){
            this.gameMap = Optional.of(gameMap);
        }else{
            throw new IllegalStateException();
        }
    }

    private void nextLevel() {
        this.levelManager.nextLevel();
        this.ghosts.forEach(x -> x.returnToStartPosition());
        this.ghosts.forEach(x -> x.setEatable(false));
        this.gameMap.get().restorePills();
        this.pacMan.returnToStartPosition();
        this.createGhost(Ghosts.CLYDE);
        this.createGhost(Ghosts.INKY);
        this.createGhost(Ghosts.PINKY);
    }

    @Override
    public final Boolean isGameEnded() {
        return this.pacMan.getLives() == 0;
    }

    @Override
    public final int getxMapSize() {
        return this.gameMap.get().getxMapSize();
    }

    @Override
    public final int getyMapSize() {
        return this.gameMap.get().getyMapSize();
    }

    @Override
    public final Directions getPacManDirection() {
        return this.pacMan.getDirection();
    }
}
