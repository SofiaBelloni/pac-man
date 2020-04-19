package model;

public interface GhostBraveBehaviour extends GhostBehaviour {

    /**
     * @return the target of the Ghost in relax mode
     */
    Pair<Integer, Integer> getRelaxTarget();

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
