package model;

/**
 * The Interface GhostBehaviour.
 */
public interface GhostBehaviour {

    /**
     * Chase algorithm.
     * When ghosts reach the corner, they start chasing PacMan.
     *
     * @param pacMan the pac man
     */
    void chase(PacMan pacMan);

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
     */
    void turnAround();


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
    Pair<Integer, Integer> getCurrentPosition();

    /**
     * Sets the current position.
     *
     * @param right the new position
     */
    void setCurrentPosition(Pair<Integer, Integer> right);

    /**
     * Gets the start position.
     *
     * @return the start position
     */
    Pair<Integer, Integer> getStartPosition();

    /**
     * Gets the status of Blinky.
     * 
     *  @return true if Blinky is dead
     */
    boolean isBlinkyDead();

    /**
     * Set Blinky dead.
     */
    void setBlinkyDead();

}
