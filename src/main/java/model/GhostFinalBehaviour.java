package model;

public interface GhostFinalBehaviour extends GhostSmartBehaviour, GhostRandomBehaviour {

    /**
     * Calculate the next position of the ghost.
     *
     * @param eatable the eatable
     * @param name the ghost name
     * @param timeToTurn the time to turn
     */
    void nextPosition(boolean eatable, boolean timeToTurn, Ghosts name);
}
