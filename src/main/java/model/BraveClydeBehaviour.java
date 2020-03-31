package model;
import java.util.Optional;
import java.util.Set;

public class BraveClydeBehaviour extends BraveAbstractBehaviour {

	private Pair<Integer, Integer> chaseTarget;
	private Pair<Integer, Integer> relaxTarget;

	public BraveClydeBehaviour(Set<Pair<Integer, Integer>> setWall, int xMap, int yMap, Pair<Integer, Integer> relaxTarget) {
		super(setWall, xMap, yMap);
		this.relaxTarget = relaxTarget;
	}

	private void targetPosition(PacMan pacMan, Pair<Integer, Integer> currentPosition){
		if (currentPosition.getX( )> pacMan.getPosition().getX() - 7 && currentPosition.getX() < pacMan.getPosition().getX() + 7 
			&& currentPosition.getY() > pacMan.getPosition().getY() - 7 && currentPosition.getY() < pacMan.getPosition().getY() + 7) {
			chaseTarget = relaxTarget;
		} else {
			chaseTarget = pacMan.getPosition();
		}
	}
	
	@Override
	public void chase(Pair<Integer, Integer> currentPosition, PacMan pacMan, Directions dir, Optional<Pair<Integer, Integer>> blinkyPosition) {
		this.targetPosition(pacMan, currentPosition);
		super.findPath(currentPosition, chaseTarget, dir);
		super.move(currentPosition, chaseTarget, 1);
	}
}
