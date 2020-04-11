package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * this class implements the Pinky behaviour.
 *
 */
public class GhostPinkyBehaviour extends GhostAbstractBehaviour {

    private Pair<Integer, Integer> chaseTarget;
    private final Set<Pair<Integer, Integer>> setWall;
    private final List<Pair<Integer, Integer>> ghostHouse;

    public GhostPinkyBehaviour(final Set<Pair<Integer, Integer>> setWall, final Set<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize, final Pair<Integer, Integer> relaxTarget) {
        super(setWall, xMapSize, yMapSize);
        this.setWall = setWall;
        this.setRelaxTarget(relaxTarget);
        this.ghostHouse = new ArrayList<>(ghostHouse);
        this.setStartPosition(this.ghostHouse.get(2));
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
    public final void chase(final PacMan pacMan, final Optional<Pair<Integer, Integer>> blinkyPosition) {
        if (!moveIfStuck()) {
            this.targetPosition(pacMan);
            super.findPath(this.chaseTarget);
            super.move(this.chaseTarget, 1);
        }
    }
}
