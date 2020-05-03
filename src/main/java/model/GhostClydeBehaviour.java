package model;


import java.util.List;
import java.util.Set;

import utils.Pair;
import utils.PairImpl;

/**
* this class implements the Clyde behaviour.
*
*/
public class GhostClydeBehaviour extends GhostBraveAbstractBehaviour {

    private Pair<Integer, Integer> chaseTarget;
    private static final int PACMANRADIUS = 7;
    private final Set<Pair<Integer, Integer>> setWall;

    public GhostClydeBehaviour(final Set<Pair<Integer, Integer>> setWall, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> relaxTarget, final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setRelaxTarget(relaxTarget);
        this.setWall = setWall;
    }

    private void targetPosition(final PacMan pacMan) {
        if (this.getCurrentPosition().getX() > pacMan.getPosition().getX() - PACMANRADIUS && this.getCurrentPosition().getX() < pacMan.getPosition().getX() + PACMANRADIUS 
            && this.getCurrentPosition().getY() > pacMan.getPosition().getY() - PACMANRADIUS && this.getCurrentPosition().getY() < pacMan.getPosition().getY() + PACMANRADIUS) {
            this.chaseTarget = this.getRelaxTarget();
            if (this.getCurrentPosition().equals(this.chaseTarget)) {
                if (this.chaseTarget.getY() + 1 < this.getyMapSize() && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() + 1))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() + 1);
                } else if (this.chaseTarget.getX() + 1 < this.getxMapSize() && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX() + 1, this.chaseTarget.getY()))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX() + 1, this.chaseTarget.getY());
                } else if (this.chaseTarget.getX() - 1 >= 0 && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX() - 1, this.chaseTarget.getY()))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX() - 1, this.chaseTarget.getY());
                } else if (this.chaseTarget.getY() - 1 >= 0 && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() - 1))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() - 1);
                }
            }
        } else {
            this.chaseTarget = pacMan.getPosition();
        }
    }

    @Override
    public final void nextPosition(final boolean eatable, final boolean timeToTurn, final boolean oldLevel) {
        if (eatable || oldLevel) {
            this.getMyFrightenedBehaviour().nextPosition(eatable, timeToTurn, oldLevel);
            this.setCurrentPosition(this.getMyFrightenedBehaviour().getCurrentPosition());
            this.setCurrentDirection(this.getMyFrightenedBehaviour().getCurrentDirection());
        } else {
            if (this.isRelaxed()) {
                super.nextPosition(eatable, timeToTurn, oldLevel);
            } else {
                if (!moveIfStuck()) {
                    this.targetPosition(this.getPacMan());
                    super.findPath(this.chaseTarget);
                    super.move(this.chaseTarget);
                }
            }
            this.getMyFrightenedBehaviour().setCurrentDirection(this.getCurrentDirection());
        }
    }
}
