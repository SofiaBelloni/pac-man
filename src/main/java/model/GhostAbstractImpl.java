package model;

import utils.GhostUtils;
import utils.Pair;

/**
 * this class implements a generic Ghost Entity.
 *
 */
public abstract class GhostAbstractImpl extends EntityAbstractImpl implements Ghost {

    private boolean eatable;
    private boolean created;
    private Ghosts name;
    private GhostFinalBehaviour myBehaviour;
    private boolean timeToTurn;
    private Pair<Integer, Integer> startPosition;
    private final int id;
    private GhostUtils myUtils;

    public GhostAbstractImpl(final int xMapSize, final int yMapSize, final int id) {
        super(xMapSize, yMapSize);
        this.created = false;
        this.eatable = false;
        this.timeToTurn = false;
        this.id = id;
    }

    @Override
    public final void nextPosition() {
        this.checkCreation();
        this.myBehaviour.nextPosition(this.eatable, this.timeToTurn, this.name);
        this.timeToTurn = false;
        this.myBehaviour.setCurrentPosition(this.convertToToroidal(this.getPosition()));
        this.myUtils.setGhostPosition(this.myBehaviour.getCurrentPosition());
        this.myUtils.setGhostDirection(this.myBehaviour.getCurrentDirection());
    }

    @Override
    public final Pair<Integer, Integer> getPosition() {
        this.checkCreation();
        return this.myBehaviour.getCurrentPosition();
    }

    @Override
    public final void returnToStartPosition() {
        this.checkCreation();
        this.myBehaviour.returnHome(this.startPosition);
    }

    @Override
    public final Directions getDirection() {
        return this.myBehaviour.getCurrentDirection();
    }

    @Override
    public final boolean isEatable() {
        this.checkCreation();
        return this.eatable;
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

    @Override
    public final void setName(final Ghosts name) {
        this.name = name;
        if (name.equals(Ghosts.OLDLEVEL)) {
            this.myUtils.setOldLevel();
        }
    }

    protected final void setMyBehaviour(final GhostFinalBehaviour myBehaviour) {
        this.myBehaviour = myBehaviour;
    }

    protected final void setCreated() {
        this.created = true;
    }

    protected final Pair<Integer, Integer> getStartPosition() {
        this.checkCreation();
        return this.startPosition;
    }

    protected final void setStartPosition(final Pair<Integer, Integer> startPosition) {
        this.startPosition = startPosition;
    }

    @Override
    public final int getId() {
        this.checkCreation();
        return this.id;
    }

    private void checkCreation() {
        if (!this.created) {
            throw new IllegalStateException("Error, ghost not created");
        }
    }

    @Override
    public final GhostUtils getMyUtils() {
        return this.myUtils;
    }

    protected final void setMyUtils() {
        this.myUtils = new GhostUtils(this.startPosition, this.name, this.getDirection());
    }
}

