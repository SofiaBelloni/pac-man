package model;

import java.util.Optional;
import java.util.Set;

public class PacManImpl extends MobileEntityAbstractImpl implements PacMan {

    private Optional<Directions> currentDirection;

    public PacManImpl(final int xMapSize, final int yMapSize, final Pair<Integer, Integer> startPosition, final Set<Pair<Integer, Integer>> noWalls) {
        super(xMapSize, yMapSize, startPosition, noWalls);
        this.currentDirection = Optional.empty();
    }

    @Override
    public final void nextPosition() {
        if (!this.currentDirection.isEmpty()) {
            
        }
    }

    @Override
    public final void setDirection(final Optional<Directions> direction) {
        this.currentDirection = direction;
    }

    @Override
    public final Optional<Directions> getDirection() {
        return this.currentDirection;
    }
}
