package model;

import java.util.Optional;

public interface PacMan extends MobileEntity {
  /**
   * Set the mobile entity direction.
   * 
   * @param direction the new direction of the entity
   */
  void setDirection(Optional<Directions> direction);

  /**
   * Get the mobile entity direction.
   * 
   * @return the direction of the entity
   */
  Optional<Directions> getDirection();
  /**
 * @return the remaining lives of PacMan
 */
int getLives();

}
