package model;

public interface FrightenedBehaviour {

    void runAway(Pair<Integer, Integer> currentPosition, Directions dir);

    Directions getNewDirection();

    Pair<Integer, Integer> getNewPosition();

}
