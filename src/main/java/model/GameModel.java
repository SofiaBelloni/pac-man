package model;

import java.util.Map;
import java.util.Set;

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
    Map<Ghosts, Set<PairImpl<Integer, Integer>>> getGhostsPositions();
    /**
     * @return a Set containing the wall's positions;
     */
    Set<PairImpl<Integer, Integer>> getWallsPositions();
    /**
     * @return a Set containing the pill's positions;
     */
    Set<PairImpl<Integer, Integer>> getPillsPositions();
    /**
     * Moves each mobile entity to its next position.
     */
    void moveEntitiesNextPosition();
    /**
     * Increments the level time.
     */
    void decLevelTime();
    /**
     * Increments the level number.
     */
    void nextLevel();
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
}
