package model;

import java.util.List;
import java.util.Set;

import utils.Directions;
import utils.Pair;
import utils.PairImpl;

/**
 * this class implements the Pinky behaviour.
 *
 */
public class GhostPinkyBehaviour extends GhostFinalAbstractBehaviour {

    private Pair<Integer, Integer> chaseTarget;

    public GhostPinkyBehaviour(final Set<Pair<Integer, Integer>> setWall, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> relaxTarget, final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setRelaxTarget(relaxTarget);
    }

    @Override
    protected final Pair<Integer, Integer> getChaseTarget() {
        final Set<Pair<Integer, Integer>> walls = this.getWalls();
        final Pair<Integer, Integer> pacManPosition = this.getPacMan().getPosition();
        final Directions pacManDirection = this.getPacMan().getDirection();
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
        return this.chaseTarget;
    }
}
