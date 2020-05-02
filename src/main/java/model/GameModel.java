package model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.Pair;

/**
 * This interface represents the main class of the model.
 * Its purpose is to manage all the logic of the game, such as the position of the entities, the scores, etc.
 */
public interface GameModel {
    /**
     * @param direction of Pac-Man
     * sets the direction of Pac-Man
     */
    void setPacManDirection(Directions direction);
    /**
     * @return a Set containing all the ghosts positions.
     */
    Map<Ghosts, List<Pair<Integer, Integer>>> getGhostsPositions();
    /**
     * @return a Set containing the wall's positions;
     */
    Set<Pair<Integer, Integer>> getWallsPositions();
    /**
     * @return a Set containing the pill's positions;
     */
    Set<Pair<Integer, Integer>> getPillsPositions();
    /**
     * Moves each mobile entity to its next position.
     */
    void moveEntitiesNextPosition();
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
     *
     * @param gameMap
     */
    void setGameMap(final GameMap gameMap);
}
