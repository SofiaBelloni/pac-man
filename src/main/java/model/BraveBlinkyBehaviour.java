package model;
import java.util.Optional;
import java.util.Set;

public class BraveBlinkyBehaviour extends BraveAbstractBehaviour {

	public BraveBlinkyBehaviour(Set<Pair<Integer, Integer>> setWall, int xMap, int yMap) {
		super(setWall, xMap, yMap);
	}
	
	public void chase(Pair<Integer, Integer> currentPosition, PacMan pacMan, Directions dir, Optional<Pair<Integer, Integer>> blinkyPosition) {
			super.findPath(currentPosition, pacMan.getPosition(), dir);
			super.move(currentPosition, pacMan.getPosition(), 1);
	}


}
