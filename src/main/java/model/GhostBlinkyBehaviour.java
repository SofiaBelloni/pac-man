package model;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
* this class implements the Blinky behaviour.
*
*/
public class GhostBlinkyBehaviour extends GhostAbstractBehaviour {

    public GhostBlinkyBehaviour(final Set<Pair<Integer, Integer>> setWall, final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize, final Pair<Integer, Integer> relaxTarget) {
        super(setWall, xMapSize, yMapSize);
        this.setRelaxTarget(relaxTarget);
        this.setStartPosition(ghostHouse.get(0));
    }

    @Override
    public final void chase(final PacMan pacMan, final Optional<Pair<Integer, Integer>> blinkyPosition) {
        if (!moveIfStuck()) {
            this.findPath(pacMan.getPosition());
            this.move(pacMan.getPosition(), 1);
        }
    }

}
