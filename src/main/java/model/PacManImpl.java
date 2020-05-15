package model;

import java.util.Optional;
import java.util.Set;

import utils.Directions;
import utils.Pair;
import utils.PairImpl;
/**
 * Class that represent the pacman entity.
 *
 */
public final class PacManImpl extends EntityAbstractImpl implements PacMan {

    private Directions currentDirection;
    private int lives;
    private Pair<Integer, Integer> position;
    private final Pair<Integer, Integer> startPosition;
    private final Set<Pair<Integer, Integer>> noWalls;

    /**
     * Private constructor. is called by the builder.
     */
    private PacManImpl(final int xMapSize, final int yMapSize, final Pair<Integer, Integer> startPosition,
            final int lives, final Set<Pair<Integer, Integer>> noWalls, final Directions currentDirection) {
        super(xMapSize, yMapSize);
        this.noWalls = noWalls;
        this.startPosition = startPosition;
        this.setPosition(startPosition);
        this.currentDirection = currentDirection;
        this.lives = lives;
    }

    /**
     * inner builder class to build the PacManImpl object.
     */
    public static class Builder {
        private Optional<Integer> xMapSize = Optional.empty();
        private Optional<Integer> yMapSize = Optional.empty();
        private Optional<Pair<Integer, Integer>> startPosition = Optional.empty();
        private Optional<Integer> lives = Optional.empty();
        private Optional<Set<Pair<Integer, Integer>>> noWalls = Optional.empty();
        private Optional<Directions> currentDirection = Optional.empty();
        private boolean built;

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
        public PacManImpl build() {
            check(!this.built);
            check(this.xMapSize.isPresent());
            check(this.yMapSize.isPresent());
            check(this.lives.isPresent());
            check(this.startPosition.isPresent());
            check(this.currentDirection.isPresent());
            check(this.noWalls.isPresent());
            this.built = true;

            return new PacManImpl(this.xMapSize.get(), this.yMapSize.get(), this.startPosition.get(), 
                    this.lives.get(), this.noWalls.get(), this.currentDirection.get());
        }
    }

    /**
     * Move the PacMan to the next position.
     */
    @Override
    public void nextPosition() {
        final Pair<Integer, Integer> next = this.convertToToroidal(this.calculateNextPosition());
        if (this.noWalls.contains(next)) {
            this.setPosition(next);
            }
    }

    /**
     * calculate the next position of pacman basing on actual position and actual direction.
     */
    private Pair<Integer, Integer> calculateNextPosition() {
        int x = 0;
        int y = 0;
        switch (this.currentDirection) {
        case UP:
            y = -1;
            break;
        case DOWN:
            y = 1;
            break;
        case LEFT:
            x = -1;
            break;
        case RIGHT:
            x = 1;
            break;
        default:
            break;
        }
        return new PairImpl<Integer, Integer>(this.getPosition().getX() + x, this.getPosition().getY() + y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Directions direction) {
        this.currentDirection = direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Directions getDirection() {
        return this.currentDirection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return this.lives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void kill() {
        this.lives = this.lives - 1;
        this.returnToStartPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void returnToStartPosition() {
        this.setPosition(startPosition);
    }

    /**
     * Set the position of PacMan.
     * 
     * @param position the position of PacMan
     */
    private void setPosition(final Pair<Integer, Integer> position) {
        this.position = position;
    }


}
