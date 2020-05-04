package controller;

import model.GameModel;
import view.View;

/**
 * A thread that periodically updates the model and renders it on the view.
 */
public class GameLoopImpl implements Runnable, GameLoop {

    private static final double FPS = 3.333;
    private static final double TIME_BETWEEN_UPDATES = 1000.0 / FPS;

    private Thread thread;
    private volatile boolean running;
    private volatile boolean paused;
    private final LevelTimer levelTimer;
    private final DataUpdater data;
    /**
     * Constructor.
     * @param model
     *      the model reference
     * @param view
     *      the view reference
     */
    public GameLoopImpl(final GameModel model, final View view) {
        this.data = new DataUpdater(model, view);
        this.running = false;
        this.paused = false;
        this.levelTimer = new LevelTimer(model);
    }

    @Override
    public final void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public final void run() {
        this.levelTimer.startTimer();
        this.running = true;
        long now = 0;
        long lastUpdateTime = System.currentTimeMillis();
        long unprocessedTime = 0;
        while (this.running) {
            if (this.paused) {
                synchronized (this.thread) {
                    while (this.paused) {
                        try {
                            this.thread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                lastUpdateTime = System.currentTimeMillis();
            }
            now = System.currentTimeMillis();
            this.update();
            this.render(Math.min(1.0f, (double) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES)));
            unprocessedTime = System.currentTimeMillis() - now;
            if (unprocessedTime < TIME_BETWEEN_UPDATES) {
                try {
                    Thread.sleep((long) TIME_BETWEEN_UPDATES - unprocessedTime);
                } catch (InterruptedException e) { 
                    e.printStackTrace();
                }
            }
            lastUpdateTime = now;
        }
        this.thread.interrupt();
    }

    @Override
    public final synchronized void stop() {
        this.running = false;
        this.levelTimer.stopTimer();
    }

    @Override
    public final synchronized void pause() {
        this.paused = true;
        this.levelTimer.stopTimer();
    }

    @Override
    public final synchronized void resume() {
        this.paused = false;
        this.levelTimer.startTimer();
        this.thread.notifyAll();
    }

    @Override
    public final DataUpdater getData() {
        return this.data;
    }

    private void render(final double delta) {
      //delegate method
      //all time-related values must be multiplied by delta
        this.data.render();
    }

    private void update() {
      //delegate method
        this.data.updateModel();
    }
}
