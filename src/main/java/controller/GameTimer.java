package controller;

import java.util.TimerTask;
import model.GameModel;
/**
 * This class represents the timer of each level.
 */
public class GameTimer extends TimerTask {

    private final GameModel model;
    /**
     * Constructor.
     * @param model
     *      the model reference
     */
    public GameTimer(final GameModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        if (this.model.getLevelTime() > 0) {
            this.model.decLevelTime();
        } else {
            this.cancel();
        }
    }
    
}
