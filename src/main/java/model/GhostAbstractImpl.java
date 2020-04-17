package model;

import java.util.Set;

/**
 * this class implements a generic Ghost Entity.
 *
 */
public abstract class GhostAbstractImpl extends EntityAbstractImpl implements Ghost {

    private final Set<Pair<Integer, Integer>> setWall;
    private final Set<Pair<Integer, Integer>> ghostHouse;
    private boolean eatable;
    private boolean isRelaxed;
    private boolean timeToTurn;
    private boolean isInside;
    private boolean created;
    private Ghosts name;
    private GhostBehaviour myBehaviour;
    private final PacMan pacMan;

    public GhostAbstractImpl(final Set<Pair<Integer, Integer>> setWall,
            final Set<Pair<Integer, Integer>> ghostHouse, final PacMan pacMan, final int xMapSize, final int yMapSize) {
        super(xMapSize, yMapSize);
        this.isRelaxed = true;
        this.isInside = true;
        this.created = false;
        this.timeToTurn = false;
        this.eatable = false;
        this.setWall = setWall;
        this.ghostHouse = ghostHouse;
        this.pacMan = pacMan;
    }

    @Override
    public final void nextPosition() {
        this.checkCreation();
        if (this.isInside && !this.ghostHouse.contains(this.getPosition())) {
            this.setWall.addAll(this.ghostHouse);
            this.isInside = false;
        }
        if (this.eatable) {
            if (this.timeToTurn) {
                 if (!this.isInside && !this.ghostHouse.contains(new PairImpl<>(this.getPosition().getX(), this.getPosition().getY() - 1))) {
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
                if (this.getPosition().equals(this.myBehaviour.getRelaxTarget())) {
                    this.isRelaxed = false;
                }
            } else {
                this.myBehaviour.chase(this.pacMan);
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
    public final void returnHome() {
        this.checkCreation();
        this.setWall.removeAll(this.ghostHouse);
        this.myBehaviour.setCurrentPosition(this.myBehaviour.getStartPosition());
    }

    @Override
    public final void setEatable(final boolean eatable) {
        this.checkCreation();
        this.eatable = eatable;
        if (this.eatable) {
            this.timeToTurn = true;
        }
    }

    @Override
    public final void blinkyIsDead() {
        this.checkCreation();
        if (this.getName().equals(Ghosts.INKY)) {
            this.myBehaviour.setBlinkyDead();
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

    protected final void setMyBehaviour(final GhostBehaviour myBehaviour) {
        this.myBehaviour = myBehaviour;
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

