package model;

import java.util.Map;
import java.util.Set;

import utils.Directions;
import utils.GhostUtils;
import utils.Pair;

/**
 * This interface represents the main class of the model.
 * Its purpose is to manage all the logic of the game, such as the position of the entities, the scores, etc.
 * Once the object has been created, set the game map by calling setGameMap(GameMap gameMap) and then initializeNewGame()
 */
public interface GameModel {
    /**
     * @param direction of Pac-Man
     * sets the direction of Pac-Man
     */
    void setPacManDirection(Directions direction);
    /**
     * @return a Set containing the wall's positions;
     */
    Set<Pair<Integer, Integer>> getWallsPositions();
    /**
     * @return a Set containing the pill's positions;
     */
    Set<Pair<Integer, Integer>> getPillsPositions();
    /**
     * @return the level duration
     */
    int getLevelDuration();
    /**
     * @return a Map containing KEY -> Id, VALUE -> position.
     */
    Map<Integer, Pair<Integer, Integer>> getGhostsPositions();
    /**
     * @return a Map containing KEY -> Id, VALUE -> type.
     */
    Map<Integer, Ghosts> getGhostsTypes();
    /**
     * @return a Map containing KEY -> Id, VALUE -> direction.
     */
    Map<Integer, Directions> getGhostsDirections();
    /**
     *
     * @return true if the game is inverted, false otherwise
     */
    boolean isGameInverted();
    /**
     * Moves each mobile entity to its next position.
     */
    void moveEntitiesNextPosition();
    /**
     * reset the game.
     */
    void initializeNewGame();

    /**
     * Increments the level time.
     */
    void decLevelTime();
    /**
     * @return the level number
     */
    int getLevelNumber();
    /**
     * @return the level time
     */
    int getLevelTime();
    /**
     * @return the scores of the current game
     */
    int getScores();
    /**
     * @return the remaining lives of Pac-Man
     */
    int getPacManLives();
    /**
     * @return the Pacman position
     */
    Pair<Integer, Integer> getPacManPosition();
    /**
     * @return the Pacman direction.
     */
    Directions getPacManDirection();
    /**
     * @return true if the game is ended, false otherwise
     */
    Boolean isGameEnded();
    /**
     * @return the X GameMap size
     */
    int getyMapSize();
    /**
     * @return the Y GameMap size
     */
    int getxMapSize();
    /**
     * Sets the game map.
     *
     * @param gameMap sets the game map
     */
    void setGameMap(GameMap gameMap);
    /**
     * @return the Map of GhostUtils
     */
    Map<Integer, GhostUtils> getGhosts();
    /**
     * @return the time of inverted game
     */
    int getInvertedGameTime();
}
