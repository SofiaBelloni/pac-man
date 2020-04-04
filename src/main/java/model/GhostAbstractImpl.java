package model;

import java.util.Optional;
import java.util.Set;

/**
 * this class implements a generic Ghost Entity.
 *
 */
public abstract class GhostAbstractImpl extends EntityAbstractImpl implements Ghost {

    private final Set<PairImpl<Integer, Integer>> setWall;
    private final Set<PairImpl<Integer, Integer>> setHome;
    private boolean eatable;
    private boolean isRelaxed;
    private boolean isBlinkyDead;
    private boolean closedDoor;
    private boolean created;
    private Ghosts name;
    private GhostBehaviour myBehaviour;
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
        this.created = false;
    }

    public final void nextPosition(final PacMan pacMan) {
        this.checkCreation();
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


    public final PairImpl<Integer, Integer> getPosition() {
        this.checkCreation();
        return this.myBehaviour.getCurrentPosition();
    }

    public final void closeTheDoor() {
        this.checkCreation();
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
        this.checkCreation();
        return this.eatable;
    }

    public final void returnHome() {
        this.checkCreation();
        this.myBehaviour.setCurrentPosition(this.myBehaviour.getStartPosition());
    }

    public final void setEatable(final boolean eatable) {
        this.checkCreation();
        this.eatable = eatable;
        if (this.eatable) {
            this.myBehaviour.turnAround(this.myBehaviour.getCurrentDirection());
        }
    }

    public final void blinkyIsDead() {
        this.checkCreation();
        if (this.getName().equals(Ghosts.INKY)) {
            this.isBlinkyDead = true;
        } else {
            throw new IllegalStateException("This method is designed only for Inky");
        }
    }

    public final Ghosts getName() {
        this.checkCreation();
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

    protected final PairImpl<Integer, Integer> getRelaxTarget() {
        return this.relaxTarget;
    }

    protected final void setRelaxTarget(final PairImpl<Integer, Integer> relaxTarget) {
        this.relaxTarget = relaxTarget;
    }

    protected final void setBlinky(final Optional<Ghost> blinky) {
        this.blinky = blinky;
    }

    protected final void setCreated() {
        this.created = true;
    }

    private void checkCreation() {
        if (!this.created) {
            throw new IllegalStateException("Error, ghost not created");
        }
    }
}

