package model;

import java.util.Set;

import model.GameMapImpl.Builder;

public interface GameMapBuilder {
    /**
     * @param walls
     * @return a set containing all the positions of the walls
     */
    Builder walls(Set<Pair<Integer, Integer>> walls);
    /**
     * @param ghostsHouse
     * @return a set containing all the positions of the ghost's house
     */
    Builder ghostsHouse(Set<Pair<Integer, Integer>> ghostsHouse);
    /**
     * @param pills
     * @return a set containing all the positions of the pills
     */
    Builder pills(Set<Pair<Integer, Integer>> pills);
    /**
     * @return a GameMapImpl object if all fields are not empty
     * @throws IllegalStateException if a field is empty
     */
    GameMapImpl build();
}
