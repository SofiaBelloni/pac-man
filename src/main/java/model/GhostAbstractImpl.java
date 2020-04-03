package model;

import java.util.Optional;

import javax.naming.OperationNotSupportedException;

public abstract class GhostAbstractImpl extends EntityAbstractImpl implements Ghost {

    private boolean eatable;
    private boolean isRelaxed;
    private boolean isBlinkyDead;
    protected Ghosts name;
    protected GhostBehaviour myBehaviour;
    protected PairImpl<Integer, Integer> initialPosition;
    protected PairImpl<Integer, Integer> relaxTarget;
    protected Optional<Ghost> blinky;

    public GhostAbstractImpl(int xMapSize, int yMapSize) {
        super(yMapSize, yMapSize);
        this.eatable = false;
        this.isRelaxed = true;
        this.blinky = Optional.empty();
        this.isBlinkyDead = false;
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
                if (blinky.isEmpty()) {
                    this.myBehaviour.chase(pacMan, Optional.empty());
                } else {
                    this.myBehaviour.chase(pacMan, Optional.of(blinky.get().getPosition()));
                }
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
    
    public Ghosts getName() {
        return this.name;
    }
    
    public void blinkyIsDead() throws OperationNotSupportedException {
        if (this.getName().equals(Ghosts.INKY)) {
            this.isBlinkyDead = true;
        } else {
            throw new OperationNotSupportedException("This method is designed only for Inky");
        }
    }
}

