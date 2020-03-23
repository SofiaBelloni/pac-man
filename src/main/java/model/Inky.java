package model;

import java.util.Map;
import java.util.Set;

public class Inky extends GhostAbstractImpl {

    private Pair<Integer, Integer> appo;
    private int targetX;
    private int targetY;
    private Pair<Integer, Integer> blinkyPosition;

    public Inky(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall, final Blinky blinky) {
        super(map, setWall);
        super.target = new Pair<>(MAX,0);
        this.blinkyPosition = blinky.getPosition();
    }

    public Pair<Integer, Integer> targetPosition(PacMan pacMan){
        Pair<Integer, Integer> pacManPosition = pacMan.getPosition();
        Directions pacManDirection = pacMan.getDirection();
        if (pacManDirection.equals(Directions.UP)) {
            appo = new Pair<>(pacManPosition.getX(), pacManPosition.getY() + 2);
        } else if (pacManDirection.equals(Directions.RIGHT)) {
            appo = new Pair<>(pacManPosition.getX() + 2, pacManPosition.getY());
        } else if (pacManDirection.equals(Directions.DOWN)) {
            appo = new Pair<>(pacManPosition.getX(), pacManPosition.getY() - 2);
        } else {
            appo = new Pair<>(pacManPosition.getX() - 2, pacManPosition.getY());
        }
        targetX = blinkyPosition.getX() + (pacManPosition.getX() - blinkyPosition.getX() * 2);
        targetY = blinkyPosition.getY() + (pacManPosition.getY() - blinkyPosition.getY() * 2);
        return new Pair<>(targetX, targetY);

    }

     public int nextPosition(PacMan pacMan) {
        if (super.isEatable()) {
            super.runAway();
        } else {
             for (Pair<Integer, Integer> p:map.keySet()) {
                 map.put(p, 1000);
             }
               if (isRelaxed) {
                   isRelaxed = relax();
               } else {
                   findPath(currentPosition, targetPosition(pacMan));
                   move(targetPosition(pacMan));
               }
        }
        if (currentPosition.equals(pacMan.getPosition())) {
              return 1;
        } else {
              return 0;
        }

    }
}
