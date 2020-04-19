package modeltest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.GameMap;
import model.GameMapImpl;
import model.Pair;
import model.PairImpl;

public class TestGameMapBuilding {

    private static final int XMAPSIZE = 40;
    private static final int YMAPSIZE = 40;

    @Test
    public void testBuilderNoFields() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            @SuppressWarnings("unused")
            final GameMap gameMap = new GameMapImpl.Builder()
            .mapSize(XMAPSIZE, YMAPSIZE)
            .build();
        });
    }

    @Test
    public void builderNotEmpty() {
        Set<Pair<Integer, Integer>> walls = new HashSet<>();
        Set<Pair<Integer, Integer>> pills = new HashSet<>();
        Set<Pair<Integer, Integer>> ghostsHouse = new HashSet<>();
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 20; j++) {
                walls.add(new PairImpl<Integer, Integer>(i, j));
            }
        }
        ghostsHouse.add(new PairImpl<Integer, Integer>(0, 20));
        ghostsHouse.add(new PairImpl<Integer, Integer>(1, 20));
        for (int i = 0; i < 40; i++) {
            for (int j = 21; j < 40; j++) {
                pills.add(new PairImpl<Integer, Integer>(i, j));
            }
        }
        GameMap gameMap = new GameMapImpl.Builder()
                .mapSize(XMAPSIZE, YMAPSIZE)
                .pacManStartPosition(new PairImpl<Integer, Integer>(2, 20))
                .walls(walls)
                .ghostsHouse(ghostsHouse)
                .pills(pills)
                .pillScore(10)
                .build();
        assertEquals(gameMap.getPacManStartPosition(), new PairImpl<Integer, Integer>(2, 20));
    }
}
