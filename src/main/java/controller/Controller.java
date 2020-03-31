package controller;

/**
 * This interface represents the main class of the controller.
 * Its purpose is to accept input and converts it to commands for the model or view.
 */
public interface Controller {

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
     * @param score of the player
     * Saves the player and his score.
     **/
    void savePlayer(int score);

}
