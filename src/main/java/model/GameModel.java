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
    Map<Ghost, Pair<Integer, Integer>> getGhostsPosition();
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
     * @param entity type
     * @param position of the entity
     */
    void addEntity(Entities entity, Pair<Integer, Integer> position);
    /**
     * Increments the level time.
     */
    void incLevelTime();
    /**
     * Increments the level number.
     */
    void incLevelNumber();
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
