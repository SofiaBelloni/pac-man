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
     * @param isRunning
     *      represents the run method state
     */
    public GameTimer(final GameModel model, final boolean isRunning) {
        this.model = model;
        this.isRunning = isRunning;
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
    
}
