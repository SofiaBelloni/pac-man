package model;


import java.util.List;
import java.util.Set;

import utils.Pair;
import utils.PairImpl;

/**
* this class implements the Blinky behaviour.
*
*/
public class GhostBlinkyBehaviour extends GhostBraveAbstractBehaviour implements GhostBehaviour {

    private Pair<Integer, Integer> chaseTarget;

    public GhostBlinkyBehaviour(final Set<Pair<Integer, Integer>> setWall, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> relaxTarget, final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setRelaxTarget(relaxTarget);
    }

    private void targetPosition(final PacMan pacMan) {
            this.chaseTarget = pacMan.getPosition();
    }

    @Override
    public final void nextPosition(final boolean eatable, final boolean timeToTurn, final boolean oldLevel) {
        if (eatable || (oldLevel && this.isRelaxed())) {
            this.getMyFrightenedBehaviour().nextPosition(eatable, timeToTurn, oldLevel);
            this.setCurrentPosition(this.getMyFrightenedBehaviour().getCurrentPosition());
            this.setCurrentDirection(this.getMyFrightenedBehaviour().getCurrentDirection());
        } else {
            if (this.isRelaxed()) {
                this.relax(oldLevel);
            } else {
                if (!moveIfStuck()) {
                    this.targetPosition(this.getPacMan());
                    this.findPath(this.chaseTarget);
                    this.move(this.chaseTarget);
                }
            }
            this.getMyFrightenedBehaviour().setCurrentDirection(this.getCurrentDirection());
        }
    }

}
