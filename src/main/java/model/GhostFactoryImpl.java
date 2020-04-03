package model;

import java.util.Optional;
import java.util.Set;


public class GhostFactoryImpl implements GhostFactory {
    private final Set<PairImpl<Integer, Integer>> setWall;
    private int xMapSize;
    private int yMapSize;

    public GhostFactoryImpl(final Set<PairImpl<Integer, Integer>> setWall) {
        this.setWall = setWall;
    }

    @Override
    public final Ghost blinky() { 
        return new GhostAbstractImpl(this.xMapSize, this.yMapSize) {
            public void create() {
                this.setName(Ghosts.BLINKY);
                this.setRelaxTarget(new PairImpl<>(xMapSize - 1, yMapSize - 1));
                this.setMyBehaviour(new BlinkyBehaviour(setWall, xMapSize, yMapSize, this.getRelaxTarget()));
                this.setInitialPosition(new PairImpl<>(7, 9));
                this.getMyBehaviour().setCurrentPosition(this.getPosition());
            }
        };
    }

    @Override
    public final Ghost pinky() {
        return new GhostAbstractImpl(this.xMapSize, this.yMapSize) {
            public void create() {
                this.setName(Ghosts.PINKY);
                this.setRelaxTarget(new PairImpl<>(0, yMapSize - 1));
                this.setMyBehaviour(new PinkyBehaviour(setWall, xMapSize, yMapSize, this.getRelaxTarget()));
                this.setInitialPosition(new PairImpl<>(7, 6));
                this.getMyBehaviour().setCurrentPosition(this.getPosition());
            }
        };
    }

    @Override
    public final Ghost inky(final Ghost blink) {
        if (!blink.getName().equals(Ghosts.BLINKY)) {
            throw new IllegalStateException("Insert Blinky");
        }
        return new GhostAbstractImpl(this.xMapSize, this.yMapSize) {
            public void create() {
                this.setName(Ghosts.INKY);
                this.setBlinky(Optional.of(blink));
                this.setRelaxTarget(new PairImpl<>(xMapSize - 1, 0));
                this.setMyBehaviour(new InkyBehaviour(setWall, xMapSize, yMapSize, this.getRelaxTarget()));
                this.setInitialPosition(new PairImpl<>(7, 7));
                this.getMyBehaviour().setCurrentPosition(this.getPosition());
            }
        };
    }

    @Override
    public final Ghost clyde() {
        return new GhostAbstractImpl(this.xMapSize, this.yMapSize) {
            public void create() {
                this.setName(Ghosts.CLYDE);
                this.setRelaxTarget(new PairImpl<>(0, 0));
                this.setMyBehaviour(new ClydeBehaviour(setWall, xMapSize, yMapSize, this.getRelaxTarget()));
                this.setInitialPosition(new PairImpl<>(7, 8));
                this.getMyBehaviour().setCurrentPosition(this.getPosition());
            }
        };
    }
}
