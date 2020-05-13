package utils;

import model.Ghosts;

/**
 * The Class GhostUtils used to pass ghost informations from model to view.
 */
public class GhostUtils {


    private Pair<Integer, Integer> oldGhostPosition;

    private Pair<Integer, Integer> ghostPosition;

    private Ghosts ghostName;

    private Directions direction;

    /**
     * Instantiates a new ghost utils.
     *
     * @param ghostPosition the ghost position
     * @param ghostName the ghost name
     * @param direction the direction
     */
    public GhostUtils(final Pair<Integer, Integer> ghostPosition,
             final Ghosts ghostName, final Directions direction) {
        this.ghostPosition = ghostPosition;
        this.ghostName = ghostName;
        this.direction = direction;
    }

    /**
     * Gets the ghost direction.
     *
     * @return the ghost direction
     */
    public final Directions getGhostDirection() {
        return this.direction;
    }

    /**
     * Sets the ghost direction.
     *
     * @param direction the new ghost direction
     */
    public final void setGhostDirection(final Directions direction) {
        this.direction = direction;
    }

    /**
     * Gets the ghost old position.
     *
     * @return the ghost old position
     */
    public final Pair<Integer, Integer> getGhostOldPosition() {
        return this.oldGhostPosition;
    }

    /**
     * Gets the ghost position.
     *
     * @return the ghost position
     */
    public final Pair<Integer, Integer> getGhostPosition() {
        return this.ghostPosition;
    }

    /**
     * Sets the ghost position.
     *
     * @param ghostPosition the ghost position
     */
    public final void setGhostPosition(final Pair<Integer, Integer> ghostPosition) {
        this.oldGhostPosition = this.ghostPosition;
        this.ghostPosition = ghostPosition;
    }

    /**
     * Gets the ghost name.
     *
     * @return the ghost name
     */
    public final Ghosts getGhostName() {
        return this.ghostName;
    }

    /**
     * Sets the ghost as old level ghost.
     */
    public final void setOldLevel() {
        this.ghostName = Ghosts.OLDLEVEL;
    }
}
