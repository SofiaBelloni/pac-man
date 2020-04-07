package model;

import java.util.Set;

public interface GameMapLoader {
    /**
     * @return the size of the map on the x-axis
     */
    Integer getxMapSize();
    /**
     * @return the size of the map on the y-axis
     */
    Integer getyMapSize();
    /**
     * @return a Set containing the positions of the pills
     */
    Set<Pair<Integer, Integer>> getPills();
    /**
     * @return a Set containing the positions of the walls
     */
    Set<Pair<Integer, Integer>> getWalls();
    /**
     * @return a Set containing the positions of the ghost house
     */
    Set<Pair<Integer, Integer>> getGhostsHouse();
    /**
     * @return the initial position of PacMan
     */
    Pair<Integer, Integer> getPacManStartPosition();
}
