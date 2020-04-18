package controller;

import java.util.Timer;

import model.GameModel;
/**
 * This class is used to start and stop the GameTimer.
 */
public class LevelTimer {

    private GameModel model;
    private Timer levelTimer;
    private GameTimer gameTimer;
    private boolean isRunning;

    /**
     * Constructor.
     * @param model
     *      the model reference
     */
    public LevelTimer(final GameModel model) {
        this.model = model;
        this.levelTimer = new Timer();
        this.isRunning = false;
    }
    /**
     * Starts the GameTimer.
     **/
    public void startTimer() {
        gameTimer = new GameTimer(this.model);
        this.levelTimer.scheduleAtFixedRate(gameTimer, 0, 1000);
        this.isRunning = true;
    }
    /**
     * Interrupts the GameTimer if it is running.
     **/
    public void stopTimer() {
        if (this.isRunning) {
            this.gameTimer.cancel();
        }
    }
    
}
