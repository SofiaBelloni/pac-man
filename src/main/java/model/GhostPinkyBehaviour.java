package model;


import java.util.List;
import java.util.Set;

/**
 * this class implements the Pinky behaviour.
 *
 */
public class GhostPinkyBehaviour extends GhostBraveAbstractBehaviour implements GhostBehaviour {

    private Pair<Integer, Integer> chaseTarget;
    private final Set<Pair<Integer, Integer>> setWall;

    public GhostPinkyBehaviour(final Set<Pair<Integer, Integer>> setWall, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> relaxTarget, final Pair<Integer, Integer> startPosition) {
        super(setWall, pacMan, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setWall = setWall;
        this.setRelaxTarget(relaxTarget);
    }

    private void targetPosition(final PacMan pacMan) {
        final Pair<Integer, Integer> pacManPosition = pacMan.getPosition();
        final Directions pacManDirection = pacMan.getDirection();
        for (int i = 0; i <= 4; i++) {
            if (pacManDirection.equals(Directions.UP)) {
                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() + i)) && pacManPosition.getY() + i < getyMapSize()) {
                    this.chaseTarget = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() + i);
                }
            } else if (pacManDirection.equals(Directions.RIGHT)) {
                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX() + i, pacManPosition.getY())) && pacManPosition.getX() + i < getxMapSize()) {
                    this.chaseTarget = new PairImpl<>(pacManPosition.getX() + i, pacManPosition.getY());
                }
            } else if (pacManDirection.equals(Directions.DOWN)) {
                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() - i)) && pacManPosition.getY() - i >= 0) {
                    this.chaseTarget = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() - i);
                }
            } else {
                if (!this.setWall.contains(new PairImpl<>(pacManPosition.getX() - i, pacManPosition.getY())) && pacManPosition.getX() - i >= 0) {
                    this.chaseTarget = new PairImpl<>(pacManPosition.getX() - i, pacManPosition.getY());
                }
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
