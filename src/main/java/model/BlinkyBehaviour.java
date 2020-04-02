package model;
import java.util.Optional;
import java.util.Set;

public class BlinkyBehaviour extends GhostAbstractBehaviour {

    public BlinkyBehaviour(Set<PairImpl<Integer, Integer>> setWall, int xMap, int yMap, PairImpl<Integer, Integer> relaxTarget) {
        super(setWall, xMap, yMap);
        this.relaxTarget = relaxTarget;
    }

    public void chase(PacMan pacMan, Optional<PairImpl<Integer, Integer>> blinkyPosition) {
        this.findPath(pacMan.getPosition());
        this.move(pacMan.getPosition(), 1);
    }
}
