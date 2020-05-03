package model;


import java.util.List;
import java.util.Set;

import utils.Pair;

/**
* this class implements the Blinky behaviour.
*
*/
public class GhostBlinkyBehaviour extends GhostBraveAbstractBehaviour implements GhostBehaviour {

    public GhostBlinkyBehaviour(final Set<Pair<Integer, Integer>> setWall, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> relaxTarget, final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setRelaxTarget(relaxTarget);
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
                    this.findPath(this.getPacMan().getPosition());
                    this.move(this.getPacMan().getPosition());
                }
            }
            this.getMyFrightenedBehaviour().setCurrentDirection(this.getCurrentDirection());
        }
    }

}
