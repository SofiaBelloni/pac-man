package model;

import java.util.Set;


public class GhostFactoryImpl implements GhostFactory {
    private final Set<Pair<Integer,Integer>> setWall;
    private final int xMap;
    private final int yMap;

    public GhostFactoryImpl(Set<Pair<Integer,Integer>> setWall, Integer xMap, Integer yMap) {
        this.setWall = setWall;
        this.xMap = xMap;
        this.yMap = yMap;
    }
    
    @Override
    public Ghost blinky() { 
        return new GhostAbstractImpl() {        
            public void create() {
                this.relaxTarget = new Pair<>(xMap,yMap);
                this.myBehaviour = new BlinkyBehaviour(setWall, xMap, yMap, this.relaxTarget);
                this.initialPosition = new Pair<>(7,6);
                this.myBehaviour.setPosition(this.initialPosition);
            }
        };
    }

    @Override
    public Ghost pinky() {
        return new GhostAbstractImpl() {        
            public void create() {
                this.relaxTarget = new Pair<>(0,yMap);
                this.myBehaviour = new PinkyBehaviour(setWall, xMap, yMap, this.relaxTarget);
                this.initialPosition = new Pair<>(7,6);
                this.myBehaviour.setPosition(this.initialPosition);
            }
        };
    }

    @Override
    public Ghost inky(Ghost blinky) {
        return new GhostAbstractImpl() {
            public void create() {
                this.relaxTarget = new Pair<>(xMap,0);
                this.myBehaviour = new InkyBehaviour(setWall, xMap, yMap, this.relaxTarget);
                this.initialPosition = new Pair<>(7,6);
                this.myBehaviour.setPosition(this.initialPosition);
            }
        };
    }

    @Override
    public Ghost clyde() {
        return new GhostAbstractImpl() {
            public void create() {
                this.relaxTarget = new Pair<>(0,0);
                this.myBehaviour = new ClydeBehaviour(setWall, xMap, yMap, this.relaxTarget);
                this.initialPosition = new Pair<>(7,6);
                this.myBehaviour.setPosition(this.initialPosition);
            }
        };
    }
}
