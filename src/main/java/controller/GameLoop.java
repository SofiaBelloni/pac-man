package controller;
/**
 * A thread that periodically updates the model and renders it on the view.
 */
public class GameLoop implements Runnable {
    
    private static double FPS = 60.0;
    private static final double TIME_BETWEEN_UPDATES = 1000.0 / FPS;

    private Thread thread;
    private boolean running = false;
    private boolean paused = false;
    /**
     * Starts the loop.
     */
    public void start() {
        thread = new Thread(this);
        thread.run();
    }
    /**
     * Run method.
     */
    public void run() {
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
            }
            now = System.currentTimeMillis();
            this.update(Math.min(1.0f, (double) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES)));
            this.render();
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


    private void render() {
      //TODO: Render game
    }

    private void update(final double delta) {
      //TODO: Update game
      //all time-related values must be multiplied by delta
    }
}
