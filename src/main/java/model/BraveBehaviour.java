package model;

import java.util.Optional;

public interface BraveBehaviour {

    void chase(Pair<Integer, Integer> currentPosition, PacMan pacMan, Directions dir, Optional<Pair<Integer, Integer>> blinkyPosition);

    void relax(Pair<Integer, Integer> currentPosition, Pair<Integer, Integer> targetPosition, Directions dir);

    Directions getNewDirection();

    Pair<Integer, Integer> getNewPosition();
}
