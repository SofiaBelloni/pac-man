package model;

import java.util.Optional;
import java.util.Set;

public class PacManImpl extends MobileEntityAbstractImpl implements PacMan {

    private Optional<Directions> currentDirection;
    private int lives;

    public PacManImpl(final int xMapSize, final int yMapSize, final Pair<Integer, Integer> startPosition,
            final int lives, final Set<Pair<Integer, Integer>> noWalls) {
        super(xMapSize, yMapSize, startPosition, noWalls);
        this.currentDirection = Optional.empty();
        this.lives = lives;
    }

    @Override
    public final void nextPosition() {
        if (!this.currentDirection.isEmpty()) {
            Pair<Integer, Integer> next = this.convertToToroidal(this.calculateNextPosition());
            if (this.getNoWalls().contains(next)) {
                this.setPosition(next);
            }
        }
    }

    private Pair<Integer, Integer> calculateNextPosition() {
        int x = 0;
        int y = 0;
        switch (this.currentDirection.get()) {
        case UP:
            y = 1;
            break;
        case DOWN:
            y = -1;
            break;
        case LEFT:
            x = -1;
            break;
        case RIGHT:
            x = 1;
            break;
        default:
            //bisogna gestirlo in qualche modo
            break;
        }
        return new Pair<Integer, Integer>(this.getPosition().getX() + x, this.getPosition().getY() + y);
    }

    private Pair<Integer, Integer> convertToToroidal(final Pair<Integer, Integer> position) {
        int newX = position.getX();
        int newY = position.getY();
        if (newX >= this.getxMapSize()) {
            newX = 0;
        }
        if (newY >= this.getyMapSize()) {
            newY = 0;
        }
        if (newX < 0) {
            newX = this.getxMapSize() - 1;
        }
        if (newY < 0) {
            newY = this.getyMapSize() - 1;
        }
        return new Pair<Integer, Integer>(newX, newY);
    }

    @Override
    public final void setDirection(final Optional<Directions> direction) {
        this.currentDirection = direction;
    }

    @Override
    public final Optional<Directions> getDirection() {
        return this.currentDirection;
    }

    @Override
    public final int getLives() {
        return this.lives;
    }
}
