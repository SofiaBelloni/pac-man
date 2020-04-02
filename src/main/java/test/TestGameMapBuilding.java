package test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import model.GameMap;
import model.GameMapImpl;
import model.PairImpl;

public class TestGameMapBuilding {

    private static final int XMAPSIZE = 40;
    private static final int YMAPSIZE = 40;
    
    @Test(expected = IllegalStateException.class)
    public void testBuilderNoFields() {
        @SuppressWarnings("unused")
        final GameMap gameMap = new GameMapImpl.Builder(XMAPSIZE, XMAPSIZE).build();
    }
    
    @Test
    public void builderNotEmpty() {
        Set<PairImpl<Integer, Integer>> walls = new HashSet<>();
        Set<PairImpl<Integer, Integer>> noWalls = new HashSet<>();
        Set<PairImpl<Integer, Integer>> pills = new HashSet<>();
        Set<PairImpl<Integer, Integer>> ghostsHouse = new HashSet<>();
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
        for (int i = 2; i < 40; i++) {
            noWalls.add(new PairImpl<Integer, Integer>(i, 20));
        }
        noWalls.addAll(pills);
        GameMap gameMap = new GameMapImpl.Builder(XMAPSIZE, YMAPSIZE)
                .walls(walls)
                .ghostsHouse(ghostsHouse)
                .pills(pills)
                .build();
        assertEquals(gameMap.getGhostHousePosition(), ghostsHouse);
        assertEquals(gameMap.getWallsPositions(), walls);
        assertEquals(gameMap.getPillsPositions(), pills);
        assertEquals(gameMap.getNoWallsPositions(), noWalls);
    }
}
