package model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * this class implements a generic Ghost Entity.
 *
 */
public abstract class GhostAbstractImpl extends EntityAbstractImpl implements Ghost {

    private final Set<Pair<Integer, Integer>> setWall;
    private final List<Pair<Integer, Integer>> ghostHouse;
    private boolean eatable;
    private boolean isRelaxed;
    private boolean timeToTurn;
    private boolean isBlinkyDead;
    private boolean isInside;
    private boolean created;
    private Ghosts name;
    private GhostBehaviour myBehaviour;
    private Pair<Integer, Integer> relaxTarget;
    private Optional<Ghost> blinky;
    private final Pair<Integer, Integer> door;

    public GhostAbstractImpl(final Set<Pair<Integer, Integer>> setWall,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize,
            final int yMapSize, final Pair<Integer, Integer> door) {
        super(xMapSize, yMapSize);
        this.isRelaxed = true;
        this.isInside = true;
        this.created = false;
        this.timeToTurn = false;
        this.eatable = false;
        this.blinky = Optional.empty();
        this.isBlinkyDead = false;
        this.setWall = setWall;
        this.ghostHouse = ghostHouse;
        this.door = door;
    }

    @Override
    public final void nextPosition(final PacMan pacMan) {
        this.checkCreation();
        if (isInside && !this.ghostHouse.contains(this.getPosition())) {
            this.setWall.add(door);
            this.isInside = false;
        }
        if (this.eatable) {
            if (this.timeToTurn) {
                 if (!isInside && !this.door.equals(new PairImpl<>(this.getPosition().getX(), this.getPosition().getY() - 1))) {
                     this.myBehaviour.turnAround();
                 } else {
                     this.myBehaviour.runAway();
                 }
                this.timeToTurn = false;
            } else {
                this.myBehaviour.runAway();
            }
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
        this.myBehaviour.setCurrentPosition(this.convertToToroidal(this.getPosition()));
    }

    @Override
    public final Pair<Integer, Integer> getPosition() {
        this.checkCreation();
        return this.myBehaviour.getCurrentPosition();
    }

    @Override
    public final boolean isEatable() {
        this.checkCreation();
        return this.eatable;
    }

    @Override
    public final void returnHome() {
        this.checkCreation();
        this.myBehaviour.setCurrentPosition(this.myBehaviour.getStartPosition());
    }

    @Override
    public final void setEatable(final boolean eatable) {
        this.checkCreation();
        this.eatable = eatable;
        if (this.isEatable()) {
            this.timeToTurn = true;
        }
    }

    @Override
    public final void blinkyIsDead() {
        this.checkCreation();
        if (this.getName().equals(Ghosts.INKY)) {
            this.isBlinkyDead = true;
        } else {
            throw new IllegalStateException("This method is designed only for Inky");
        }
    }

    @Override
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

    protected final Pair<Integer, Integer> getRelaxTarget() {
        return this.relaxTarget;
    }

    protected final void setRelaxTarget(final Pair<Integer, Integer> relaxTarget) {
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

