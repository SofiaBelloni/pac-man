package model;

import java.util.Map;
import java.util.Set;

public class Pinky extends GhostAbstractImpl {

    Set<Pair<Integer, Integer>> setWall;
    private Pair<Integer, Integer> ris;
    
    public Pinky(final Map<Pair<Integer, Integer>, Integer> map,final Set<Pair<Integer, Integer>> setWall) {
        super(map, setWall);
        super.target = new Pair<>(0,MAX);
        this.setWall=setWall;
    }
    
    public Pair<Integer,Integer> targetPosition(PacMan pacMan){
        Pair<Integer, Integer> pacManPosition = pacMan.getPosition();
        Directions pacManDirection = pacMan.getDirection();
        if (pacManDirection.equals(Directions.UP)) {
            if (!setWall.contains(new Pair<>(pacManPosition.getX(), pacManPosition.getY() + 4)) && pacManPosition.getY() + 4 <= MAX){
                ris = new Pair<>(pacManPosition.getX(), pacManPosition.getY() + 4);
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX(), pacManPosition.getY() + 3)) && pacManPosition.getY() + 3 <= MAX){
                ris = new Pair<>(pacManPosition.getX(), pacManPosition.getY() + 3);
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX(), pacManPosition.getY() + 2)) && pacManPosition.getY() + 2 <= MAX){
                ris = new Pair<>(pacManPosition.getX(), pacManPosition.getY() + 2);
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX(), pacManPosition.getY() + 1)) && pacManPosition.getY() + 1 <= MAX){
                ris = new Pair<>(pacManPosition.getX(), pacManPosition.getY() + 1);
            } else {
                ris = pacMan.getPosition();
            }
        } else if (pacManDirection.equals(Directions.RIGHT)) {
            if (!setWall.contains(new Pair<>(pacManPosition.getX() + 4, pacManPosition.getY())) && pacManPosition.getX() + 4 <= MAX){
                ris = new Pair<>(pacManPosition.getX() + 4, pacManPosition.getY());
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX() + 3, pacManPosition.getY())) && pacManPosition.getX() + 3 <= MAX){
                ris = new Pair<>(pacManPosition.getX() + 3, pacManPosition.getY());
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX() + 2, pacManPosition.getY())) && pacManPosition.getX() + 2 <= MAX){
                ris = new Pair<>(pacManPosition.getX() + 2, pacManPosition.getY());
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX() + 1, pacManPosition.getY())) && pacManPosition.getX() + 1 <= MAX){
                ris = new Pair<>(pacManPosition.getX() + 1, pacManPosition.getY());
            } else {
                ris = pacMan.getPosition();
            }
        } else if (pacManDirection.equals(Directions.DOWN)) {
            if (!setWall.contains(new Pair<>(pacManPosition.getX(), pacManPosition.getY() - 4)) && pacManPosition.getY() - 4 >= 0){
                ris = new Pair<>(pacManPosition.getX(), pacManPosition.getY() - 4);
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX(), pacManPosition.getY() - 3)) && pacManPosition.getY() - 3 >= 0){
                ris = new Pair<>(pacManPosition.getX(), pacManPosition.getY() - 3);
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX(), pacManPosition.getY() - 2)) && pacManPosition.getY() - 2 >= 0){
                ris = new Pair<>(pacManPosition.getX(), pacManPosition.getY() - 2);
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX(), pacManPosition.getY() - 1)) && pacManPosition.getY() - 1 >= 0){
                ris = new Pair<>(pacManPosition.getX(), pacManPosition.getY() - 1);
            } else {
                ris = pacMan.getPosition();
            }
        } else {
            if (!setWall.contains(new Pair<>(pacManPosition.getX() - 4, pacManPosition.getY())) && pacManPosition.getX() - 4 >= 0){
                ris = new Pair<>(pacManPosition.getX() - 4, pacManPosition.getY());
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX() - 3, pacManPosition.getY())) && pacManPosition.getX() - 3 >= 0){
                ris = new Pair<>(pacManPosition.getX() - 3, pacManPosition.getY());
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX() - 2, pacManPosition.getY())) && pacManPosition.getX() - 2 >= 0){
                ris = new Pair<>(pacManPosition.getX() - 2, pacManPosition.getY());
            } else if (!setWall.contains(new Pair<>(pacManPosition.getX() - 1, pacManPosition.getY())) && pacManPosition.getX() - 1 >= 0){
                ris = new Pair<>(pacManPosition.getX() - 1, pacManPosition.getY());
            } else {
                ris = pacMan.getPosition();
            }
        }
        
        if (currentPosition.equals(ris)) {
            setAdj(ris);
            if (!setWall.contains(up)) {
                ris = up;
            } else if (!setWall.contains(right)) {
                ris = right;
            } else if (!setWall.contains(down)) {
                ris = down;
            } else {
                ris = left;
            }
        } 
        
        return ris;
    }
    
    
     public int nextPosition(PacMan pacMan) {
            if (super.isEatable()) {
                super.runAway();
            } else {
                super.min=100000;
                 for (Pair<Integer, Integer> p:map.keySet()) {
                     map.put(p, 1000);
                 }
                   if (isRelaxed) {
                       isRelaxed = relax();
                   } else {
                       findPath(currentPosition, targetPosition(pacMan), super.dir);
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
