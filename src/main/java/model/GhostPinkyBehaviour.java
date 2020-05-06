package model;

import java.util.List;
import java.util.Set;
import utils.Pair;
import utils.PairImpl;

/**
 * this class implements the Pinky behaviour.
 *
 */
public class GhostPinkyBehaviour extends GhostSmartAbstractBehaviour implements GhostBehaviour {

    private Pair<Integer, Integer> chaseTarget;

    public GhostPinkyBehaviour(final Set<Pair<Integer, Integer>> setWall, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> relaxTarget, final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setRelaxTarget(relaxTarget);
    }

    private void targetPosition(final PacMan pacMan) {
        final Set<Pair<Integer, Integer>> walls = this.getWalls();
        final Pair<Integer, Integer> pacManPosition = pacMan.getPosition();
        final Directions pacManDirection = pacMan.getDirection();
        for (int i = 0; i <= 4; i++) {
            if (pacManDirection.equals(Directions.UP)) {
                if (!walls.contains(new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() - i)) && pacManPosition.getY() - i >= 0) {
                    this.chaseTarget = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() - i);
                }
            } else if (pacManDirection.equals(Directions.RIGHT)) {
                if (!walls.contains(new PairImpl<>(pacManPosition.getX() + i, pacManPosition.getY())) && pacManPosition.getX() + i < getxMapSize()) {
                    this.chaseTarget = new PairImpl<>(pacManPosition.getX() + i, pacManPosition.getY());
                }
            } else if (pacManDirection.equals(Directions.DOWN)) {
                if (!walls.contains(new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() + i)) && pacManPosition.getY() + i < getyMapSize()) {
                    this.chaseTarget = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() + i);
                }
            } else {
                if (!walls.contains(new PairImpl<>(pacManPosition.getX() - i, pacManPosition.getY())) && pacManPosition.getX() - i >= 0) {
                    this.chaseTarget = new PairImpl<>(pacManPosition.getX() - i, pacManPosition.getY());
                }
            }
        }

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
    }

    @Override
    public final void nextPosition(final boolean eatable, final boolean timeToTurn, final boolean oldLevel) {
        if ((eatable || oldLevel) && !this.isRelaxed()) {
            this.getRandomBehaviour().nextPosition(eatable, timeToTurn, oldLevel);
            this.setCurrentPosition(this.getRandomBehaviour().getCurrentPosition());
            this.setCurrentDirection(this.getRandomBehaviour().getCurrentDirection());
        } else {
            if (this.isRelaxed()) {
                this.relax(oldLevel, eatable);
            } else {
                if (!moveIfStuck()) {
                    this.targetPosition(this.getPacMan());
                    this.findPath(this.chaseTarget);
                    this.move(this.chaseTarget);
                }
            }
                this.getRandomBehaviour().setCurrentDirection(this.getCurrentDirection());
        }
    }
}
