package modeltest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.PacMan;
import model.PacManImpl;
import utils.Directions;
import utils.Pair;
import utils.PairImpl;

/**
 * JUnit test that creates objects.
 */

public class TestPacManEntity {

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

    @Test
    public void testPacManNextPosition() {
        final Set<Pair<Integer, Integer>> noWalls = new HashSet<>();
        noWalls.add(new PairImpl<Integer, Integer>(0, 0));
        noWalls.add(new PairImpl<Integer, Integer>(1, 0));
        noWalls.add(new PairImpl<Integer, Integer>(XMAPSIZE - 1, 0));
        final PacMan pacMan = new PacManImpl.Builder()
                                    .currentDirection(Directions.RIGHT)
                                    .lives(3)
                                    .mapSize(XMAPSIZE, YMAPSIZE)
                                    .noWalls(noWalls)
                                    .startPosition(new PairImpl<Integer, Integer>(0, 0))
                                    .build();
        pacMan.nextPosition();
        assertEquals(pacMan.getPosition(), new PairImpl<Integer, Integer>(1, 0));
        pacMan.nextPosition(); //this nextPosition do nothing because pacman can't go there (wall)
        assertEquals(pacMan.getPosition(), new PairImpl<Integer, Integer>(1, 0));
        pacMan.setDirection(Directions.LEFT);
        pacMan.nextPosition();
        assertEquals(pacMan.getPosition(), new PairImpl<Integer, Integer>(0, 0));
        pacMan.nextPosition(); //this test is for toroidal conversion
        assertEquals(pacMan.getPosition(), new PairImpl<Integer, Integer>(XMAPSIZE - 1, 0));

    }

}
