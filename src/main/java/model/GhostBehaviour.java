package model;

import java.util.Optional;

/**
 * The Interface BraveBehaviour.
 */
public interface GhostBehaviour {
    /**
     * RunAway algorithm. 
     * When the ghost are eatable, they move in a random way.
     */
    void runAway();
    /**
     * Chase algorithm.
     * When ghosts reach the corner, they start chasing PacMan
     * @param pacMan
     * @param blinkyPosition the blinky position
     */
    void chase(PacMan pacMan, Optional<Pair<Integer, Integer>> blinkyPosition);
    /**
     * Relax algorithm.
     * When ghosts are created, they reach the respective corner of the map.
     */
    void relax();

    /**
     * Gets the position.
     *
     * @return the position
     */
    Pair<Integer, Integer> getPosition();

    /**
     * Sets the position.
     *
     * @param initialPosition the initial position
     */
    void setPosition(Pair<Integer, Integer> initialPosition);
    
    /**
     * Makes ghost turn around because they are eatable.
     * 
     * @param dir the direction
     * @return the opposite direction
     */
    Directions turnAround(Directions dir);
    /**
     * Gets the direction
     */
    Directions getDirection();
}
