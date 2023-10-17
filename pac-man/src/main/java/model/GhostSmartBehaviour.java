package model;

import utils.Pair;

/**
 * The Interface GhostSmartBehaviour.
 */
public interface GhostSmartBehaviour extends GhostBehaviour {

    /**
     * Gets the relax target.
     *
     * @return the target of the Ghost in relax mode
     */
    Pair<Integer, Integer> getRelaxTarget();

    /**
     * Notify Inky the Blinky death.
     */
    void setBlinkyDead();
}
