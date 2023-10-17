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
    }

    @Override
    public final void start() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public final void run() {
        this.running = true;
        long lastUpdateTime = 0;
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
            }
            lastUpdateTime = System.currentTimeMillis();
            this.update();
            this.render();
            unprocessedTime = System.currentTimeMillis() - lastUpdateTime;
            if (unprocessedTime < TIME_BETWEEN_UPDATES) {
                try {
                    Thread.sleep((long) TIME_BETWEEN_UPDATES - unprocessedTime);
                } catch (InterruptedException e) { 
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public final synchronized void stop() {
        this.running = false;
    }

    @Override
    public final synchronized void pause() {
        this.paused = true;
    }

    @Override
    public final void resume() {
        synchronized (this.thread) {
            this.paused = false;
            this.thread.notifyAll();
        }
    }

    @Override
    public final DataUpdater getData() {
        return this.data;
    }

    private void render() {
      //delegate method
        this.data.render();
    }

    private void update() {
      //delegate method
      this.data.updateModel();
    }
}
