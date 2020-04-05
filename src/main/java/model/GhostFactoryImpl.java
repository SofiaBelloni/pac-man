package model;

import java.util.Optional;
import java.util.Set;
import java.util.List;

/**
 * this class implements factory for creating Ghost objects.
 *
 */
public final class GhostFactoryImpl implements GhostFactory {
    private final Set<Pair<Integer, Integer>> walls;
    private final List<Pair<Integer, Integer>> ghostHouse;
    private final int xMapSize;
    private final int yMapSize;
    private final Pair<Integer, Integer> door;

    private GhostFactoryImpl(final Set<Pair<Integer, Integer>> walls, final List<Pair<Integer,
            Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> door) {
        this.walls = walls;
        this.ghostHouse = ghostHouse;
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.door = door;
    }

    public static class Builder {
        private Optional<Set<Pair<Integer, Integer>>> walls = Optional.empty();
        private Optional<List<Pair<Integer, Integer>>> ghostHouse = Optional.empty();
        private Optional<Integer> xMapSize = Optional.empty();
        private Optional<Integer> yMapSize = Optional.empty();
        private Optional<Pair<Integer, Integer>> door = Optional.empty();

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
         * @param door a pair containing the x,y position 
         * @return this
         */
        public Builder door(final Pair<Integer, Integer> door) {
            this.door = Optional.of(door);
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
         * @param ghostHouse a set containing the coordinates where you can go
         * @return this
         */
        public Builder ghostHouse(final List<Pair<Integer, Integer>> ghostHouse) {
            this.ghostHouse = Optional.of(ghostHouse);
            return this;
        }

        /**
         * 
         * @return a new instance of PacManImpl
         * @throws IllegalStateException if some parameter is missed
         */
        public GhostFactoryImpl build() {
            check(this.walls.isPresent());
            check(this.ghostHouse.isPresent());
            check(this.xMapSize.isPresent());
            check(this.yMapSize.isPresent());
            check(this.door.isPresent());

            return new GhostFactoryImpl(this.walls.get(), this.ghostHouse.get(), this.xMapSize.get(),
                    this.yMapSize.get(), this.door.get());
        }
    }

    @Override
    public Ghost blinky() { 
        return new GhostAbstractImpl(this.walls, this.ghostHouse, this.xMapSize, this.yMapSize, this.door) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.BLINKY);
                this.setRelaxTarget(new PairImpl<>(xMapSize - 1, yMapSize - 1));
                this.setMyBehaviour(new BlinkyBehaviour(walls, ghostHouse, xMapSize, yMapSize, this.getRelaxTarget()));
            }
        };
    }

    @Override
    public Ghost pinky() {
        return new GhostAbstractImpl(this.walls, this.ghostHouse, this.xMapSize, this.yMapSize, this.door) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.PINKY);
                this.setRelaxTarget(new PairImpl<>(0, yMapSize - 1));
                this.setMyBehaviour(new PinkyBehaviour(walls, ghostHouse, xMapSize, yMapSize, this.getRelaxTarget()));
            }
        };
    }

    @Override
    public Ghost inky(final Ghost blink) {
        if (!blink.getName().equals(Ghosts.BLINKY)) {
            throw new IllegalStateException("Insert Blinky");
        }
        return new GhostAbstractImpl(this.walls, this.ghostHouse, this.xMapSize, this.yMapSize, this.door) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.INKY);
                this.setRelaxTarget(new PairImpl<>(xMapSize - 1, 0));
                this.setMyBehaviour(new InkyBehaviour(walls, ghostHouse, xMapSize, yMapSize, this.getRelaxTarget()));
            }
        };
    }

    @Override
    public Ghost clyde() {
        return new GhostAbstractImpl(this.walls, this.ghostHouse, this.xMapSize, this.yMapSize, this.door) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.CLYDE);
                this.setRelaxTarget(new PairImpl<>(0, 0));
                this.setMyBehaviour(new ClydeBehaviour(walls, ghostHouse, xMapSize, yMapSize, this.getRelaxTarget()));
            }
        };
    }
}
