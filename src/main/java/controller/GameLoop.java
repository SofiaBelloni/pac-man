package controller;

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

}
