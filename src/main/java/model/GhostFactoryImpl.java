package model;

import java.util.Optional;
import java.util.Set;

/**
 * this class implements factory for creating Ghost objects.
 *
 */
public final class GhostFactoryImpl implements GhostFactory {
    private final Set<Pair<Integer, Integer>> walls;
    private final Set<Pair<Integer, Integer>> ghostHouse;
    private final int xMapSize;
    private final int yMapSize;

    private GhostFactoryImpl(final Set<Pair<Integer, Integer>> walls, final Set<Pair<Integer,
            Integer>> ghostHouse, final int xMapSize, final int yMapSize) {
        this.walls = walls;
        this.ghostHouse = ghostHouse;
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
    }

    public static class Builder {
        private Optional<Set<Pair<Integer, Integer>>> walls = Optional.empty();
        private Optional<Set<Pair<Integer, Integer>>> ghostHouse = Optional.empty();
        private Optional<Integer> xMapSize = Optional.empty();
        private Optional<Integer> yMapSize = Optional.empty();

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
         * @param walls a set containing the coordinates of the walls
         * @return this
         */
        public Builder walls(final Set<Pair<Integer, Integer>> walls) {
            this.walls = Optional.of(walls);
            return this;
        }

        /**
         * 
         * @param ghostHouse a Set containing the coordinates of the ghost house
         * @return this
         */
        public Builder ghostHouse(final Set<Pair<Integer, Integer>> ghostHouse) {
            this.ghostHouse = Optional.of(ghostHouse);
            return this;
        }

        /**
         * 
         * @return a new instance of GhostFactory
         * @throws IllegalStateException if some parameter is missed
         */
        public GhostFactoryImpl build() {
            check(this.walls.isPresent());
            check(this.ghostHouse.isPresent());
            check(this.xMapSize.isPresent());
            check(this.yMapSize.isPresent());

            return new GhostFactoryImpl(this.walls.get(), this.ghostHouse.get(), this.xMapSize.get(),
                    this.yMapSize.get());
        }
    }

    @Override
    public Ghost blinky() { 
        return new GhostAbstractImpl(this.walls, this.ghostHouse, this.xMapSize, this.yMapSize) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.BLINKY);
                this.setRelaxTarget(new PairImpl<>(xMapSize - 1, yMapSize - 1));
                this.setMyBehaviour(new GhostBlinkyBehaviour(walls, ghostHouse, xMapSize, yMapSize, this.getRelaxTarget()));
            }
        };
    }

    @Override
    public Ghost pinky() {
        return new GhostAbstractImpl(this.walls, this.ghostHouse, this.xMapSize, this.yMapSize) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.PINKY);
                this.setRelaxTarget(new PairImpl<>(0, yMapSize - 1));
                this.setMyBehaviour(new GhostPinkyBehaviour(walls, ghostHouse, xMapSize, yMapSize, this.getRelaxTarget()));
            }
        };
    }

    @Override
    public Ghost inky(final Ghost blinky) {
        if (!blinky.getName().equals(Ghosts.BLINKY)) {
            throw new IllegalArgumentException("Insert Blinky");
        }
        return new GhostAbstractImpl(this.walls, this.ghostHouse, this.xMapSize, this.yMapSize) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.INKY);
                this.setRelaxTarget(new PairImpl<>(xMapSize - 1, 0));
                this.setMyBehaviour(new GhostInkyBehaviour(blinky, walls, ghostHouse, xMapSize, yMapSize, this.getRelaxTarget()));
            }
        };
    }

    @Override
    public Ghost clyde() {
        return new GhostAbstractImpl(this.walls, this.ghostHouse, this.xMapSize, this.yMapSize) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.CLYDE);
                this.setRelaxTarget(new PairImpl<>(0, 0));
                this.setMyBehaviour(new GhostClydeBehaviour(walls, ghostHouse, xMapSize, yMapSize, this.getRelaxTarget()));
            }
        };
    }
}
