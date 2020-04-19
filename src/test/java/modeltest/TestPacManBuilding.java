package modeltest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import model.Directions;
import model.PacMan;
import model.PacManImpl;
import model.Pair;
import model.PairImpl;

/**
 * JUnit test that creates objects.
 */

public class TestPacManBuilding {

    private static final int XMAPSIZE = 28;
    private static final int YMAPSIZE = 31;

    @Test
    public void testPacManBuilderNoTypeSpecified() {
        assertThrows(IllegalStateException.class, () -> {
            @SuppressWarnings("unused")
            final PacMan pacMan = new PacManImpl.Builder().build();
        });
    }

    @Test
    public void testPacManTypes() {
        final PacMan pacMan = new PacManImpl.Builder()
                                    .currentDirection(Directions.LEFT)
                                    .lives(3)
                                    .mapSize(XMAPSIZE, YMAPSIZE)
                                    .noWalls(new HashSet<Pair<Integer, Integer>>())
                                    .startPosition(new PairImpl<Integer, Integer>(0, 0))
                                    .build();
        assertEquals(pacMan.getDirection(), Directions.LEFT);
        assertEquals(pacMan.getPosition(), new PairImpl<Integer, Integer>(0, 0));
    }

    @Test
    public void testPacManLives() {
        assertThrows(IllegalStateException.class, () -> {
            @SuppressWarnings("unused")
            final PacMan pacMan = new PacManImpl.Builder()
                    .currentDirection(Directions.LEFT)
                    .lives(-1)
                    .mapSize(XMAPSIZE, YMAPSIZE)
                    .noWalls(new HashSet<Pair<Integer, Integer>>())
                    .startPosition(new PairImpl<Integer, Integer>(0, 0))
                    .build();
        });
    }

}
