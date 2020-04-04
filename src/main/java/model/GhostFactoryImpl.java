package model;

import java.util.Optional;
import java.util.Set;


public final class GhostFactoryImpl implements GhostFactory {
    private final Set<PairImpl<Integer, Integer>> setWalls;
    private final Set<PairImpl<Integer, Integer>> setHome;
    private int xMapSize;
    private int yMapSize;
    private PairImpl<Integer, Integer> door;

    private GhostFactoryImpl(final Set<PairImpl<Integer, Integer>> setWalls, final Set<PairImpl<Integer,
            Integer>> setHome, final int xMapSize, final int yMapSize,
            final PairImpl<Integer, Integer> door) {
        this.setWalls = setWalls;
        this.setHome = setHome;
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.door = door;
    }

    public static class Builder {
        private Optional<Set<PairImpl<Integer, Integer>>> setWalls = Optional.empty();
        private Optional<Set<PairImpl<Integer, Integer>>> setHome = Optional.empty();
        private Optional<Integer> xMapSize = Optional.empty();
        private Optional<Integer> yMapSize = Optional.empty();
        private Optional<PairImpl<Integer, Integer>> door = Optional.empty();

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
        public Builder door(final PairImpl<Integer, Integer> door) {
            this.door = Optional.of(door);
            return this;
        }

        /**
         * 
         * @param setWalls a set containing the coordinates of the walls
         * @return this
         */
        public Builder setWalls(final Set<PairImpl<Integer, Integer>> setWalls) {
            this.setWalls = Optional.of(setWalls);
            return this;
        }

        /**
         * 
         * @param setHome a set containing the coordinates where you can go
         * @return this
         */
        public Builder setHome(final Set<PairImpl<Integer, Integer>> setHome) {
            this.setHome = Optional.of(setHome);
            return this;
        }


        /**
         * 
         * @return a new instance of PacManImpl
         * @throws IllegalStateException if some parameter is missed
         */
        public GhostFactoryImpl build() {
            check(this.setWalls.isPresent());
            check(this.setHome.isPresent());
            check(this.xMapSize.isPresent());
            check(this.yMapSize.isPresent());
            check(this.door.isPresent());

            return new GhostFactoryImpl(this.setWalls.get(), this.setHome.get(), this.xMapSize.get(),
                    this.yMapSize.get(), this.door.get());
        }
    }

    @Override
    public Ghost blinky() { 
        return new GhostAbstractImpl(this.setWalls, this.setHome, this.xMapSize, this.yMapSize, this.door) {
            public void create() {
                this.setStartPosition(new PairImpl<>(7,6));
                this.setName(Ghosts.BLINKY);
                this.setRelaxTarget(new PairImpl<>(xMapSize - 1, yMapSize - 1));
                this.setMyBehaviour(new BlinkyBehaviour(setWalls, xMapSize, yMapSize, this.getRelaxTarget()));
                this.getMyBehaviour().setCurrentPosition(this.getStartPosition());
                this.getMyBehaviour().setCurrentPosition(this.getPosition());
            }
        };
    }

    @Override
    public Ghost pinky() {
        return new GhostAbstractImpl(this.setWalls, this.setHome, this.xMapSize, this.yMapSize, this.door) {
            public void create() {
                this.setStartPosition(new PairImpl<>(7,6));
                this.setName(Ghosts.PINKY);
                this.setRelaxTarget(new PairImpl<>(0, yMapSize - 1));
                this.setMyBehaviour(new PinkyBehaviour(setWalls, xMapSize, yMapSize, this.getRelaxTarget()));
                this.getMyBehaviour().setCurrentPosition(this.getStartPosition());
                this.getMyBehaviour().setCurrentPosition(this.getPosition());
            }
        };
    }

    @Override
    public Ghost inky(final Ghost blink) {
        if (!blink.getName().equals(Ghosts.BLINKY)) {
            throw new IllegalStateException("Insert Blinky");
        }
        return new GhostAbstractImpl(this.setWalls, this.setHome, this.xMapSize, this.yMapSize, this.door) {
            public void create() {
                this.setStartPosition(new PairImpl<>(7,6));
                this.setName(Ghosts.INKY);
                this.setBlinky(Optional.of(blink));
                this.setRelaxTarget(new PairImpl<>(xMapSize - 1, 0));
                this.setMyBehaviour(new InkyBehaviour(setWalls, xMapSize, yMapSize, this.getRelaxTarget()));
                this.getMyBehaviour().setCurrentPosition(this.getStartPosition());
                this.getMyBehaviour().setCurrentPosition(this.getPosition());
            }
        };
    }

    @Override
    public Ghost clyde() {
        return new GhostAbstractImpl(this.setWalls, this.setHome, this.xMapSize, this.yMapSize, this.door) {
            public void create() {
                this.setStartPosition(new PairImpl<>(7,6));
                this.setName(Ghosts.CLYDE);
                this.setRelaxTarget(new PairImpl<>(0, 0));
                this.setMyBehaviour(new ClydeBehaviour(setWalls, xMapSize, yMapSize, this.getRelaxTarget()));
                this.getMyBehaviour().setCurrentPosition(this.getStartPosition());
                this.getMyBehaviour().setCurrentPosition(this.getPosition());
            }
        };
    }
}
