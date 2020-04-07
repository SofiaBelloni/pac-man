package model;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
* this class implements the Clyde behaviour.
*
*/
public class GhostClydeBehaviour extends GhostAbstractBehaviour {

    private Pair<Integer, Integer> chaseTarget;
    private static final int PACMANRADIUS = 7;

    public GhostClydeBehaviour(final Set<Pair<Integer, Integer>> setWall, final List<Pair<Integer, Integer>> ghostHouse, final int xMap, final int yMap, final Pair<Integer, Integer> relaxTarget) {
        super(setWall, xMap, yMap);
        this.setRelaxTarget(relaxTarget);
        this.setStartPosition(ghostHouse.get(1));
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
    public final void chase(final PacMan pacMan, final Optional<Pair<Integer, Integer>> blinkyPosition) {
        if (!moveIfStuck()) {
            this.targetPosition(pacMan);
            this.findPath(this.chaseTarget);
            this.move(this.chaseTarget, 1);
        }
    }
}
