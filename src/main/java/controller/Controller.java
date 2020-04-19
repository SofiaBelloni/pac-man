package controller;

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
     * Saves the player.
     * 
     * @param name the name of the player
     **/
    void savePlayer(String name);
    /**
     * @param newDirection The new directions of Pac-Man
     **/
    void newPacManDirection(Directions newDirection);
    
}
