package model;

public interface Ghost extends MobileEntity {
    /**
     * @return if the ghost can be eaten by Pac-Man
     */
    boolean isEatable();
}
