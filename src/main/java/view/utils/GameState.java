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
}
