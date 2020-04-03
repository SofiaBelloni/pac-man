package model;
import java.util.Optional;
import java.util.Set;

public class PinkyBehaviour extends GhostAbstractBehaviour {

	private PairImpl<Integer, Integer> chaseTarget;
	private final Set<PairImpl<Integer, Integer>> setWall;

	public PinkyBehaviour(final Set<PairImpl<Integer, Integer>> setWall, final int xMapSize, final int yMapSize, final PairImpl<Integer, Integer> relaxTarget) {
		super(setWall, xMapSize, yMapSize);
		this.setWall = setWall;
		this.setRelaxTarget(relaxTarget);
	}
	
	   private void targetPosition(final PacMan pacMan) {
	        Pair<Integer, Integer> pacManPosition = pacMan.getPosition();
	        Directions pacManDirection = pacMan.getDirection();
	        for (int i = 0; i <= 4; i++) {
	            if (pacManDirection.equals(Directions.UP)) {
	                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() + i)) && pacManPosition.getY() + i < getyMapSize()) {
	                    this.chaseTarget = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() + i);
	                }
	            } else if (pacManDirection.equals(Directions.RIGHT)) {
	                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX() + i, pacManPosition.getY())) && pacManPosition.getX() + i < getxMapSize()) {
	                    this.chaseTarget = new PairImpl<>(pacManPosition.getX() + i, pacManPosition.getY());
	                }
	            } else if (pacManDirection.equals(Directions.DOWN)) {
	                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() - i)) && pacManPosition.getY() - i >= 0) {
	                    this.chaseTarget = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() - i);
	                }
	            } else {
	                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX() - i, pacManPosition.getY())) && pacManPosition.getX() - i >= 0) {
	                    this.chaseTarget = new PairImpl<>(pacManPosition.getX() - i, pacManPosition.getY());
	                }
	            }
	        }
	    }

	    @Override
	    public final void chase(final PacMan pacMan, final Optional<PairImpl<Integer, Integer>> blinkyPosition) {
	        this.targetPosition(pacMan);
	        super.findPath(this.chaseTarget);
	        super.move(this.chaseTarget, 1);
	    }

}
