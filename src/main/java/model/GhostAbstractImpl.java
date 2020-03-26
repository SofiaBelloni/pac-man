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
        if(map.get(position)>=min) {
            return;
        }
        if (position.equals(targetPosition)) {
            min=map.get(position);
            return;
        }
        if (position.equals(currentPosition)) {
            map.put(position, 0);
        }
        Pair<Integer, Integer> up = new Pair<>(position.getX(), position.getY() + 1);
        Pair<Integer, Integer> down = new Pair<>(position.getX(), position.getY() - 1);
        Pair<Integer, Integer> left = new Pair<>(position.getX() - 1, position.getY());
        Pair<Integer, Integer> right = new Pair<>(position.getX() + 1, position.getY());
        if ( !virtualDir.equals(Directions.DOWN) && !setWall.contains(up) && up.getY() <= MAX && map.get(position) + 1 < map.get(up)) {
            map.put(up, map.get(position) + 1);
            findPath(up, targetPosition, Directions.UP);
        }
        if ( !virtualDir.equals(Directions.LEFT) && !setWall.contains(right) && right.getX() <= MAX && map.get(position) + 1 < map.get(right)) {
            map.put(right, map.get(position) + 1);
            findPath(right, targetPosition, Directions.RIGHT);
        }
        if ( !virtualDir.equals(Directions.UP) && !setWall.contains(down) && down.getY() >= 0 && map.get(position) + 1 < map.get(down)) {
            map.put(down, map.get(position) + 1);
            findPath(down, targetPosition,Directions.DOWN);
        }
        if ( !virtualDir.equals(Directions.RIGHT) && !setWall.contains(left) && left.getX() >= 0 && map.get(position) + 1 < map.get(left)) {
            map.put(left, map.get(position) + 1);
            findPath(left, targetPosition,Directions.LEFT);
        }
    }
    protected void move(Pair<Integer, Integer> appo) {
            setAdj(appo);
            if (map.get(appo) == 1) {
                if (currentPosition.equals(up)) {
                    dir=Directions.DOWN;
                }
                if (currentPosition.equals(down)) {
                    dir=Directions.UP;
                }
                if (currentPosition.equals(left)) {
                    dir=Directions.RIGHT;
                }
                if (currentPosition.equals(right)) {
                    dir=Directions.LEFT;
                }
                currentPosition = appo;
                return;
            }
            if (!setWall.contains(up) && up.getY() <= MAX && map.get(up) == map.get(appo) - 1) {
                move(up);
                return;
            }
            if (!setWall.contains(down) && down.getY() >= 0 && map.get(down) == map.get(appo) - 1) {
                move(down);
                return;
            }
            if (!setWall.contains(left) && left.getX() >= 0 && map.get(left) == map.get(appo) - 1) {
                move(left);
                return;
            }
            if (!setWall.contains(right) && right.getX() <= MAX && map.get(right) == map.get(appo) - 1) {
                move(right);
                return;
            }
        }

    @Override
    public boolean isEatable() {
        return this.eatable;
    }
}

