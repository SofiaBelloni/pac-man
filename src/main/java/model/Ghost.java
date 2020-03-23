package model;

public interface Ghost extends MobileEntity {

    boolean isEatable();

    int nextPosition(PacMan pacMan);

    Pair<Integer, Integer> targetPosition(PacMan pacMan);
}
