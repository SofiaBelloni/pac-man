package model;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public abstract class GhostAbstractImpl implements Ghost {
    
    protected static final int MAX = 40;
    
    protected final Set<Pair<Integer, Integer>> setWall;
    protected final Map<Pair<Integer, Integer>, Integer> map;
    private boolean eatable;
    protected Pair<Integer, Integer> target;
    protected Pair<Integer, Integer> currentPosition;
    protected Pair<Integer, Integer> up, down, left, right;
    protected int min;
    protected Directions dir;
    protected boolean isRelaxed;

    public GhostAbstractImpl(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall) {
        this.map = map;
        this.setWall = setWall;
        eatable = false;
        currentPosition = new Pair<>(7,6);
        dir = Directions.UP;
        isRelaxed = true;
    }

    protected void setAdj(Pair<Integer, Integer> position) {
        up = new Pair<>(position.getX(), position.getY() + 1);
        down = new Pair<>(position.getX(), position.getY() - 1);
        left = new Pair<>(position.getX() - 1, position.getY());
        right = new Pair<>(position.getX() + 1, position.getY());
    }

    public Pair<Integer, Integer> getPosition() {
        return currentPosition;
    }

    protected int runAway() {
        Pair<Integer, Integer> appo = new Pair<>(currentPosition.getX(), currentPosition.getY());
        setAdj(currentPosition);
        Random r = new Random();
        while(appo.equals(currentPosition)) {
            if (dir.equals(Directions.DOWN)) {
                switch(r.nextInt(3)) {
                case 0:  
                    if (!setWall.contains(right)) {
                        currentPosition = right;
                        dir = Directions.RIGHT;
                    }    
                break;
                case 1:  
                    if (!setWall.contains(left)) {
                        currentPosition = left;
                        dir = Directions.LEFT;
                    }
                break;
                case 2:  
                    if (!setWall.contains(down)) {
                        currentPosition = down;
                        dir = Directions.DOWN;
                    }
                break;
                }
                  
          } else if (dir.equals(Directions.LEFT)) {
                  switch(r.nextInt(3)) {
                  case 0:  
                      if (!setWall.contains(left)) {
                          currentPosition = left;
                          dir = Directions.LEFT;
                      }  
                  break;
                  case 1:  
                      if (!setWall.contains(down)) {
                          currentPosition = down;
                          dir = Directions.DOWN;
                      }
                  break;
                  case 2:  
                      if (!setWall.contains(up)) {
                          currentPosition = up;
                          dir = Directions.UP;
                      }
                  break;
                  }
                  
              } else if (dir.equals(Directions.UP)) {
                  switch(r.nextInt(3)) {
                  case 0:  
                      if (!setWall.contains(right)) {
                          currentPosition = right;
                          dir = Directions.RIGHT;
                      }  
                  break;
                  case 1:  
                      if (!setWall.contains(up)) {
                          currentPosition = up;
                          dir = Directions.UP;
                      }
                  break;
                  case 2:  
                      if (!setWall.contains(left)) {
                          currentPosition = left;
                          dir = Directions.LEFT;
                      }
                  break;
                  }
              } else {
                  switch(r.nextInt(3)) {
                  case 0:  
                      if (!setWall.contains(right)) {
                          currentPosition = right;
                          dir = Directions.RIGHT;
                      }  
                  break;
                  case 1:  
                      if (!setWall.contains(up)) {
                          currentPosition = up;
                          dir = Directions.UP;
                      }
                  break;
                  case 2:  
                      if (!setWall.contains(down)) {
                          currentPosition = down;
                          dir = Directions.DOWN;
                      }
                  break;
                  }
              }
        }
              return 1;
    }

    public boolean relax() {
        findPath(currentPosition, target, dir);
        move(target);
        if (currentPosition.equals(target)) {
            return false;
        }
        return true;
    }
    protected void findPath(Pair<Integer,Integer> position,Pair<Integer,Integer> targetPosition, Directions virtualDir) {
        Set<Pair<Integer, Integer>> adj;
        boolean found=false;
        int i=0;
        map.put(position, i++);
        setAdj(position);
        if (up.getY()<=MAX && !setWall.contains(up) && !dir.equals(Directions.DOWN)) {
            map.put(up, i);
        }
        if (down.getY()>=0 && !setWall.contains(down) && !dir.equals(Directions.UP)) {
            map.put(down, i);
        }
        if (right.getX()<=MAX && !setWall.contains(right) && !dir.equals(Directions.LEFT)) {
            map.put(right, i);
        }
        if (left.getX()>=0 && !setWall.contains(left) && !dir.equals(Directions.RIGHT)) {
            map.put(left, i);
        }
        if (map.get(targetPosition)<900) {
            found=true;
        }
        while (!found) {
            for (Pair<Integer, Integer> p : map.keySet()) {
                if (map.get(p).equals(i)) {
                    setAdj(p);
                    if (((up.getY()<=MAX && !setWall.contains(up) && i < map.get(up)))) {
                        map.put(up, i + 1);
                    }
                    if (((right.getX()<=MAX && !setWall.contains(right) && i < map.get(right)))) {
                        map.put(right, i + 1);
                    } 
                    if (((left.getX()>=0 && !setWall.contains(left) && i < map.get(left)))) {
                        map.put(left, i + 1);
                    } 
                    if (((down.getY()>=0 && !setWall.contains(down) && i < map.get(down)))) {
                        map.put(down, i + 1);
                    }
                    if (map.get(targetPosition)<900) {
                        found=true;
                    }
                }
            }
            i++;
        }
    }
    
    
  
    protected void move(Pair<Integer, Integer> appo) {
        Pair<Integer, Integer> x=appo;
        int i= map.get(x);
        while (i > 1) {
            setAdj(x);
            if (up.getY()<=MAX && !setWall.contains(up) && map.get(up).equals(i-1)) {
                x = up;
                i = map.get(x);
            } else if (right.getX()<=MAX && !setWall.contains(right) && map.get(right).equals(i-1)) {
                x = right;
                i = map.get(x);
            } else if (down.getY()>=0 && !setWall.contains(down) && map.get(down).equals(i-1)) {
                x = down;
                i = map.get(x);
            } else if (left.getY()>=0 && !setWall.contains(left) && map.get(left).equals(i-1)) {
                x = left;
                i = map.get(x);
            } 
        }
        setAdj(x);
        if (currentPosition.equals(up)) {
            dir=Directions.DOWN;
        } else if (currentPosition.equals(right)) {
            dir=Directions.LEFT;
        } else if (currentPosition.equals(down)) {
            dir=Directions.UP;
        } else {
            dir=Directions.RIGHT;
        }
        currentPosition=x;
        
        }

    @Override
    public boolean isEatable() {
        return this.eatable;
    }
}

