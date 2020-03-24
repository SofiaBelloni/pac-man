package model;

import java.util.Optional;
import java.util.Set;

public class PacManImpl extends MobileEntityAbstractImpl implements PacMan {

    private Optional<Directions> currentDirection;
    private int lives;

    private PacManImpl(final int xMapSize, final int yMapSize, final Pair<Integer, Integer> startPosition,
            final int lives, final Set<Pair<Integer, Integer>> noWalls, final Optional<Directions> currentDirection) {
        super(xMapSize, yMapSize, startPosition, noWalls);
        this.currentDirection = currentDirection;
        this.lives = lives;
    }

    public static class Builder {
        private Optional<Integer> xMapSize;
        private Optional<Integer> yMapSize;
        private Optional<Pair<Integer, Integer>> startPosition;
        private Optional<Integer> lives;
        private Optional<Set<Pair<Integer, Integer>>> noWalls;
        private Optional<Directions> currentDirection;

        public Builder() {

        }
        /**
         * 
         * @param x xMapSize
         * @param y yMapSize
         * @return this
         */
        public Builder mapSize(final int x, final int y) {
            this.xMapSize = Optional.of(x);
            this.yMapSize = Optional.of(y);
            return this;
        }
        /**
         * 
         * @param startPosition a pair containing the x,y position 
         * @return this
         */
        public Builder startPosition(final Pair<Integer, Integer> startPosition) {
            this.startPosition = Optional.of(startPosition);
            return this;
        }

        /**
         * 
         * @param lives number of lives. Must be > 0
         * @return this
         */
        public Builder lives(final int lives) {
            this.lives = Optional.of(lives).filter(x -> x > 0);
            return this;
        }

        /**
         * 
         * @param noWalls a set containing the coordinates where you can go
         * @return this
         */
        public Builder noWalls(final Set<Pair<Integer, Integer>> noWalls) {
            this.noWalls = Optional.of(noWalls);
            return this;
        }

        /**
         * 
         * @param currentDirection the current direction. can be empty if pacman is stall
         * @return this
         */
        public Builder currentDirection(final Directions currentDirection) {
            this.currentDirection = Optional.of(currentDirection);
            return this;
        }


        /**
         * 
         * @return a new instance of PacManImpl
         * @throws IllegalStateException if some parameter is missed
         */
        public PacManImpl build() throws IllegalStateException {
            if (this.xMapSize == null || this.yMapSize == null || !this.lives.isPresent() || this.startPosition == null
                        || this.currentDirection == null || this.noWalls == null) {
                throw new IllegalStateException("Error while building PacMan. Please control to do it correctly.");
            }

            return new PacManImpl(this.xMapSize.get(), this.yMapSize.get(), this.startPosition.get(), 
                    this.lives.get(), this.noWalls.get(), this.currentDirection);
        }
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
            //bisogna gestirlo in qualche modo (forse con un log)
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

    @Override
    public final void kill() {
        this.lives = this.lives - 1;
    }

}
