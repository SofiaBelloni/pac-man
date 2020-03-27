package model;

public interface PacMan extends MobileEntity {
  /**
   * Set the PacMan direction.
   * 
   * @param direction the new direction of PacMan
   */
  void setDirection(Directions direction);
  /**
   * Move PacMan to the next position.
   */
  void nextPosition();
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
   * Decreases the life of PacMan.
   */
  void kill();

}
