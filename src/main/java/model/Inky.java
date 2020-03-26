package model;

import java.util.Map;
import java.util.Set;

public class Inky extends GhostAbstractImpl {

    private Pair<Integer, Integer> appo;
    private Pair<Integer, Integer> targett;
    private Pair<Integer, Integer> ris;
    private int targetX;
    private int targetY;
    private Pair<Integer, Integer> blinkyPosition;
    Set<Pair<Integer, Integer>> setWall;
    
    public Inky(Map<Pair<Integer, Integer>, Integer> map, Set<Pair<Integer, Integer>> setWall, Blinky blinky) {
        super(map, setWall);
        this.setWall = setWall;
        super.target=new Pair<>(MAX,0);
        this.blinkyPosition=blinky.getPosition();
    }
    
    public Pair<Integer,Integer> targetPosition(PacMan pacMan){
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
        if(blinkyPosition.getX()<=appo.getX()) {
            targetX=blinkyPosition.getX() + (Math.abs(appo.getX() - blinkyPosition.getX())*2);
        } else {
            targetX=blinkyPosition.getX() - (Math.abs(appo.getX() - blinkyPosition.getX())*2);
        }
        
        if(blinkyPosition.getY()<=appo.getY()) {
            targetY=blinkyPosition.getY() + (Math.abs(appo.getY() - blinkyPosition.getY())*2);
        } else {
            targetY=blinkyPosition.getY() - (Math.abs(appo.getY() - blinkyPosition.getY())*2);
        }
        ris = new Pair<>(targetX, targetY);
        
        if (ris.getX()>MAX) {
            ris = new Pair<>(MAX, ris.getY());
        }
        if (ris.getX()<0) {
            ris = new Pair<>(0, ris.getY());
        }
        if (ris.getY()>MAX) {
            ris = new Pair<>(ris.getX(), MAX);
        }
        if (ris.getY()<0) {
            ris = new Pair<>(ris.getY(), 0);
        }   
        if (setWall.contains(ris) || currentPosition.equals(ris)) {
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
         targett=targetPosition(pacMan);
        if(super.isEatable()) {
            super.runAway();
        }else {
            super.min=100000;
             for (Pair<Integer, Integer> p:map.keySet()) {
                 map.put(p, 1000);
             }
               if (isRelaxed) {
                   isRelaxed = relax();
               } else {
                   findPath(currentPosition, targett, super.dir);
                   move(targett);
               }
        }
        if (currentPosition.equals(pacMan.getPosition())) {
              return 1;
        } else {
              return 0;
        }

    }
}
