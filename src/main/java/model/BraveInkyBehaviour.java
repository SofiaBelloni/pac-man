package model;
import java.util.Optional;
import java.util.Set;

public class BraveInkyBehaviour extends BraveAbstractBehaviour {

	private Pair<Integer, Integer> chaseTarget;
	private final Set<Pair<Integer, Integer>> setWall;
	
	public BraveInkyBehaviour(Set<Pair<Integer, Integer>> setWall, int xMap, int yMap) {
		super(setWall, xMap, yMap);
		this.setWall = setWall;
	}
	
	private void targetPosition(PacMan pacMan, Optional<Pair<Integer, Integer>> blinkyPosition, Pair<Integer, Integer> currentPosition){
		Pair<Integer, Integer> pacManPosition = pacMan.getPosition();
		Pair<Integer, Integer> appo;
		int targetX;
		int targetY;
		Directions pacManDirection = pacMan.getDirection();
		if (pacManDirection.equals(Directions.UP)) {
			appo = new Pair<>(pacManPosition.getX(), pacManPosition.getY() + 2);
		} else if (pacManDirection.equals(Directions.RIGHT)) {
			appo = new Pair<>(pacManPosition.getX() + 2, pacManPosition.getY());
		} else if (pacManDirection.equals(Directions.DOWN)) {
			appo = new Pair<>(pacManPosition.getX(), pacManPosition.getY() - 2);
		} else {
			appo = new Pair<>(pacManPosition.getX() - 2, pacManPosition.getY());
		}
		if(blinkyPosition.get().getX()<=appo.getX()) {
			targetX=blinkyPosition.get().getX() + (Math.abs(appo.getX() - blinkyPosition.get().getX())*2);
		} else {
			targetX=blinkyPosition.get().getX() - (Math.abs(appo.getX() - blinkyPosition.get().getX())*2);
		}
		
		if(blinkyPosition.get().getY()<=appo.getY()) {
			targetY=blinkyPosition.get().getY() + (Math.abs(appo.getY() - blinkyPosition.get().getY())*2);
		} else {
			targetY=blinkyPosition.get().getY() - (Math.abs(appo.getY() - blinkyPosition.get().getY())*2);
		}
		chaseTarget = new Pair<>(targetX, targetY);
		
		if (chaseTarget.getX()>xMap) {
			chaseTarget = new Pair<>(xMap, chaseTarget.getY());
		}
		if (chaseTarget.getX()<0) {
			chaseTarget = new Pair<>(0, chaseTarget.getY());
		}
		if (chaseTarget.getY()>yMap) {
			chaseTarget = new Pair<>(chaseTarget.getX(), yMap);
		}
		if (chaseTarget.getY()<0) {
			chaseTarget = new Pair<>(chaseTarget.getY(), 0);
		}	
		if (setWall.contains(chaseTarget) || currentPosition.equals(chaseTarget)) {
			setAdj(chaseTarget);
			if (!setWall.contains(up)) {
				chaseTarget = up;
			} else if (!setWall.contains(right)) {
				chaseTarget = right;
			} else if (!setWall.contains(down)) {
				chaseTarget = down;
			} else {
				chaseTarget = left;
			}
		}
	}

	@Override
	public void chase(Pair<Integer, Integer> currentPosition, PacMan pacMan, Directions dir, Optional<Pair<Integer, Integer>> blinkyPosition) {
		this.targetPosition(pacMan, blinkyPosition, currentPosition);
		super.findPath(currentPosition, chaseTarget, dir);
		super.move(currentPosition, chaseTarget, 1);
	}

}
