package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;

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

    @Test(expected = IllegalStateException.class)
    public void testPacManBuilderNoTypeSpecified() {
        @SuppressWarnings("unused")
        final PacMan pacMan = new PacManImpl.Builder().build();
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

    @Test(expected = IllegalStateException.class)
    public void testPacManLives() {
        @SuppressWarnings("unused")
        final PacMan pacMan = new PacManImpl.Builder()
                                    .currentDirection(Directions.LEFT)
                                    .lives(-1)
                                    .mapSize(XMAPSIZE, YMAPSIZE)
                                    .noWalls(new HashSet<Pair<Integer, Integer>>())
                                    .startPosition(new PairImpl<Integer, Integer>(0, 0))
                                    .build();
    }

}
