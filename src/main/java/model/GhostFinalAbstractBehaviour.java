package model;

import java.util.List;
import java.util.Set;

import utils.Directions;
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
    public final void move(final boolean timeToTurn) {
        this.rBehaviour.move(timeToTurn);
    }

    @Override
    public final void setCurrentPosition(final Pair<Integer, Integer> newPosition) {
        super.setCurrentPosition(newPosition);
        this.rBehaviour.setCurrentPosition(newPosition);
    }

    @Override 
    public final void setCurrentDirection(final Directions newDirection) {
        super.setCurrentDirection(newDirection);
        this.rBehaviour.setCurrentDirection(newDirection);
    }

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
