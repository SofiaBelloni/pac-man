package model;

import java.util.Optional;
import java.util.Set;


public class GhostFactoryImpl implements GhostFactory {
    private final Set<PairImpl<Integer, Integer>> setWall;
    private int xMap;
    private int yMap;

    public GhostFactoryImpl(final Set<PairImpl<Integer, Integer>> setWall) {
        this.setWall = setWall;
    }

    @Override
    public Ghost blinky() { 
        return new GhostAbstractImpl(this.xMap, this.yMap) {        
            public void create() {
                this.name = Ghosts.BLINKY;
                this.relaxTarget = new PairImpl<>(xMap - 1, yMap - 1);
                this.myBehaviour = new BlinkyBehaviour(setWall, xMap, yMap, this.relaxTarget);
                this.initialPosition = new PairImpl<>(7, 6);
                this.myBehaviour.setPosition(this.initialPosition);
            }
        };
    }

    @Override
    public Ghost pinky() {
        return new GhostAbstractImpl(this.xMap, this.yMap) {        
            public void create() {
                this.name = Ghosts.PINKY;
                this.relaxTarget = new PairImpl<>(0, yMap - 1);
                this.myBehaviour = new PinkyBehaviour(setWall, xMap, yMap, this.relaxTarget);
                this.initialPosition = new PairImpl<>(7, 6);
                this.myBehaviour.setPosition(this.initialPosition);
            }
        };
    }

    @Override
    public Ghost inky(Ghost blink) {
        if (!blink.getName().equals(Ghosts.BLINKY)) {
            throw new IllegalStateException("Insert Blinky");
        }
        return new GhostAbstractImpl(this.xMap, this.yMap) {        
            public void create() {
                this.name = Ghosts.INKY;
                this.blinky = Optional.of(blink);
                this.relaxTarget = new PairImpl<>(xMap - 1, 0);
                this.myBehaviour = new InkyBehaviour(setWall, xMap, yMap, this.relaxTarget);
                this.initialPosition = new PairImpl<>(7,6);
                this.myBehaviour.setPosition(this.initialPosition);
            }
        };
    }

    @Override
    public Ghost clyde() {
        return new GhostAbstractImpl(this.xMap, this.yMap) {        
            public void create() {
                this.name = Ghosts.CLYDE;
                this.relaxTarget = new PairImpl<>(0, 0);
                this.myBehaviour = new ClydeBehaviour(setWall, xMap, yMap, this.relaxTarget);
                this.initialPosition = new PairImpl<>(7, 6);
                this.myBehaviour.setPosition(this.initialPosition);
            }
        };
    }
}
