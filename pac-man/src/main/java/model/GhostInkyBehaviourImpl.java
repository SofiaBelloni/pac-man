package model;

import java.util.List;
import java.util.Set;

import utils.Directions;
import utils.Pair;
import utils.PairImpl;

/**
* this class implements the Inky behaviour.
*
*/
public class GhostInkyBehaviourImpl extends GhostFinalAbstractBehaviour {

    private Pair<Integer, Integer> chaseTarget;
    private final Ghost blinky;

    public GhostInkyBehaviourImpl(final Ghost blinky, final Set<Pair<Integer, Integer>> setWall,
            final PacMan pacMan, final List<Pair<Integer, Integer>> ghostHouse,
            final int xMapSize, final int yMapSize, final Pair<Integer, Integer> relaxTarget,
            final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.blinky = blinky;
        this.setRelaxTarget(relaxTarget);
    }

    @Override
    protected final Pair<Integer, Integer> getChaseTarget() {
        if (this.isBlinkyDead()) {
            return this.getPacMan().getPosition();
        } else {
            final Set<Pair<Integer, Integer>> walls = this.getWalls();
            final Pair<Integer, Integer> pacManPosition = this.getPacMan().getPosition();
            final Directions pacManDirection = this.getPacMan().getDirection();
            final Pair<Integer, Integer> blinkyPosition = this.blinky.getPosition();
            Pair<Integer, Integer> appo;
            int targetX;
            int targetY;
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
            if (this.chaseTarget.getX() >= getxMapSize() - 1) {
                this.chaseTarget = new PairImpl<>(getxMapSize() - 2, this.chaseTarget.getY());
            }
            if (this.chaseTarget.getX() < 1) {
                this.chaseTarget = new PairImpl<>(1, this.chaseTarget.getY());
            }
            if (this.chaseTarget.getY() >= getyMapSize() - 1) {
                this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), getyMapSize() - 2);
            }
            if (this.chaseTarget.getY() < 0) {
                this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), 1);
            }
            if (this.getCurrentPosition().equals(this.chaseTarget) || walls.contains(this.chaseTarget)) {
                if (this.chaseTarget.getY() + 1 < this.getyMapSize() && !walls.contains(new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() + 1))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() + 1);
                } else if (this.chaseTarget.getX() + 1 < this.getxMapSize() && !walls.contains(new PairImpl<>(this.chaseTarget.getX() + 1, this.chaseTarget.getY()))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX() + 1, this.chaseTarget.getY());
                } else if (this.chaseTarget.getX() - 1 >= 0 && !walls.contains(new PairImpl<>(this.chaseTarget.getX() - 1, this.chaseTarget.getY()))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX() - 1, this.chaseTarget.getY());
                } else if (this.chaseTarget.getY() - 1 >= 0 && !walls.contains(new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() - 1))) {
                    this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() - 1);
                } else {
                    this.chaseTarget = pacManPosition;
                }
            }
            return this.chaseTarget;
        }
    }

}
