package controller;

import model.GameModel;

/**
 * 
 * This class represents the controller of the game.
 *
 */
public class ControllerImpl implements Controller {
    private final GameModel model;
    private View view;
    private GameLoop gameLoop;
    private FileManager fileManager;
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
        this.gameLoop = new GameLoopImpl(this.model, this.view);
        this.fileManager = new FileManagerImpl();
        this.highScore = this.fileManager.getHighScore();
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

 
    public void savePlayer(final String name) {
        this.fileManager.savePlayer(name, this.model.getLevelNumber(), this.model.getScores());

    }


    public void newPacManDirection(Directions newDirection) {
        // TODO Auto-generated method stub
        this.model.setPacManDirection(newDirection);
    }

}
