package model;

import java.util.Optional;

/**
 * The Interface GhostBehaviour.
 */
public interface GhostBehaviour {

    /**
     * Chase algorithm.
     * When ghosts reach the corner, they start chasing PacMan.
     *
     * @param pacMan the pac man
     * @param blinkyPosition the blinky position
     */
    void chase(PacMan pacMan, Optional<PairImpl<Integer, Integer>> blinkyPosition);

    /**
     * Run away algorithm.
     * When ghosts are eatable, they run away from PacMan.
     */
    void runAway();

    /**
     * Relax algorithm.
     * When ghosts are created, they move in their respective corner.
     */
    void relax();

    /**
     * Makes ghost turn around.
     *
     * @param dir the current direction
     * @return the new direction
     */
    Directions turnAround(Directions dir);


    /**
     * Gets the current direction.
     *
     * @return the current direction
     */
    Directions getCurrentDirection();

    /**
     * Gets the current position.
     *
     * @return the current position
     */
    PairImpl<Integer, Integer> getCurrentPosition();

    /**
     * Sets the current position.
     *
     * @param newPosition the new position
     */
    void setCurrentPosition(PairImpl<Integer, Integer> newPosition);

    /**
     * Gets the start position.
     *
     * @return the start position
     */
    PairImpl<Integer, Integer> getStartPosition();
}
