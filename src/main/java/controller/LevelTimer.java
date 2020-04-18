package controller;

import java.util.Timer;

import model.GameModel;
/**
 * This class is used to start and stop the GameTimer.
 */
public class LevelTimer {

    private final Timer timer;
    private final GameTimer gameTimer;

    /**
     * Constructor.
     * @param model
     *      the model reference
     */
    public LevelTimer(final GameModel model) {
        this.timer = new Timer();
        this.gameTimer = new GameTimer(model);
    }
    /**
     * Starts the GameTimer only if it has not already been started.
     **/
    public void startTimer() {
        if (!this.gameTimer.isRunning()) {
            this.timer.scheduleAtFixedRate(this.gameTimer, 0, 1000);
            this.gameTimer.setRunning(true);
        }
    }
    /**
     * Interrupts the GameTimer only if it is running.
     **/
    public void stopTimer() {
        if (this.gameTimer.isRunning()) {
            this.gameTimer.cancel();
            this.gameTimer.setRunning(false);
        }
    }
}
