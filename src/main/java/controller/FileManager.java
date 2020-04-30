package controller;

import java.util.List;

/**
 * Interface for saving the players' ranking on file and for its recovery from file.
 */
public interface FileManager {
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

}
