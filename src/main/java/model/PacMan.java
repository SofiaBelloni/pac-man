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
   * @return the remaining lives of PacMan
   */
  int getLives();
  /**
   * Decreases the life of PacMan.
   */
  void kill();

}
