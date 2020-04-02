package model;

import java.util.Set;

/**
 * this interface is used to manage the game map and all its immobile entities.
 *
 */
public interface GameMap {
    /**
     * @param position of the pill to remove
     */
    void removePill(PairImpl<Integer, Integer> position);
    /**
     * @return the walls positions
     */
    Set<PairImpl<Integer, Integer>> getWallsPositions();
    /**
     * @return the pills positions
     */
    Set<PairImpl<Integer, Integer>> getPillsPositions();
    /**
     * @return the ghost house position
     */
    Set<PairImpl<Integer, Integer>> getGhostHousePosition();
    /**
     * @return free positions (no wall, pill, or ghost house)
     */
    Set<PairImpl<Integer, Integer>> getNoWallsPositions();
    /**
     * @return the map size on x axis
     */
    int getxMapSize();
    /**
     * @return the map size on y axis
     */
    int getyMapSize();
    /**
     * @return score value of one pill
     */
    int getPillScore();
}
