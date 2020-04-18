package controller;

import java.util.TimerTask;
import model.GameModel;
/**
 * This class represents the timer of each level.
 */
public class GameTimer extends TimerTask {

    private final GameModel model;
    private boolean isRunning;
    /**
     * Constructor.
     * @param model
     *      the model reference
     */
    public GameTimer(final GameModel model) {
        this.model = model;
        this.isRunning = false;
    }

    @Override
    public void run() {
        if (this.model.getLevelTime() > 0) {
            this.model.decLevelTime();
        } else {
            this.isRunning = false;
            this.cancel();
        }
    }
    /**
     * @return true if GameTimer is running, otherwise false.
     */
    public boolean isRunning() {
        return isRunning;
    }
    /** 
     * @param isRunning
     *      represents the run method state, i.e. if GameTimer is running or not.
     */
    public void setRunning(final boolean isRunning) {
        this.isRunning = isRunning;
    }
}
