package model;

import java.util.List;
import java.util.Set;
import utils.Pair;
import utils.PairImpl;

/**
* this class implements the Clyde behaviour.
*
*/
public class GhostClydeBehaviour extends GhostFinalAbstractBehaviour {

    private Pair<Integer, Integer> chaseTarget;
    private static final int PACMANRADIUS = 7;

    public GhostClydeBehaviour(final Set<Pair<Integer, Integer>> setWall, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> relaxTarget, final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setRelaxTarget(relaxTarget);
    }

    private void targetPosition(final PacMan pacMan) {
        final Set<Pair<Integer, Integer>> walls = this.getWalls();
        if (this.getCurrentPosition().getX() > pacMan.getPosition().getX() - PACMANRADIUS && this.getCurrentPosition().getX() < pacMan.getPosition().getX() + PACMANRADIUS 
            && this.getCurrentPosition().getY() > pacMan.getPosition().getY() - PACMANRADIUS && this.getCurrentPosition().getY() < pacMan.getPosition().getY() + PACMANRADIUS) {
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
        } else {
            this.chaseTarget = pacMan.getPosition();
        }
    }

    @Override
    public final void nextPosition(final boolean eatable, final boolean timeToTurn, final Ghosts name) {
        this.checkIfInside();
        if ((eatable || name.equals(Ghosts.OLDLEVEL)) && !this.isRelaxed()) {
            if (timeToTurn || !moveIfStuck()) {
                this.getRandomBehaviour().move(timeToTurn);
                this.setCurrentPosition(this.getRandomBehaviour().getCurrentPosition());
                this.setCurrentDirection(this.getRandomBehaviour().getCurrentDirection());
            }
        } else {
            if (this.isInside() || !moveIfStuck()) {
                if (this.isRelaxed()) {
                    this.relax(name, eatable);
                } else {
                    this.targetPosition(this.getPacMan());
                    super.findPath(this.chaseTarget);
                    super.move(this.chaseTarget);
                }
            }
        }
    }
}
