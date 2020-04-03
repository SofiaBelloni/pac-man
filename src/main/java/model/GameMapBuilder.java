package model;

import java.util.Set;

import model.GameMapImpl.Builder;

public interface GameMapBuilder {
    /**
     * @param xMapSize
     * @param yMapSize
     * @return this
     */
    Builder mapSize(int xMapSize, int yMapSize);
    /**
     * @param pillScore score value of one pill
     * @return this
     */
    Builder pillScore(int pillScore);
    /**
     * @param walls a set containing all the positions of the walls
     * @return this
     */
    Builder walls(Set<PairImpl<Integer, Integer>> walls);
    /**
     * @param ghostsHouse a set containing all the positions of the ghost house
     * @return this
     */
    Builder ghostsHouse(Set<PairImpl<Integer, Integer>> ghostsHouse);
    /**
     * @param pills a set containing all the positions of the pills
     * @return this
     */
    Builder pills(Set<PairImpl<Integer, Integer>> pills);
    /**
     * @return a GameMapImpl object if all fields are not empty
     */
    GameMapImpl build();
}
