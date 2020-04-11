package model;

import java.io.IOException;
import java.util.Optional;

public class GameMapFactory {
    private static final int DEFAULT_PILL_SCORE = 100;

    public final GameMap createMap1(final Optional<Integer> pillScore) {
        return this.createGameMap(
                this.createMapLoader("game_maps/game_map_1"), pillScore);
    }

    private GameMapLoader createMapLoader(final String mapPath) {
        try {
            return new GameMapLoaderImpl(mapPath);
        } catch (IOException e) {
            throw new IllegalStateException("Worng file path");
        }
    }

    private GameMap createGameMap(final GameMapLoader mapLoader, final Optional<Integer> pillScore) {
        GameMapImpl.Builder mapBuilder = new GameMapImpl.Builder().ghostsHouse(mapLoader.getGhostsHouse())
                .mapSize(mapLoader.getxMapSize(), mapLoader.getyMapSize())
                .pacManStartPosition(mapLoader.getPacManStartPosition())
                .pills(mapLoader.getPills())
                .walls(mapLoader.getWalls());
        if (pillScore.isEmpty()) {
            mapBuilder = mapBuilder.pillScore(DEFAULT_PILL_SCORE);
        } else {
            mapBuilder.pillScore(pillScore.get());
        }
        return mapBuilder.build();
    }
}
