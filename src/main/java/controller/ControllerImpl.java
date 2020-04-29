package controller;

import java.io.IOException;
import java.util.Optional;
import model.Directions;
import model.GameModel;
import view.View;

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
    private final String defaultMapName;
    private Optional<GameMapLoader> gameMapLoader;

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
        this.defaultMapName = "game_map_1";
        this.gameMapLoader = Optional.empty();
    }

    @Override
    public void setGameMap(final String mapName) {
        try {
            this.gameMapLoader = Optional.of(new GameMapLoaderImpl(mapName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getHighScore() {
        return this.highScore;
    }

    @Override
    public final void startGame() {
        // TODO Auto-generated method stub
        if (this.gameMapLoader.isEmpty()) {
            try {
                this.gameMapLoader = Optional.of(new GameMapLoaderImpl(this.defaultMapName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    public void savePlayer(final String name) {
        this.fileManager.savePlayer(name, this.model.getLevelNumber(), this.model.getScores());
    }

    @Override
    public void newPacManDirection(final Directions newDirection) {
        // TODO Auto-generated method stub
        this.model.setPacManDirection(newDirection);
    }
    
    public DataUpdater getData() {
        return this.gameLoop.getData();
    }
}
