package model;

import java.util.Set;

/**
* this class implements the Clyde behaviour.
*
*/
public class GhostClydeBehaviour extends GhostAbstractBehaviour {

    private Pair<Integer, Integer> chaseTarget;
    private static final int PACMANRADIUS = 7;

    public GhostClydeBehaviour(final Set<Pair<Integer, Integer>> setWall, final Set<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize, final Pair<Integer, Integer> relaxTarget) {
        super(setWall, ghostHouse, xMapSize, yMapSize);
        this.setRelaxTarget(relaxTarget);
        this.setStartPosition(this.getGhostHouse().get(0));
    }

    private void targetPosition(final PacMan pacMan) {
        if (this.getCurrentPosition().getX() > pacMan.getPosition().getX() - PACMANRADIUS && this.getCurrentPosition().getX() < pacMan.getPosition().getX() + PACMANRADIUS 
            && this.getCurrentPosition().getY() > pacMan.getPosition().getY() - PACMANRADIUS && this.getCurrentPosition().getY() < pacMan.getPosition().getY() + PACMANRADIUS) {
            this.chaseTarget = this.getRelaxTarget();
        } else {
            this.chaseTarget = pacMan.getPosition();
        }
    }

    @Override
    public final void chase(final PacMan pacMan) {
        if (!moveIfStuck()) {
            this.targetPosition(pacMan);
            this.findPath(this.chaseTarget);
            this.move(this.chaseTarget, 1);
        }
    }
}
