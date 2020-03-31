package model;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public abstract class GhostAbstractImpl implements Ghost {
    protected int xMap;
    protected int yMap;
    protected final Set<Pair<Integer, Integer>> setWall;
    private boolean eatable;
    protected Pair<Integer, Integer> target;
    protected Pair<Integer, Integer> currentPosition;
    protected Pair<Integer, Integer> initialPosition;
    protected Directions dir;
    protected boolean isRelaxed;
    protected FrightenedBehaviour f; 
    protected BraveBehaviour c;

    public GhostAbstractImpl(final Set<Pair<Integer, Integer>> setWall, Integer xMap, Integer yMap) {
        this.setWall = setWall;
        this.xMap = xMap;
        this.yMap = yMap;
        eatable = false;
        currentPosition = new Pair<>(7,6);
        dir = Directions.UP;
        isRelaxed = true;
        f = new FrightenedBehaviourImpl(setWall, xMap, yMap);
    }
    

    public Pair<Integer, Integer> getPosition() {
        return currentPosition;
    }
    

    @Override
    public boolean isEatable() {
        return this.eatable;
    }  
    
    @Override
    public void returnHome() {
        this.currentPosition = this.initialPosition;
    }
    
    @Override
    public void setEatable(boolean eatable) {
        this.eatable = eatable;
        if (eatable) {
            if (dir.equals(Directions.UP)) {
                dir = Directions.DOWN;
            } else if (dir.equals(Directions.RIGHT)) {
                dir = Directions.LEFT;
            } else if (dir.equals(Directions.DOWN)) {
                dir = Directions.UP;
            } else {
                dir = Directions.RIGHT;
            }
        }
    }
}

