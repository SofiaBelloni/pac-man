package test;

import java.io.IOException;

import org.junit.Test;

import model.GameMap;
import model.GameMapImpl;
import model.GameMapLoader;
import model.GameMapLoaderImpl;

public class TestGameMapLoder {

    @Test(expected = NullPointerException.class)
    public void mapLoadingWrongPath() throws IOException {
        @SuppressWarnings("unused")
        GameMapLoader mapLoader = new GameMapLoaderImpl("/game_maps/pacman_map_1.txt");
    }

    @Test
    public void mapLoadingCorrectPath() throws IOException {
        GameMapLoader mapLoader = new GameMapLoaderImpl("game_maps/pacman_map_1");
        GameMap gameMap = new GameMapImpl.Builder()
                .ghostsHouse(mapLoader.getGhostsHouse())
                .mapSize(mapLoader.getxMapSize(), mapLoader.getyMapSize())
                .pacManStartPosition(mapLoader.getPacManStartPosition())
                .pills(mapLoader.getPills())
                .pillScore(100)
                .walls(mapLoader.getWalls())
                .build();
        gameMap.toString();
    }
}
