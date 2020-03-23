package model;

import java.util.Map;
import java.util.Set;

public class Pinky extends GhostAbstractImpl {

    public Pinky(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall) {
        super(map, setWall);
        super.target = new Pair<>(0,MAX);
    }

    public Pair<Integer,Integer> targetPosition(PacMan pacMan){
        Pair<Integer, Integer> pacManPosition = pacMan.getPosition();
        Directions pacManDirection = pacMan.getDirection();
        if (pacManDirection.equals(Directions.UP)) {
            if (pacManPosition.getY() + 4 > MAX) {
                return new Pair<>(pacManPosition.getX(), MAX);
            } else {
                return new Pair<>(pacManPosition.getX(), pacManPosition.getY() + 4);
            }
        } else if (pacManDirection.equals(Directions.RIGHT)) {
            if (pacManPosition.getX() + 4 > MAX) {
                return new Pair<>(MAX, pacManPosition.getY());
            } else {
                return new Pair<>(pacManPosition.getX() + 4, pacManPosition.getY());
            }
        } else if (pacManDirection.equals(Directions.DOWN)) {
            if (pacManPosition.getY() - 4 < 0) {
                return new Pair<>(pacManPosition.getX(), 0);
            } else {
                return new Pair<>(pacManPosition.getX(),pacManPosition.getY() - 4);
            }
        } else {
            if (pacManPosition.getX() - 4 < 0) {
                return new Pair<>(0, pacManPosition.getY());
            } else {
                return new Pair<>(pacManPosition.getX() - 4, pacManPosition.getY());
            }
        }
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
