package model;

/**
 * The Interface GhostFinalBehaviour.
 */
public interface GhostFinalBehaviour extends GhostSmartBehaviour {

    /**
     * Calculate the next position of the ghost.
     *
     * @param eatable the eatable
     * @param timeToTurn the time to turn
     * @param name the ghost name
     */
    void nextPosition(boolean eatable, boolean timeToTurn, Ghosts name);
}
