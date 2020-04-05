package controller;

import model.GameModel;

/**
 * 
 * This class represents the controller of the game.
 *
 */
public class ControllerImpl implements ViewObserver {
    private final GameModel model;
    private View view;
    private GameLoop gameLoop;
    private int highScore;

    /**
     * Constructor.
     * @param model
     *      the model reference
     * @param view
     *      the view reference
     */
    public ControllerImpl(final GameModel model, final View view) {
        this.model = model;
        this.view = view;
        this.gameLoop = new GameLoop();
        //TODO leggi highScore da file
    }

    @Override
    public final void startGame() {
        // TODO Auto-generated method stub
        this.gameLoop.start();

    }

    @Override
    public final void pauseGame() {
        // TODO Auto-generated method stub
        this.gameLoop.pause();
    }

    @Override
    public final void resumeGame() {
        // TODO Auto-generated method stub
        this.gameLoop.resume();

    }

    @Override
    public void savePlayer(final int score) {
        // TODO Auto-generated method stub

    }

    @Override
    public void newPacManDirection(Directions newDirection) {
        // TODO Auto-generated method stub
        this.model.setPacManDirection(newDirection);
    }

}
