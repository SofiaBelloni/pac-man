package model;

import java.util.Optional;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

public abstract class GhostAbstractImpl extends EntityAbstractImpl implements Ghost {

    private final Set<PairImpl<Integer, Integer>> setWall;
    private final Set<PairImpl<Integer, Integer>> setHome;
    private boolean eatable;
    private boolean isRelaxed;
    private boolean isBlinkyDead;
    private boolean closedDoor;
    private Ghosts name;
    private GhostBehaviour myBehaviour;
    private PairImpl<Integer, Integer> startPosition;
    private PairImpl<Integer, Integer> relaxTarget;
    private Optional<Ghost> blinky;
    private PairImpl<Integer, Integer> door;

    public GhostAbstractImpl(final Set<PairImpl<Integer, Integer>> setWall, final Set<PairImpl<Integer, Integer>> setHome, final int xMapSize, final int yMapSize, final PairImpl<Integer, Integer> door) {
        super(yMapSize, yMapSize);
        this.eatable = false;
        this.isRelaxed = true;
        this.blinky = Optional.empty();
        this.isBlinkyDead = false;
        this.setWall = setWall;
        this.setHome = setHome;
        this.door = door;
        this.closedDoor = false;
    }

    public final void nextPosition(final PacMan pacMan) {
        if (!closedDoor) {
            this.closeTheDoor();
        }
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
        this.myBehaviour.setCurrentPosition(this.convertToToroidal(this.myBehaviour.getCurrentPosition()));
    }

    private void closeTheDoor() {
        int i = 0;
        for (Pair<Integer, Integer> p : setHome) {
            if (this.myBehaviour.getCurrentPosition().equals(p)) {
                i++;
            }
        }
            if (i == 0) {
                this.setWall.add(door);
                this.closedDoor = true;
            }
    }

    public final boolean isEatable() {
        return this.eatable;
    }

    public final void returnHome() {
        this.myBehaviour.setCurrentPosition(this.startPosition);
    }

    public final void blinkyIsDead() throws OperationNotSupportedException {
        if (this.getName().equals(Ghosts.INKY)) {
            this.isBlinkyDead = true;
        } else {
            throw new OperationNotSupportedException("This method is designed only for Inky");
        }
    }

    public final PairImpl<Integer, Integer> getPosition() {
        return this.myBehaviour.getCurrentPosition();
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

    protected final PairImpl<Integer, Integer> getStartPosition() {
        return this.startPosition;
    }

    protected final void setStartPosition(final PairImpl<Integer, Integer> startPosition) {
        this.startPosition = startPosition;
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

