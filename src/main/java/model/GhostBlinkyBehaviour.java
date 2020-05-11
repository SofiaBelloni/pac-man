package model;


import java.util.List;
import java.util.Set;
import utils.Pair;

/**
* this class implements the Blinky behaviour.
*
*/
public class GhostBlinkyBehaviour extends GhostFinalAbstractBehaviour {

    public GhostBlinkyBehaviour(final Set<Pair<Integer, Integer>> setWall, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> relaxTarget, final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setRelaxTarget(relaxTarget);
    }

    @Override
    public final void nextPosition(final boolean eatable, final boolean timeToTurn, final Ghosts name) {
        this.checkIfInside();
        if ((eatable || name.equals(Ghosts.OLDLEVEL)) && !this.isRelaxed()) {
            if (timeToTurn || !moveIfStuck()) {
                this.getRandomBehaviour().move(timeToTurn);
                this.setCurrentPosition(this.getRandomBehaviour().getCurrentPosition());
                this.setCurrentDirection(this.getRandomBehaviour().getCurrentDirection());
            }
        } else {
            if (this.isInside() || !moveIfStuck()) {
                if (this.isRelaxed()) {
                    this.relax(name, eatable);
                } else {
                    this.findPath(this.getPacMan().getPosition());
                    this.move(this.getPacMan().getPosition());
                }
            }
        }
    }

}
