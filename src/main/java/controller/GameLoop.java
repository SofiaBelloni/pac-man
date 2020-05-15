package controller;
/**
 * This interface represents a mechanism that periodically updates the model and renders it on the view.
 */
public interface GameLoop {
    /**
     * Starts the loop.
     */
    void start();
    /**
     * Interrupts the loop and stops the thread.
     */
    void stop();
    /**
     * Suspends the loop.
     */
    void pause();
    /**
     * Makes the loop restart if it was previously suspended.
     */
    void resume();
    /**
     * @return a {@link DataUpdater} object that allows to access to all the mappable data of the game.
     **/
    DataUpdater getData();
}
