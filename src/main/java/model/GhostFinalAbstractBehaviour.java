package model;

import java.util.List;
import java.util.Set;

import utils.Pair;

/**
 *This class implements a generic ghost final behaviour.
 *
 */
public abstract class GhostFinalAbstractBehaviour extends GhostSmartAbstractBehaviour implements GhostFinalBehaviour {

    private final GhostRandomBehaviour rBehaviour;

    public GhostFinalAbstractBehaviour(final Set<Pair<Integer, Integer>> walls, final PacMan pacMan,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> startPosition) {
        super(walls, pacMan, ghostHouse, yMapSize, yMapSize, startPosition);
        this.rBehaviour = new GhostRandomBehaviourImpl(walls, ghostHouse, xMapSize, yMapSize, startPosition);
        this.setCurrentPosition(startPosition);
    }

    @Override
    public final void nextPosition(final boolean eatable, final boolean timeToTurn, final Ghosts name) {
        this.checkIfInside();
        if (timeToTurn) {
            this.setRelaxed(false);
        }
        if (eatable || (name.equals(Ghosts.OLDLEVEL) && !this.isRelaxed())) {
            if (timeToTurn || !moveIfStuck()) {
                this.rBehaviour.move(timeToTurn);
                this.setCurrentPosition(this.rBehaviour.getCurrentPosition());
                this.setCurrentDirection(this.rBehaviour.getCurrentDirection());
            }
        } else {
            if (this.isInside() || !moveIfStuck()) {
                if (this.isRelaxed()) {
                    this.relax(name, eatable);
                } else {
                    this.findPath(this.getChaseTarget());
                }
            }
        }
        this.rBehaviour.setCurrentPosition(this.getCurrentPosition());
        this.rBehaviour.setCurrentDirection(this.getCurrentDirection());
    }

    protected abstract Pair<Integer, Integer> getChaseTarget();

    @Override
    public final void returnHome(final Pair<Integer, Integer> newPosition) {
        super.returnHome(newPosition);
        this.rBehaviour.returnHome(newPosition);
        this.setRelaxed(true);
    }

    @Override
    public final void checkIfInside() {
        super.checkIfInside();
        this.rBehaviour.checkIfInside();
    }

    protected final GhostRandomBehaviour getRandomBehaviour() {
        return this.rBehaviour;
    }

}
