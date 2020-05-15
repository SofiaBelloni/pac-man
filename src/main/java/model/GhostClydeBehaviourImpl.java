package model;

import java.util.List;
import java.util.Set;
import utils.Pair;
import utils.PairImpl;

/**
* this class implements the Clyde behaviour.
*
*/
public class GhostClydeBehaviourImpl extends GhostFinalAbstractBehaviour {

    private Pair<Integer, Integer> chaseTarget;
    private static final int PACMANRADIUS = 7;

    public GhostClydeBehaviourImpl(final Set<Pair<Integer, Integer>> setWall, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> relaxTarget, final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setRelaxTarget(relaxTarget);
    }

    @Override
    protected final Pair<Integer, Integer> getChaseTarget() {
        final Set<Pair<Integer, Integer>> walls = this.getWalls();
        if (this.getCurrentPosition().getX() > this.getPacMan().getPosition().getX() - PACMANRADIUS && this.getCurrentPosition().getX() < this.getPacMan().getPosition().getX() + PACMANRADIUS 
            && this.getCurrentPosition().getY() > this.getPacMan().getPosition().getY() - PACMANRADIUS && this.getCurrentPosition().getY() < this.getPacMan().getPosition().getY() + PACMANRADIUS) {
            this.chaseTarget = this.getRelaxTarget();
            if (this.getCurrentPosition().equals(this.chaseTarget)) {
                if (this.chaseTarget.getY() + 1 < this.getyMapSize() && !walls.contains(new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() + 1))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() + 1);
                } else if (this.chaseTarget.getX() + 1 < this.getxMapSize() && !walls.contains(new PairImpl<>(this.chaseTarget.getX() + 1, this.chaseTarget.getY()))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX() + 1, this.chaseTarget.getY());
                } else if (this.chaseTarget.getX() - 1 >= 0 && !walls.contains(new PairImpl<>(this.chaseTarget.getX() - 1, this.chaseTarget.getY()))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX() - 1, this.chaseTarget.getY());
                } else if (this.chaseTarget.getY() - 1 >= 0 && !walls.contains(new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() - 1))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() - 1);
                }
            }
            return this.chaseTarget;
        } else {
            return this.getPacMan().getPosition();
        }
    }
}
