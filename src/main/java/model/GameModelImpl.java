package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import utils.Pair;

public class GameModelImpl implements GameModel {

    private static final int PAC_MAN_LIVES = 3;
    private static final int LEVEL_DURATION = 60;
    private static final int INVERTED_GAME_DURATION = 10;
    private Set<Ghost> ghosts;
    private GhostFactory ghostFactory;
    private PacMan pacMan;
    private Optional<GameMap> gameMap = Optional.empty();
    private LevelManager levelManager;

    public GameModelImpl() { }

    @Override
    public final void setPacManDirection(final Directions direction) {
        this.checkGameEnded();
        this.pacMan.setDirection(direction);
    }

    @Override
    public final int getLevelDuration(){
        return  this.levelManager.getLevelDuration();
    }

    @Override
    public final Map<Integer, Pair<Integer, Integer>> getGhostsPositions() {
        final Map<Integer, Pair<Integer, Integer>> ghostsPositions = new HashMap<>();
        this.ghosts.forEach(x -> ghostsPositions.put(x.getId(), x.getPosition()));
        return ghostsPositions;
    }

    @Override
    public final Map<Integer, Ghosts> getGhostsTypes() {
        final Map<Integer, Ghosts> gostsTypes = new HashMap<>();
        this.ghosts.forEach(x -> gostsTypes.put(x.getId(), x.getName()));
        return gostsTypes;
    }

    @Override
    public final Map<Integer, Directions> getGhostsDirections() {
        final Map<Integer, Directions> ghostsDirections = new HashMap<>();
        this.ghosts.forEach(x -> ghostsDirections.put(x.getId(), x.getDirection()));
        return ghostsDirections;
    }

    @Override
    public boolean isGameInverted() {
        return this.levelManager.isGameInverted();
    }

    @Override
    public final void moveEntitiesNextPosition() {
        this.checkGameEnded();
        if (this.checkPillCollision()) {
            this.gameMap.get().removePill(this.pacMan.getPosition());
            final boolean oldIsGameInverted = this.levelManager.isGameInverted();
            this.levelManager.incScores(this.gameMap.get().getPillScore());
            if (!oldIsGameInverted && this.levelManager.isGameInverted()) {
                this.ghosts.forEach(x -> x.setEatable(true));
            }
        }
        if (this.checkPacmanGhostCollision()) {
            if (this.levelManager.isGameInverted()) {
                this.ghosts.removeIf(x ->
                x.getPosition().equals(this.pacMan.getPosition()));
            } else {
                this.pacMan.kill();
                this.ghosts.forEach(Entity::returnToStartPosition);
            }
        }
        this.pacMan.nextPosition();
        this.ghosts.forEach(Entity::nextPosition);

    }

    @Override
    public void initializeNewGame(){
        this.gameMap.get().restorePills();
        this.levelManager = new LevelManagerImpl(LEVEL_DURATION,
                INVERTED_GAME_DURATION,
                (this.gameMap.get().getPillsPositions().size() * this.gameMap.get().getPillScore())/4);
        this.ghosts = new HashSet<>();
        this.pacMan = new PacManImpl.Builder()
                .currentDirection(Directions.UP)
                .mapSize(this.gameMap.get().getxMapSize(), this.gameMap.get().getyMapSize())
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
        //this.createGhost(Ghosts.BLINKY);
        this.createGhost(Ghosts.PINKY);
    }

    @Override
    public final void decLevelTime() {
        this.checkGameEnded();
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

    @Override
    public final void setGameMap(final GameMap gameMap) {
        this.gameMap = Optional.of(gameMap);
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

/*    private void checkGameMapPresence() {
        this.checkCondition(this.gameMap.isEmpty());
    }*/

    private void checkGameEnded(){
        this.checkCondition(this.isGameEnded() && this.gameMap.isPresent());
    }

    private void checkCondition(final Boolean condition){
        if (condition){
            throw new IllegalStateException();
        }
    }

    private void nextLevel() {
        this.levelManager.nextLevel();
        this.ghosts.forEach(Entity::returnToStartPosition);
        this.ghosts.forEach(x -> x.setEatable(false));
        this.ghosts.forEach(x -> x.setOldLevelTrue());
        this.gameMap.get().restorePills();
        this.pacMan.returnToStartPosition();
        this.createGhost(Ghosts.CLYDE);
        //this.createGhost(Ghosts.BLINKY);
        this.createGhost(Ghosts.PINKY);
    }

    private boolean checkPillCollision() {
        return this.gameMap.get().isPill(this.getPacManPosition());
    }

    private boolean checkPacmanGhostCollision() {
        return this.ghosts.stream().anyMatch(x -> x.getPosition().equals(this.pacMan.getPosition())
        || (this.areDirectionsOpposite(this.pacMan.getDirection(), x.getDirection())
                && this.arePositionsNear(this.pacMan.getPosition(), x.getPosition())));
    }

    private boolean areDirectionsOpposite(final Directions dir1, final Directions dir2){
        return (dir1.equals(Directions.UP) && dir2.equals(Directions.DOWN))
                || (dir1.equals(Directions.DOWN) && dir2.equals(Directions.UP))
                ||(dir1.equals(Directions.LEFT) && dir2.equals(Directions.RIGHT))
                || (dir1.equals(Directions.RIGHT) && dir2.equals(Directions.LEFT));
    }

    private boolean arePositionsNear(final Pair<Integer, Integer> position1, final Pair<Integer, Integer> position2) {
        return Math.abs(position1.getX() - position2.getX()) <= 1
                && Math.abs(position1.getY() - position2.getY()) <= 1;
    }

/*    private List<Pair<Integer, Integer>> getGhostPositions(final Ghosts ghost) {
        final List<Pair<Integer, Integer>> positions = new ArrayList<>();
        this.ghosts.stream().filter(x -> x.getName().equals(ghost))
                .forEach(x -> positions.add(x.getPosition()));
        return positions;
    }*/

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
}
