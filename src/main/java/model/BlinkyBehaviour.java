package model;
import java.util.Optional;
import java.util.Set;

public class BlinkyBehaviour extends GhostAbstractBehaviour {

    public BlinkyBehaviour(final Set<PairImpl<Integer, Integer>> setWall, final int xMapSize, final int yMapSize, final PairImpl<Integer, Integer> relaxTarget) {
        super(setWall, xMapSize, yMapSize);
        this.setRelaxTarget(relaxTarget);
    }

    public final void chase(final PacMan pacMan, final Optional<PairImpl<Integer, Integer>> blinkyPosition) {
        this.findPath(pacMan.getPosition());
        this.move(pacMan.getPosition(), 1);
    }

}
