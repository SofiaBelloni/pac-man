package model;
import java.util.Optional;
import java.util.Set;

public class BravePinkyBehaviour extends BraveAbstractBehaviour {
	
	private Pair<Integer, Integer> chaseTarget;
	private final Set<Pair<Integer, Integer>> setWall;

	public BravePinkyBehaviour(Set<Pair<Integer, Integer>> setWall, int xMap, int yMap) {
		super(setWall, xMap, yMap);
		this.setWall = setWall;
	}
	
	private void targetPosition(PacMan pacMan, Pair<Integer, Integer> currentPosition){
		Pair<Integer, Integer> pacManPosition = pacMan.getPosition();
		Directions pacManDirection = pacMan.getDirection();
		for (int i = 0; i <= 4; i++) {
			if (pacManDirection.equals(Directions.UP)) {
				if (!setWall.contains(new Pair<>(pacManPosition.getX(), pacManPosition.getY() + i)) && pacManPosition.getY() + i <= yMap){
					chaseTarget = new Pair<>(pacManPosition.getX(), pacManPosition.getY() + i);
				}
			} else if (pacManDirection.equals(Directions.RIGHT)) {
				if (!setWall.contains(new Pair<>(pacManPosition.getX() + i, pacManPosition.getY())) && pacManPosition.getX() + i <= xMap){
					chaseTarget = new Pair<>(pacManPosition.getX() + i, pacManPosition.getY());
				}
			} else if (pacManDirection.equals(Directions.DOWN)) {
				if (!setWall.contains(new Pair<>(pacManPosition.getX(), pacManPosition.getY() - i)) && pacManPosition.getY() - i >= 0){
					chaseTarget = new Pair<>(pacManPosition.getX(), pacManPosition.getY() - i);
				}
			} else {
				if (!setWall.contains(new Pair<>(pacManPosition.getX() - i, pacManPosition.getY())) && pacManPosition.getX() - i >= 0){
					chaseTarget = new Pair<>(pacManPosition.getX() - i, pacManPosition.getY());
				}
			}
		}
	}
	
	@Override
	public void chase(Pair<Integer, Integer> currentPosition, PacMan pacMan, Directions dir, Optional<Pair<Integer,Integer>> blinkyPosition) {
		this.targetPosition(pacMan, currentPosition);
		super.findPath(currentPosition, chaseTarget, dir);
		super.move(currentPosition, chaseTarget, 1);
	}

}
