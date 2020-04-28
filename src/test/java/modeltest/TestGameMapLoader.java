package modeltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import controller.GameMapLoader;
import controller.GameMapLoaderImpl;
import model.GameMap;
import model.GameMapImpl;
import utils.PairImpl;

public class TestGameMapLoader {

    @Test
    public void mapLoadingWrongPath() throws IOException {
        Assertions.assertThrows(NullPointerException.class, () -> {
            @SuppressWarnings("unused")
            GameMapLoader mapLoader = new GameMapLoaderImpl("/game_maps/pacman_map_1.txt");
        });
    }

    @Test
    public void mapLoadingCorrectPath() throws IOException {
        GameMapLoader mapLoader = new GameMapLoaderImpl("game_map_1");
        GameMap gameMap = new GameMapImpl.Builder()
                .ghostsHouse(mapLoader.getGhostsHouse())
                .mapSize(mapLoader.getxMapSize(), mapLoader.getyMapSize())
                .pacManStartPosition(mapLoader.getPacManStartPosition())
                .pills(mapLoader.getPills())
                .pillScore(100)
                .walls(mapLoader.getWalls())
                .build();
        assertEquals(gameMap.getPacManStartPosition(), new PairImpl<Integer, Integer>(13, 17));
    }
}
