package controller;

import java.io.IOException;
import java.util.Optional;
import model.Directions;
import model.GameMapImpl;
import model.GameModel;
import view.View;

/**
 * 
 * This class represents the implementation of controller interface.
 *
 */
public class ControllerImpl implements Controller {

    private static final String DEFAULT_MAP_NAME = "game_map_1";

    private final GameModel model;
    private final View view;
    private final GameLoop gameLoop;
    private final FileManager fileManager;
    private int highScore;

    /**
     * Constructor.
     * @param model
     *      the {@link GameModel} reference
     * @param view
     *      the {@link View} reference
     */
    public ControllerImpl(final GameModel model, final View view) {
        this.model = model;
        this.setGameMap(DEFAULT_MAP_NAME);
        this.view = view;
        this.gameLoop = new GameLoopImpl(this.model, this.view);
        this.fileManager = new FileManagerImpl();
        this.highScore = this.fileManager.getHighScore();
    }

    @Override
    public void setGameMap(final String mapName) {
        try {
            GameMapLoader mapLoader = new GameMapLoaderImpl(mapName);
            this.model.setGameMap(new GameMapImpl.Builder()
                    .ghostsHouse(mapLoader.getGhostsHouse())
                    .mapSize(mapLoader.getxMapSize(), mapLoader.getyMapSize())
                    .pacManStartPosition(mapLoader.getPacManStartPosition())
                    .pills(mapLoader.getPills())
                    .walls(mapLoader.getWalls())
                    .build());
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
        this.gameLoop.start();

    }

    @Override
    public final void pauseGame() {
        this.gameLoop.pause();
    }

    @Override
    public final void resumeGame() {
        this.gameLoop.resume();

    }

    @Override
    public void savePlayer(final String name) {
        this.fileManager.savePlayer(name, this.model.getLevelNumber(), this.model.getScores());
    }

    @Override
    public void newPacManDirection(final Directions newDirection) {
        this.model.setPacManDirection(newDirection);
    }

    @Override
    public DataUpdater getData() {
        return this.gameLoop.getData();
    }
}
