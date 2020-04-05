package controller;

/**
 * Observer for controller.
 */
public interface ViewObserver {

    /**
     * Starts a new game session.
     **/
    void startGame();
    /**
     * Pauses the game interrupting the game loop.
     */
    void pauseGame();
    /**
     * Resumes the game if it has been paused.
     */
    void resumeGame();
    /**
     * Saves the player and his score.
     * 
     * @param score of the player
     **/
    void savePlayer(int score);
    /**
     * View communicates the new direction of the Pac-Man.
     * 
     * @param newDirection The new directions of Pac-Man
     */
    void newPacManDirection(Directions newDirection);
}
