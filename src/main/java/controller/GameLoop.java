package controller;

import model.GameModel;

/**
 * A thread that periodically updates the model and renders it on the view.
 */
public class GameLoop implements Runnable {

    private static double FPS = 60.0;
    private static final double TIME_BETWEEN_UPDATES = 1000.0 / FPS;

    private Thread thread;
    private boolean running;
    private boolean paused;
    private GameModel model;
    private View view;
    private LevelTimer levelTimer;
    /**
     * Constructor.
     * @param model
     *      the model reference
     * @param view
     *      the view reference
     */
    public GameLoop(GameModel model, View view) {
        this.model = model;
        this.view = view;
        this.running = false;
        this.paused = false;
        this.levelTimer = new LevelTimer(this.model);
    }
    /**
     * Starts the loop.
     */
    public void start() {
        thread = new Thread(this);
        thread.run();
    }

    @Override
    public void run() {
        this.levelTimer.startTimer();
        this.running = true;
        long now = 0;
        long lastUpdateTime = System.currentTimeMillis();
        long unprocessedTime = 0;
        while (this.running) {
            if (this.paused) {
                synchronized (this.thread) {
                    while (this.paused) {
                        this.levelTimer.stopTimer();
                        try {
                            this.thread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.levelTimer.startTimer();
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
    }
    /**
     * Interrupts the loop and stops the thread.
     */
    public synchronized void stop() {
        this.running = false;
        this.thread.interrupt();
    }
    /**
     * Suspends the loop.
     */
    public synchronized void pause() {
        this.paused = true;
    }
    /**
     * Makes the loop restart if it was previously suspended.
     */
    public synchronized void resume() {
        this.paused = false;
        this.thread.notifyAll();
    }


    private void render(final double delta) {
      //TODO: Render game
      //all time-related values must be multiplied by delta

    }

    private void update() {
      //TODO: Update game
        this.model.moveEntitiesNextPosition();
    }
}
