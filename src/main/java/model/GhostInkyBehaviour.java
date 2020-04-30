package model;


import java.util.List;
import java.util.Set;

import utils.Pair;
import utils.PairImpl;

/**
* this class implements the Inky behaviour.
*
*/
public class GhostInkyBehaviour extends GhostBraveAbstractBehaviour {

    private final Set<Pair<Integer, Integer>> setWall;
    private Pair<Integer, Integer> chaseTarget;
    private final Ghost blinky;

    public GhostInkyBehaviour(final Ghost blinky, final Set<Pair<Integer, Integer>> setWall,
            final PacMan pacMan, final List<Pair<Integer, Integer>> ghostHouse,
            final int xMapSize, final int yMapSize, final Pair<Integer, Integer> relaxTarget,
            final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.blinky = blinky;
        this.setWall = setWall;
        this.setRelaxTarget(relaxTarget);
    }

    private void targetPosition(final PacMan pacMan, final Pair<Integer, Integer> blinkyPosition) {
        final Pair<Integer, Integer> pacManPosition = pacMan.getPosition();
        Pair<Integer, Integer> appo;
        int targetX;
        int targetY;
        final Directions pacManDirection = pacMan.getDirection();
        if (pacManDirection.equals(Directions.UP)) {
            appo = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() - 2);
        } else if (pacManDirection.equals(Directions.RIGHT)) {
            appo = new PairImpl<>(pacManPosition.getX() + 2, pacManPosition.getY());
        } else if (pacManDirection.equals(Directions.DOWN)) {
            appo = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() + 2);
        } else {
            appo = new PairImpl<>(pacManPosition.getX() - 2, pacManPosition.getY());
        }
        if (blinkyPosition.getX() <= appo.getX()) {
            targetX = blinkyPosition.getX() + (Math.abs(appo.getX() - blinkyPosition.getX()) * 2);
        } else {
            targetX = blinkyPosition.getX() - (Math.abs(appo.getX() - blinkyPosition.getX()) * 2);
        }
        if (blinkyPosition.getY() <= appo.getY()) {
            targetY = blinkyPosition.getY() + (Math.abs(appo.getY() - blinkyPosition.getY()) * 2);
        } else {
            targetY = blinkyPosition.getY() - (Math.abs(appo.getY() - blinkyPosition.getY()) * 2);
        }
        this.chaseTarget = new PairImpl<>(targetX, targetY);
        if (this.chaseTarget.getX() >= getxMapSize()) {
            this.chaseTarget = new PairImpl<>(getxMapSize() - 1, this.chaseTarget.getY());
        }
        if (this.chaseTarget.getX() < 0) {
            this.chaseTarget = new PairImpl<>(0, this.chaseTarget.getY());
        }
        if (this.chaseTarget.getY() >= getyMapSize()) {
            this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), getyMapSize() - 1);
        }
        if (this.chaseTarget.getY() < 0) {
            this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), 0);
        }
        if (this.getCurrentPosition().equals(this.chaseTarget)) {
            if (this.chaseTarget.getY() + 1 < this.getyMapSize() 
                    && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() + 1))) {
                this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() + 1);
            } else if (this.chaseTarget.getX() + 1 < this.getxMapSize() 
                    && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX() + 1, this.chaseTarget.getY()))) {
                this.chaseTarget = new PairImpl<>(this.chaseTarget.getX() + 1, this.chaseTarget.getY());
            } else if (this.chaseTarget.getX() - 1 >= 0 
                    && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX() - 1, this.chaseTarget.getY()))) {
                this.chaseTarget = new PairImpl<>(this.chaseTarget.getX() - 1, this.chaseTarget.getY());
            } else if (this.chaseTarget.getY() - 1 >= 0 
                    && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() - 1))) {
                this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() - 1);
            }
        }
    }

    @Override
    public final void nextPosition(final boolean eatable, final boolean timeToTurn) {
        if (eatable) {
            this.getMyFrightenedBehaviour().nextPosition(eatable, timeToTurn);
            this.setCurrentPosition(this.getMyFrightenedBehaviour().getCurrentPosition());
            this.setCurrentDirection(this.getMyFrightenedBehaviour().getCurrentDirection());
        } else {
            if (this.isRelaxed()) {
                super.nextPosition(eatable, timeToTurn);
            } else {
                if (!this.moveIfStuck()) {
                    if (this.isBlinkyDead()) {
                        this.chaseTarget = this.getPacMan().getPosition();
                    } else {
                        this.targetPosition(this.getPacMan(), this.blinky.getPosition());
                    }
                    this.findPath(this.chaseTarget);
                    this.move(this.chaseTarget);
                }
                this.getMyFrightenedBehaviour().setCurrentDirection(this.getCurrentDirection());
            }
        }
    }
}
