package model;

import java.util.Optional;
import java.util.Set;

public class PacManImpl extends EntityAbstractImpl implements PacMan {

    private Directions currentDirection;
    private int lives;

    private PacManImpl(final int xMapSize, final int yMapSize, final PairImpl<Integer, Integer> startPosition,
            final int lives, final Set<PairImpl<Integer, Integer>> noWalls, final Directions currentDirection) {
        super(xMapSize, yMapSize, startPosition, noWalls);
        this.currentDirection = currentDirection;
        this.lives = lives;
    }

    public static class Builder {
        private Optional<Integer> xMapSize = Optional.empty();
        private Optional<Integer> yMapSize = Optional.empty();
        private Optional<PairImpl<Integer, Integer>> startPosition = Optional.empty();
        private Optional<Integer> lives = Optional.empty();
        private Optional<Set<PairImpl<Integer, Integer>>> noWalls = Optional.empty();
        private Optional<Directions> currentDirection = Optional.empty();

        /**
         * Used by the builder to check if the Optional fields are correctly assigned.
         * @param b 
         */
        private static void check(final boolean b) {
            if (!b) {
                throw new IllegalStateException();
            }
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
        public Builder startPosition(final PairImpl<Integer, Integer> startPosition) {
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
        public Builder noWalls(final Set<PairImpl<Integer, Integer>> noWalls) {
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
        public PacManImpl build() {
            check(this.xMapSize.isPresent());
            check(this.yMapSize.isPresent());
            check(this.lives.isPresent());
            check(this.startPosition.isPresent());
            check(this.currentDirection.isPresent());
            check(this.noWalls.isPresent());

            return new PacManImpl(this.xMapSize.get(), this.yMapSize.get(), this.startPosition.get(), 
                    this.lives.get(), this.noWalls.get(), this.currentDirection.get());
        }
    }

    /**
     * Move the PacMan to the next position.
     */
    @Override
    public void nextPosition() {
        PairImpl<Integer, Integer> next = this.convertToToroidal(this.calculateNextPosition());
        if (this.getNoWalls().contains(next)) {
            this.setPosition(next);
            }
    }

    private Pair<Integer, Integer> calculateNextPosition() {
        int x = 0;
        int y = 0;
        switch (this.currentDirection) {
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
        return new PairImpl<Integer, Integer>(this.getPosition().getX() + x, this.getPosition().getY() + y);
    }

    @Override
    public void setDirection(final Directions direction) {
        this.currentDirection = direction;
    }

    @Override
    public Directions getDirection() {
        return this.currentDirection;
    }

    @Override
    public int getLives() {
        return this.lives;
    }

    @Override
    public void kill() {
        this.lives = this.lives - 1;
    }

}
