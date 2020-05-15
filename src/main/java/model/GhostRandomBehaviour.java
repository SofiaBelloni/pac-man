package model;

/**
 * The Interface GhostRandomBehaviour.
 */
public interface GhostRandomBehaviour extends GhostBehaviour {

    /**
     * Move the ghost random.
     *
     * @param timeToTurn true if the ghost have to turn around
     */
    void move(boolean timeToTurn);
}
