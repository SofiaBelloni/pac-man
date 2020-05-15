package controller;

import java.util.List;

import utils.Player;

/**
 * Interface for managing the ranking.
 */
public interface RankingManager {
    /**
     * @return the current high-score
     */
    int getHighScore();
    /**
     * Saves a new player. 
     * @param name      the name of the player.
     * @param level     the level reached by the player.
     * @param score     the score reached by the player.
     */
    void savePlayer(String name, int level, int score);
    /**
     * @return list with all the Players saved. 
     */
    List<Player> getAllPlayers();
    /**
     * Deletes all the players saved. 
     */
    void reset();
}
