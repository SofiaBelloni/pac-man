package controller;

import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;
/**
 * This class represents the timer of each level.
 */
public class LevelTimer {

    private final GameModel model;
    private final Timer timer;
    private TimerTask task;
    private boolean isRunning;

    /**
     * Constructor.
     * @param model
     *      the {@link GameModel} reference
     */
    public LevelTimer(final GameModel model) {
        this.model = model;
        this.isRunning = false;
        this.timer = new Timer();
    }

    /**
     * Starts the timer only if it has not already been started.
     **/
    public void startTimer() {
        if (!this.isRunning) { 
            this.isRunning = true;
            task = new TimerTask() {
                public void run() {
                    model.decLevelTime();
                }
            };
            this.timer.scheduleAtFixedRate(task, 0, 1000);
        }
    }

    /**
     * Interrupts the timer only if it is running.
     **/
    public void stopTimer() {
        if (this.isRunning) {
            this.task.cancel();
            this.isRunning = false;
        }
    }

}
