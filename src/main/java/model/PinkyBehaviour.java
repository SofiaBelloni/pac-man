package model;
import java.util.Optional;
import java.util.Set;

public class PinkyBehaviour extends GhostAbstractBehaviour {
	
	private PairImpl<Integer, Integer> chaseTarget;
	private final Set<PairImpl<Integer, Integer>> setWall;

	public PinkyBehaviour(Set<PairImpl<Integer, Integer>> setWall, int xMap, int yMap, PairImpl<Integer, Integer> relaxTarget) {
		super(setWall, xMap, yMap);
		this.setWall = setWall;
		this.relaxTarget = relaxTarget;
	}
	
	   private void targetPosition(PacMan pacMan){
	        Pair<Integer, Integer> pacManPosition = pacMan.getPosition();
	        Directions pacManDirection = pacMan.getDirection();
	        for (int i = 0; i <= 4; i++) {
	            if (pacManDirection.equals(Directions.UP)) {
	                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() + i)) && pacManPosition.getY() + i <= yMap){
	                    this.chaseTarget = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() + i);
	                }
	            } else if (pacManDirection.equals(Directions.RIGHT)) {
	                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX() + i, pacManPosition.getY())) && pacManPosition.getX() + i <= xMap){
	                    this.chaseTarget = new PairImpl<>(pacManPosition.getX() + i, pacManPosition.getY());
	                }
	            } else if (pacManDirection.equals(Directions.DOWN)) {
	                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() - i)) && pacManPosition.getY() - i >= 0){
	                    this.chaseTarget = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() - i);
	                }
	            } else {
	                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX() - i, pacManPosition.getY())) && pacManPosition.getX() - i >= 0){
	                    this.chaseTarget = new PairImpl<>(pacManPosition.getX() - i, pacManPosition.getY());
	                }
	            }
	        }
	    }
	    
	    @Override
	    public void chase(PacMan pacMan,Optional<PairImpl<Integer,Integer>> blinkyPosition) {
	        this.targetPosition(pacMan);
	        super.findPath(chaseTarget);
	        super.move(chaseTarget, 1);
	    }

}
