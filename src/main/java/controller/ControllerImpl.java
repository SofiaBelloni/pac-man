package controller;

import java.io.IOException;
import java.util.List;

import model.GameMapImpl;
import model.GameModel;
import utils.Directions;
import utils.Player;
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
    private final LevelTimer levelTimer;
    private final RankingManager rankingManager;

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
        this.levelTimer = new LevelTimer(this.model);
        this.rankingManager = new RankingManagerFileImpl();
    }

    @Override
    public final void setGameMap(final String mapName) {
        try {
            final GameMapLoader mapLoader = new GameMapLoaderImpl(mapName);
            this.model.setGameMap(new GameMapImpl.Builder()
                    .ghostsHouse(mapLoader.getGhostsHouse())
                    .mapSize(mapLoader.getxMapSize(), mapLoader.getyMapSize())
                    .pacManStartPosition(mapLoader.getPacManStartPosition())
                    .pills(mapLoader.getPills())
                    .walls(mapLoader.getWalls())
                    .build());
            this.model.initializeNewGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final int getHighScore() {
        return this.rankingManager.getHighScore();
    }

    @Override
    public final List<Player> getAllPlayers() {
        return this.rankingManager.getAllPlayers();
    }

    @Override
    public final void resetRanking() {
        this.rankingManager.reset();
    }

    @Override
    public final void startGame() {
        this.gameLoop.start();
        this.levelTimer.startTimer();
    }

    @Override
    public final void pauseGame() {
        this.levelTimer.stopTimer();
        this.gameLoop.pause();
    }

    @Override
    public final void resumeGame() {
        this.levelTimer.startTimer();
        this.gameLoop.resume();
    }

    @Override
    public final void stopGame() {
        this.levelTimer.stopTimer();
        this.gameLoop.stop();
        this.model.initializeNewGame();
    }

    @Override
    public final void savePlayer(final String name) {
        this.rankingManager.savePlayer(name, this.getData().getLevel(), this.getData().getCurrentScore());
    }

    @Override
    public final void newPacManDirection(final Directions newDirection) {
        this.model.setPacManDirection(newDirection);
    }

    @Override
    public final DataUpdater getData() {
        return this.gameLoop.getData();
    }
}
