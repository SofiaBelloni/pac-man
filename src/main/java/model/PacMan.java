package model;

public interface PacMan extends MobileEntity {
  /**
   * Set the mobile entity direction.
   * 
   * @param direction the new direction of the entity
   */
  void setDirection(Directions direction);

  /**
   * Get the mobile entity direction.
   * 
   * @return the direction of the entity
   */
  Directions getDirection();

  /**
   * Moves Pac-Man to the next position based on the direction.
   */
  void nextPosition();

  /**
   * Get the PacMan position.
   * 
   * @return the position of PacMan
   */
  Pair<Integer, Integer> getPosition();

}
