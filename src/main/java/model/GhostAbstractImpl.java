package model;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public abstract class GhostAbstractImpl implements Ghost {
    protected static final int MAX = 20;
    private boolean eatable;
    protected Map<Pair<Integer, Integer>, Integer> map;
    private Set<Pair<Integer, Integer>> setWall;
    protected Pair<Integer, Integer> target;
    protected int distance;
    protected Pair<Integer, Integer> currentPosition;
    private Directions dir;
    protected boolean isRelaxed;
    private Pair<Integer, Integer> up, down, left, right;

    public GhostAbstractImpl(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall) {
        currentPosition = new Pair<>(7, 6);
        dir = Directions.UP;
        isRelaxed = true;
        this.map = map;
        this.setWall = setWall;
        this.eatable = false;
    }

    private void setAdj(Pair<Integer, Integer> position) {
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
        findPath(currentPosition, target);
        if (currentPosition.equals(target)) {
            return false;
        }
        return true;
    }
    public void findPath(Pair<Integer,Integer> position,Pair<Integer,Integer> targetPosition) {
        if (position.equals(targetPosition)) {
            distance = map.get(position);
            return;
        }
        if (position.equals(currentPosition)) {
            map.put(position, 0);
        }
        setAdj(position);
        if (!setWall.contains(up) && up.getY() <= MAX && map.get(position) + 1 < map.get(up)) {
            map.put(up, map.get(position) + 1);
            findPath(up, targetPosition);
        }
        if (!setWall.contains(right) && right.getX() <= MAX && map.get(position) + 1 < map.get(right)) {
            map.put(right, map.get(position) + 1);
            findPath(right, targetPosition);
        }
        if (!setWall.contains(down) && down.getY() >= 0 && map.get(position) + 1 < map.get(down)) {
            map.put(down, map.get(position) + 1);
            findPath(down, targetPosition);
        }
        if (!setWall.contains(left) && left.getX() >= 0 && map.get(position) + 1 < map.get(left)) {
            map.put(left, map.get(position) + 1);
            findPath(left, targetPosition);
        }
    }
    public void move(Pair<Integer, Integer> appo) {

        setAdj(appo);
        if (map.get(appo) == 1) {
            currentPosition = appo;
            return;
        } else {
            if (!setWall.contains(up) && up.getY() <= MAX && map.get(up) == map.get(appo) - 1) {
                dir = Directions.UP;
                move(up);
            }
            if (!setWall.contains(down) && down.getY() >= 0 && map.get(down) == map.get(appo) - 1) {
                dir = Directions.DOWN;
                move(down);
            }
            if (!setWall.contains(left) && left.getX() >= 0 && map.get(left) == map.get(appo) - 1) {
                dir = Directions.LEFT;
                move(left);
            }
            if (!setWall.contains(right) && right.getX() <= MAX && map.get(right) == map.get(appo) - 1) {
                dir = Directions.RIGHT;
                move(right);
            }
        }
    }

    @Override
    public boolean isEatable() {
        return this.eatable;
    }
}

