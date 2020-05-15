package model;

import java.util.Set;

import utils.Pair;

/**
 * this interface is used to manage the game map and all its immobile entities.
 *
 */
public interface GameMap {
    /**
     * @param position of the pill to remove
     */
    void removePill(Pair<Integer, Integer> position);
    /**
     * @return the walls positions
     */
    Set<Pair<Integer, Integer>> getWallsPositions();
    /**
     * @return the pills positions
     */
    Set<Pair<Integer, Integer>> getPillsPositions();
    /**
     * @return the ghost house position
     */
    Set<Pair<Integer, Integer>> getGhostHousePosition();
    /**
     * @return free positions (no wall or ghost house)
     */
    Set<Pair<Integer, Integer>> getNoWallsPositions();
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
    /**
     * @param position of the pill
     * @return true if in position there is a pill, false otherwise
     */
    boolean isPill(Pair<Integer, Integer> position);
    /**
     * @return the initial position of PacMan
     */
    Pair<Integer, Integer> getPacManStartPosition();
    /**
     * restores the pills in the map.
     */
    void restorePills();
}
