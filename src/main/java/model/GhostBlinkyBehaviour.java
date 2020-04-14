package model;

import java.util.Set;

/**
* this class implements the Blinky behaviour.
*
*/
public class GhostBlinkyBehaviour extends GhostAbstractBehaviour {

    public GhostBlinkyBehaviour(final Set<Pair<Integer, Integer>> setWall, final Set<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize, final Pair<Integer, Integer> relaxTarget) {
        super(setWall, ghostHouse, xMapSize, yMapSize);
        this.setRelaxTarget(relaxTarget);
        this.setStartPosition(this.getGhostHouse().get(0));
    }

    @Override
    public final void chase(final PacMan pacMan) {
        if (!moveIfStuck()) {
            this.findPath(pacMan.getPosition());
            this.move(pacMan.getPosition(), 1);
        }
    }

}
