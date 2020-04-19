package model;

public interface PacMan extends Entity {
  /**
   * Set the PacMan direction.
   * 
   * @param direction the new direction of PacMan
   */
  void setDirection(Directions direction);
  /**
   * Get the PacMan direction.
   * 
   * @return the direction of PacMan
   */
  Directions getDirection();
  /**
   * @return the remaining lives of PacMan
   */
  int getLives();
  /**
   * Decreases the life of PacMan and PacMan return to the startPosition.
   */
  void kill();
}
