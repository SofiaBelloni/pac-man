package model;
import java.util.Optional;
import java.util.Set;

public class ClydeBehaviour extends GhostAbstractBehaviour {

    private PairImpl<Integer, Integer> chaseTarget;
    private static final int PACMANRADIUS = 7;

    public ClydeBehaviour(final Set<PairImpl<Integer, Integer>> setWall, final int xMap, final int yMap, final PairImpl<Integer, Integer> relaxTarget) {
        super(setWall, xMap, yMap);
        this.setRelaxTarget(relaxTarget);
    }

    private void targetPosition(final PacMan pacMan) {
        if (this.getCurrentPosition().getX() > pacMan.getPosition().getX() - PACMANRADIUS && this.getCurrentPosition().getX() < pacMan.getPosition().getX() + PACMANRADIUS 
            && this.getCurrentPosition().getY() > pacMan.getPosition().getY() - PACMANRADIUS && this.getCurrentPosition().getY() < pacMan.getPosition().getY() + PACMANRADIUS) {
            this.chaseTarget = this.getRelaxTarget();
        } else {
            this.chaseTarget = pacMan.getPosition();
        }
    }

    @Override
    public final void chase(final PacMan pacMan, final Optional<PairImpl<Integer, Integer>> blinkyPosition) {
        if (!moveIfStuck()) {
            this.targetPosition(pacMan);
            this.findPath(this.chaseTarget);
            this.move(this.chaseTarget, 1);
        }
    }
}
