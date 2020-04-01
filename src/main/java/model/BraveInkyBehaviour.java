package model;
import java.util.Optional;
import java.util.Set;

public class BraveInkyBehaviour extends BraveAbstractBehaviour {

	private Pair<Integer, Integer> chaseTarget;
	private final Set<Pair<Integer, Integer>> setWall;
	
	public BraveInkyBehaviour(Set<Pair<Integer, Integer>> setWall, int xMap, int yMap, Pair<Integer, Integer> relaxTarget) {
		super(setWall, xMap, yMap);
		this.setWall = setWall;
	    this.relaxTarget = relaxTarget;
	}
	
	private void targetPosition(PacMan pacMan, Optional<Pair<Integer, Integer>> blinkyPosition){
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
        if(blinkyPosition.get().getX() <= appo.getX()) {
            targetX=blinkyPosition.get().getX() + (Math.abs(appo.getX() - blinkyPosition.get().getX())*2);
        } else {
            targetX=blinkyPosition.get().getX() - (Math.abs(appo.getX() - blinkyPosition.get().getX())*2);
        }
        
        if(blinkyPosition.get().getY() <= appo.getY()) {
            targetY=blinkyPosition.get().getY() + (Math.abs(appo.getY() - blinkyPosition.get().getY())*2);
        } else {
            targetY=blinkyPosition.get().getY() - (Math.abs(appo.getY() - blinkyPosition.get().getY())*2);
        }
        this.chaseTarget = new Pair<>(targetX, targetY);
        
        if (this.chaseTarget.getX() > xMap) {
            this.chaseTarget = new Pair<>(xMap, this.chaseTarget.getY());
        }
        if (this.chaseTarget.getX() < 0) {
            this.chaseTarget = new Pair<>(0, this.chaseTarget.getY());
        }
        if (this.chaseTarget.getY() > yMap) {
            this.chaseTarget = new Pair<>(this.chaseTarget.getX(), yMap);
        }
        if (this.chaseTarget.getY() < 0) {
            this.chaseTarget = new Pair<>(this.chaseTarget.getY(), 0);
        }   
        if (this.setWall.contains(this.chaseTarget) || this.currentPosition.equals(this.chaseTarget)) {
            this.setAdj(this.chaseTarget);
            if (!this.setWall.contains(this.up)) {
                this.chaseTarget = this.up;
            } else if (!this.setWall.contains(this.right)) {
                this.chaseTarget = this.right;
            } else if (!this.setWall.contains(this.down)) {
                this.chaseTarget = this.down;
            } else {
                this.chaseTarget = this.left;
            }
        }
    }

    @Override
    public void chase(PacMan pacMan, Optional<Pair<Integer, Integer>> blinkyPosition) {
        this.targetPosition(pacMan, blinkyPosition);
        this.findPath(this.chaseTarget);
        this.move(this.chaseTarget, 1);
    }


}
