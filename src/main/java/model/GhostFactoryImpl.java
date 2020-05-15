package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import utils.Pair;
import utils.PairImpl;

/**
 * this class implements  a factory of Ghost objects.
 *
 */
public final class GhostFactoryImpl implements GhostFactory {

    private static final int BLINKY_X_START_POSITION = 12;

    private static final int PINKY_X_START_POSITION = 13;

    private static final int INKY_X_START_POSITION = 14;

    private static final int CLYDE_X_START_POSITION = 15;

    private static final int GHOST_Y_START_POSITION = 14;

    private final Set<Pair<Integer, Integer>> walls;
    private final List<Pair<Integer, Integer>> ghostHouse;
    private final PacMan pacMan;
    private final int xMapSize;
    private final int yMapSize;
    private int id;

    private GhostFactoryImpl(final Set<Pair<Integer, Integer>> walls, final Set<Pair<Integer,
            Integer>> ghostHouse, final PacMan pacMan, final int xMapSize, final int yMapSize) {
        this.walls = walls;
        this.ghostHouse = new ArrayList<>(ghostHouse);
        this.pacMan = pacMan;
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.id = 0;
    }

    public static class Builder {
        private Optional<Set<Pair<Integer, Integer>>> walls = Optional.empty();
        private Optional<Set<Pair<Integer, Integer>>> ghostHouse = Optional.empty();
        private Optional<PacMan> pacMan = Optional.empty();
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
         * @param pacMan the PacMan
         * @return this
         */
        public Builder pacMan(final PacMan pacMan) {
            this.pacMan = Optional.of(pacMan);
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
            check(this.pacMan.isPresent());
            check(this.xMapSize.isPresent());
            check(this.yMapSize.isPresent());

            return new GhostFactoryImpl(this.walls.get(), this.ghostHouse.get(), this.pacMan.get(), this.xMapSize.get(), 
                    this.yMapSize.get());
        }
    }

    @Override
    public Ghost blinky() { 
        return new GhostAbstractImpl(this.xMapSize, this.yMapSize, ++this.id) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.BLINKY);
                this.setStartPosition(new PairImpl<>(BLINKY_X_START_POSITION, GHOST_Y_START_POSITION));
                this.setMyBehaviour(new GhostBlinkyBehaviourImpl(walls, pacMan, ghostHouse,
                        xMapSize, yMapSize, new PairImpl<>(xMapSize - 2, 1), this.getStartPosition()));
                this.setMyUtils();
            }
        };
    }

    @Override
    public Ghost pinky() {
        return new GhostAbstractImpl(this.xMapSize, this.yMapSize, ++this.id) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.PINKY);
                this.setStartPosition(new PairImpl<>(PINKY_X_START_POSITION, GHOST_Y_START_POSITION));
                this.setMyBehaviour(new GhostPinkyBehaviourImpl(walls, pacMan, ghostHouse,
                        xMapSize, yMapSize, new PairImpl<>(1, 1), this.getStartPosition()));
                this.setMyUtils();
            }
        };
    }

    @Override
    public Ghost inky(final Ghost blinky) {
        if (!blinky.getName().equals(Ghosts.BLINKY)) {
            throw new IllegalArgumentException("Insert Blinky");
        }
        return new GhostAbstractImpl(this.xMapSize, this.yMapSize, ++this.id) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.INKY);
                this.setStartPosition(new PairImpl<>(INKY_X_START_POSITION, GHOST_Y_START_POSITION));
                this.setMyBehaviour(new GhostInkyBehaviourImpl(blinky, walls, pacMan, ghostHouse,
                        xMapSize, yMapSize, new PairImpl<>(xMapSize - 2, yMapSize - 2), this.getStartPosition()));
                this.setMyUtils();
            }
        };
    }

    @Override
    public Ghost clyde() {
        return new GhostAbstractImpl(this.xMapSize, this.yMapSize, ++this.id) {
            @Override
            public void create() {
                this.setCreated();
                this.setName(Ghosts.CLYDE);
                this.setStartPosition(new PairImpl<>(CLYDE_X_START_POSITION, GHOST_Y_START_POSITION));
                this.setMyBehaviour(new GhostClydeBehaviourImpl(walls, pacMan, ghostHouse,
                        xMapSize, yMapSize, new PairImpl<>(1, yMapSize - 2), this.getStartPosition()));
                this.setMyUtils();
            }
        };
    }
}
