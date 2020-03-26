package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Directions;
import model.PacMan;
import model.PacManImpl;
import model.Pair;

/**
 * JUnit test that creates objects.
 */

public class TestPacManBuilding {

    private static final int XMAPSIZE = 40;
    private static final int YMAPSIZE = 40;

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
                                    .noWalls(null)
                                    .startPosition(new Pair<Integer, Integer>(0, 0))
                                    .build();
        assertEquals(pacMan.getDirection(), Directions.LEFT);
        assertEquals(pacMan.getPosition(), new Pair<Integer, Integer>(0, 0));
    }

}
