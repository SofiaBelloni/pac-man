package model;
import java.util.Optional;
import java.util.Set;

public class BlinkyBehaviour extends GhostAbstractBehaviour {

    public BlinkyBehaviour(Set<Pair<Integer, Integer>> setWall, int xMap, int yMap, Pair<Integer, Integer> relaxTarget) {
        super(setWall, xMap, yMap);
        this.relaxTarget = relaxTarget;
    }

    public void chase(PacMan pacMan, Optional<Pair<Integer, Integer>> blinkyPosition) {
        this.findPath(pacMan.getPosition());
        this.move(pacMan.getPosition(), 1);
    }
}
