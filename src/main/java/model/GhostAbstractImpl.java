package model;

import java.util.Optional;

import javax.naming.OperationNotSupportedException;

public abstract class GhostAbstractImpl extends EntityAbstractImpl implements Ghost {

    private boolean eatable;
    private boolean isRelaxed;
    private boolean isBlinkyDead;
    private Ghosts name;
    private GhostBehaviour myBehaviour;
    private PairImpl<Integer, Integer> initialPosition;
    private PairImpl<Integer, Integer> relaxTarget;
    private Optional<Ghost> blinky;

    public GhostAbstractImpl(final int xMapSize, final int yMapSize) {
        super(yMapSize, yMapSize);
        this.eatable = false;
        this.isRelaxed = true;
        this.blinky = Optional.empty();
        this.isBlinkyDead = false;
    }

    public final void nextPosition(final PacMan pacMan) {
        if (this.eatable) {
            this.myBehaviour.runAway();
        } else {
            if (this.isRelaxed) {
                this.myBehaviour.relax();
                if (this.myBehaviour.getCurrentPosition().equals(this.relaxTarget)) {
                    this.isRelaxed = false;
                }
            } else {
                if (this.blinky.isEmpty() || this.isBlinkyDead) {
                    this.myBehaviour.chase(pacMan, Optional.empty());
                } else {
                    this.myBehaviour.chase(pacMan, Optional.of(this.blinky.get().getPosition()));
                }
            }
        }
    }

    public final boolean isEatable() {
        return this.eatable;
    }

    public final void returnHome() {
        this.myBehaviour.setCurrentPosition(this.initialPosition);
    }

    public final void blinkyIsDead() throws OperationNotSupportedException {
        if (this.getName().equals(Ghosts.INKY)) {
            this.isBlinkyDead = true;
        } else {
            throw new OperationNotSupportedException("This method is designed only for Inky");
        }
    }

    public final PairImpl<Integer, Integer> getPosition() {
        return this.convertToToroidal(this.myBehaviour.getCurrentPosition());
    }

    public final void setEatable(final boolean eatable) {
        this.eatable = eatable;
        if (this.eatable) {
            this.myBehaviour.turnAround(this.myBehaviour.getCurrentDirection());
        }
    }

    public final Ghosts getName() {
        return this.name;
    }

    protected final void setName(final Ghosts name) {
        this.name = name;
    }

    protected final GhostBehaviour getMyBehaviour() {
        return this.myBehaviour;
    }

    protected final void setMyBehaviour(final GhostBehaviour myBehaviour) {
        this.myBehaviour = myBehaviour;
    }

    protected final void setInitialPosition(final PairImpl<Integer, Integer> initialPosition) {
        this.initialPosition = initialPosition;
    }

    protected final PairImpl<Integer, Integer> getRelaxTarget() {
        return this.relaxTarget;
    }

    protected final void setRelaxTarget(final PairImpl<Integer, Integer> relaxTarget) {
        this.relaxTarget = relaxTarget;
    }

    protected final void setBlinky(final Optional<Ghost> blinky) {
        this.blinky = blinky;
    }
}

