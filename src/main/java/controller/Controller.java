package controller;

public interface Controller {
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
