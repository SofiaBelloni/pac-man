package model;
import java.util.Optional;
import java.util.Set;

public class BraveClydeBehaviour extends BraveAbstractBehaviour {

    private Pair<Integer, Integer> chaseTarget;
    private static final int PACMANRADIUS = 7;

    public BraveClydeBehaviour(Set<Pair<Integer, Integer>> setWall, int xMap, int yMap, Pair<Integer, Integer> relaxTarget) {
        super(setWall, xMap, yMap);
        this.relaxTarget = relaxTarget;
    }

    private void targetPosition(PacMan pacMan){
        if (this.currentPosition.getX( )> pacMan.getPosition().getX() - PACMANRADIUS && this.currentPosition.getX() < pacMan.getPosition().getX() + PACMANRADIUS 
            && this.currentPosition.getY() > pacMan.getPosition().getY() - PACMANRADIUS && this.currentPosition.getY() < pacMan.getPosition().getY() + PACMANRADIUS) {
            this.chaseTarget = this.relaxTarget;
        } else {
            this.chaseTarget = pacMan.getPosition();
        }
    }
    
    @Override
    public void chase(PacMan pacMan, Optional<Pair<Integer, Integer>> blinkyPosition) {
        this.targetPosition(pacMan);
        this.findPath(this.chaseTarget);
        this.move(this.chaseTarget, 1);
    }
}
