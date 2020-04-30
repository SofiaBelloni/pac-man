package model;


import java.util.List;
import java.util.Set;

import utils.Pair;

/**
* this class implements the Clyde behaviour.
*
*/
public class GhostClydeBehaviour extends GhostBraveAbstractBehaviour {

    private Pair<Integer, Integer> chaseTarget;
    private static final int PACMANRADIUS = 7;

    public GhostClydeBehaviour(final Set<Pair<Integer, Integer>> setWall, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> relaxTarget, final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setRelaxTarget(relaxTarget);
    }

    private void targetPosition(final PacMan pacMan) {
        if (this.getCurrentPosition().getX() > pacMan.getPosition().getX() - PACMANRADIUS 
                && this.getCurrentPosition().getX() < pacMan.getPosition().getX() + PACMANRADIUS 
                && this.getCurrentPosition().getY() > pacMan.getPosition().getY() - PACMANRADIUS 
                && this.getCurrentPosition().getY() < pacMan.getPosition().getY() + PACMANRADIUS) {
            this.chaseTarget = this.getRelaxTarget();
        } else {
            this.chaseTarget = pacMan.getPosition();
        }
    }

    @Override
    public final void nextPosition(final boolean eatable, final boolean timeToTurn) {
        if (eatable) {
            this.getMyFrightenedBehaviour().nextPosition(eatable, timeToTurn);
            this.setCurrentPosition(this.getMyFrightenedBehaviour().getCurrentPosition());
            this.setCurrentDirection(this.getMyFrightenedBehaviour().getCurrentDirection());
        } else {
            if (this.isRelaxed()) {
                super.nextPosition(eatable, timeToTurn);
            } else {
                if (!moveIfStuck()) {
                    this.targetPosition(this.getPacMan());
                    super.findPath(this.chaseTarget);
                    super.move(this.chaseTarget);
                }
            }
            this.getMyFrightenedBehaviour().setCurrentDirection(this.getCurrentDirection());
        }
    }
}
