package model;

public interface Ghost extends Entity {

    boolean isEatable();

    int nextPosition(PacMan pacMan);

    Pair<Integer, Integer> targetPosition(PacMan pacMan);
}
