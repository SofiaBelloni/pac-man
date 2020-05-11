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
    protected final Pair<Integer, Integer> getChaseTarget() {
        return this.getPacMan().getPosition();
    }


}
