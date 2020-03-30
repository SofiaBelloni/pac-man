package model;

import java.util.Optional;

public abstract class BraveAbstractBehaviour implements BraveBehaviour {

    @Override
    public void chase(Pair<Integer, Integer> currentPosition, PacMan pacMan, Directions dir,
            Optional<Pair<Integer, Integer>> blinkyPosition) {
        // TODO Auto-generated method stub

    }

    @Override
    public void relax(Pair<Integer, Integer> currentPosition, Pair<Integer, Integer> targetPosition, Directions dir) {
        // TODO Auto-generated method stub

    }

    @Override
    public Directions getNewDirection() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pair<Integer, Integer> getNewPosition() {
        // TODO Auto-generated method stub
        return null;
    }

}
