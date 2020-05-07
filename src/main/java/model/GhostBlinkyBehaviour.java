package model;


import java.util.List;
import java.util.Set;
import utils.Pair;

/**
* this class implements the Blinky behaviour.
*
*/
public class GhostBlinkyBehaviour extends GhostSmartAbstractBehaviour implements GhostBehaviour {

    public GhostBlinkyBehaviour(final Set<Pair<Integer, Integer>> setWall, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> relaxTarget, final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setRelaxTarget(relaxTarget);
    }

    @Override
    public final void nextPosition(final boolean eatable, final boolean timeToTurn, final boolean oldLevel) {
        this.checkIfInside();
        if ((eatable || oldLevel) && !this.isRelaxed() && !this.isInside()) {
            if (timeToTurn || !moveIfStuck()) {
                this.getRandomBehaviour().nextPosition(eatable, timeToTurn, oldLevel);
                this.setCurrentPosition(this.getRandomBehaviour().getCurrentPosition());
                this.setCurrentDirection(this.getRandomBehaviour().getCurrentDirection());
            }
        } else {
            if (this.isInside() || !moveIfStuck()) {
                if (this.isRelaxed()) {
                    this.relax(oldLevel, eatable);
                } else {
                    this.findPath(this.getPacMan().getPosition());
                    this.move(this.getPacMan().getPosition());
                }
            }
        }
    }

}
