package model;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public abstract class GhostAbstractImpl implements Ghost {
   
    private boolean eatable;
    private boolean isRelaxed;
    protected BraveBehaviour myBehaviour;
    protected Pair<Integer, Integer> initialPosition;
    protected Pair<Integer, Integer> relaxTarget;

    public GhostAbstractImpl() {
        this.eatable = false;
        this.isRelaxed = true;
    }
    
    public void nextPosition(PacMan pacMan) {
        if (this.eatable) {
            this.myBehaviour.runAway();
        } else {
            if (this.isRelaxed) {
                this.myBehaviour.relax();
                if (this.myBehaviour.getPosition().equals(this.relaxTarget)) {
                    this.isRelaxed = false;
                }
            } else {
                this.myBehaviour.chase(pacMan, Optional.empty());
            }
        }
    }
    
    public boolean isEatable() {
        return this.eatable;
    }  
    
    public void returnHome() {
        this.myBehaviour.setPosition(this.initialPosition);
    }

    public Pair<Integer, Integer> getPosition() {
        return this.myBehaviour.getPosition();
    }
    
    public void setEatable(boolean eatable) {
        this.eatable = eatable;
        if (this.eatable) {
            this.myBehaviour.turnAround(this.myBehaviour.getDirection());
        }
    }
}

