package model;

import java.util.Optional;

public abstract class GhostAbstractImpl extends EntityAbstractImpl implements Ghost {

    private boolean eatable;
    private boolean isRelaxed;
    protected GhostBehaviour myBehaviour;
    protected PairImpl<Integer, Integer> initialPosition;
    protected PairImpl<Integer, Integer> relaxTarget;

    public GhostAbstractImpl(int xMapSize, int yMapSize) {
        super(yMapSize, yMapSize);
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

    public PairImpl<Integer, Integer> getPosition() {
        return this.convertToToroidal(this.myBehaviour.getPosition());
    }

    public void setEatable(boolean eatable) {
        this.eatable = eatable;
        if (this.eatable) {
            this.myBehaviour.turnAround(this.myBehaviour.getDirection());
        }
    }
}

