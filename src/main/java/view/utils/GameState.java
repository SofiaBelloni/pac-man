package view.utils;
/**
 * Class to manage the current game state in the GameViewController.
 */
public class GameState {

    public enum State {
        /**
         * The game is running.
         */
        RUNNING,
        /**
         * The game is finished.
         */
        FINISHED,
        /**
         * The game is paused.
         */
        PAUSE;
    }

    private State state = State.FINISHED;
    private boolean isStarting;

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(final State state) {
        this.state = state;
    }

    /**
     * @return true if the game is in the 3,2,1 start countdown.
     */
    public boolean isStarting() {
        return isStarting;
    }

    /**
     * Set true when you start the TimerTask.
     * Set false when the game start.
     * @param isStarting 
     */
    public void setStarting(final boolean isStarting) {
        this.isStarting = isStarting;
    }

}
