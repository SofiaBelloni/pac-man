package model;

import java.util.Optional;
import java.util.Set;

public class Inky extends GhostAbstractImpl {

    private Pair<Integer, Integer> blinkyPosition;
    Set<Pair<Integer, Integer>> setWall;
    private boolean isBlinkyDead;
    private BraveBehaviour blinky;
    
    public Inky(Set<Pair<Integer, Integer>> setWall, int xMap, int yMap, Blinky blinky) {
        super(setWall, xMap, yMap);
        this.setWall = setWall;
        super.target=new Pair<>(xMap,0);
        this.blinkyPosition=blinky.getPosition();
        super.c = new BraveInkyBehaviour(setWall, xMap, yMap);
        super.initialPosition = new Pair<>(7,6);
    }

     public int nextPosition(PacMan pacMan) {
         if (isEatable()) {
             f.runAway(currentPosition, dir);
                currentPosition = f.getNewPosition();
                dir = f.getNewDirection();
            } else {
                   if (isRelaxed) {
                       c.relax(currentPosition, target, dir); 
                        currentPosition = c.getNewPosition();
                        dir = c.getNewDirection();
                        if (currentPosition.equals(target)) {
                            isRelaxed = false;
                        }
                   } else {
                        if (isBlinkyDead) {
                            blinky.chase(currentPosition, pacMan, dir, Optional.of(blinkyPosition));
                            currentPosition = blinky.getNewPosition();
                            dir = blinky.getNewDirection();
                        } else {
                            c.chase(currentPosition, pacMan, dir, Optional.of(blinkyPosition));
                            currentPosition = c.getNewPosition();
                            dir = c.getNewDirection();
                        }
                   }
            }
            if (currentPosition.equals(pacMan.getPosition())) {
                  return 1;
            } else {
                  return 0;
            }
     }
}
