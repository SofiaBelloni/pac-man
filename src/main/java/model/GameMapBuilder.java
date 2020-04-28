package model;

import java.util.Set;

import model.GameMapImpl.Builder;
import utils.Pair;

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
    Builder walls(Set<Pair<Integer, Integer>> walls);
    /**
     * @param ghostsHouse a set containing all the positions of the ghost house
     * @return this
     */
    Builder ghostsHouse(Set<Pair<Integer, Integer>> ghostsHouse);
    /**
     * @param pills a set containing all the positions of the pills
     * @return this
     */
    Builder pills(Set<Pair<Integer, Integer>> pills);
    /**
     * @param position initial position of PacMan
     * @return this
     */
    Builder pacManStartPosition(Pair<Integer, Integer> position);
    /**
     * @return a GameMapImpl object if all fields are not empty
     */
    GameMapImpl build();
}
