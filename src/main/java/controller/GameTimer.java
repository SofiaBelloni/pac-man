package controller;

import java.util.TimerTask;

public class GameTimer extends TimerTask {
    
    private GameModel model;

    public GameTimer(final GameModel model) {
        this.model = model;
    }

    public void run() {
        if (this.model.getLevelTime() > 1) {
            this.model.decLevelTime();
        } else {
            this.model.nextLevel();
            this.cancel();
        }
    }
    
}
