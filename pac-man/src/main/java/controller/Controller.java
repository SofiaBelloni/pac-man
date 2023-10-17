package controller;

import java.util.List;

import utils.Directions;
import utils.Player;
/**
 * This interface represents the controller of the application.
 **/
public interface Controller {
    /**
     * Sets the map to use.
     * @param mapName the name of the map.
     **/
    void setGameMap(String mapName);
    /**
     * @return the current high-score.
     **/
    int getHighScore();
    /**
     * @return list with all the Players saved. 
     */
    List<Player> getAllPlayers();
    /**
     * Deletes all the players saved. 
     */
    void resetRanking();
    /**
     * Starts a new game session.
     **/
    void startGame();
    /**
     * Pauses the game interrupting the game loop.
     **/
    void pauseGame();
    /**
     * Resumes the game if it has been paused.
     **/
    void resumeGame();
    /**
     * Stops the game session interrupting the game loop.
     **/
    void stopGame();
    /**
     * Saves a new player.
     * 
     * @param name the name of the player
     **/
    void savePlayer(String name);
    /**
     * Sets the new direction of Pac-Man.
     * @param newDirection The new direction of Pac-Man
     **/
    void newPacManDirection(Directions newDirection);
    /**
     * @return a {@link DataUpdater} object that allows to access to all the mappable data of the game.
     **/
    DataUpdater getData();

}
