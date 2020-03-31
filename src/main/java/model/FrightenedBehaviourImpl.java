package model;
import java.util.Random;
import java.util.Set;

public class FrightenedBehaviourImpl implements FrightenedBehaviour {
	
	private final int xMap;
	private final int yMap;
	private Pair<Integer, Integer> newPosition;
	private Directions newDirection;
	private final Set<Pair<Integer, Integer>> setWall;
	private Pair<Integer, Integer> up;
	private Pair<Integer, Integer> down;
	private Pair<Integer, Integer> left;
	private Pair<Integer, Integer> right;
	
	public FrightenedBehaviourImpl(final Set<Pair<Integer, Integer>> setWall, final int xMap, final int yMap) {
		this.setWall = setWall;
		this.xMap = xMap;
		this.yMap = yMap;
	}
	
    private void setAdj(Pair<Integer, Integer> position) {
    	up = new Pair<>(position.getX(), position.getY() + 1);
    	down = new Pair<>(position.getX(), position.getY() - 1);
	  	left = new Pair<>(position.getX() - 1, position.getY());
	  	right = new Pair<>(position.getX() + 1, position.getY());
    }

	public void runAway(Pair<Integer, Integer> currentPosition, Directions dir) {
		newPosition = currentPosition;
  	  	setAdj(newPosition);
  	  	Random r = new Random();
  	  	while(newPosition.equals(currentPosition)) {
	  	  	if (dir.equals(Directions.DOWN)) {
	  	  		switch(r.nextInt(3)) {
	  	  		case 0:  
	  	  			if (!setWall.contains(right) && right.getX() <= xMap) {
	  	  				newPosition = right;
	  	  				newDirection = Directions.RIGHT;
	  	  			}	 
	  	  		break;
	  	  		case 1:  
	  	  			if (!setWall.contains(left) && left.getX() >= 0) {
	  	  				newPosition = left;
	  	  				newDirection = Directions.LEFT;
	  	  			}
	  	  		break;
	  	  		case 2:  
	  	  			if (!setWall.contains(down) && down.getY() >= 0) {
	  	  				newPosition = down;
	  	  				newDirection = Directions.DOWN;
	  	  			}
	  	  		break;
	  	  		}
	    		  
		  } else if (dir.equals(Directions.LEFT)) {
	    		  switch(r.nextInt(3)) {
				  case 0:  
					  if (!setWall.contains(left) && left.getX() >= 0) {
						  newPosition = left;
						  newDirection = Directions.LEFT;
					  }	 
			      break;
				  case 1:  
					  if (!setWall.contains(down)  && down.getY() >= 0) {
						  newPosition = down;
						  newDirection = Directions.DOWN;
					  }
			      break;
				  case 2:  
					  if (!setWall.contains(up) && up.getY() <= yMap) {
						  newPosition = up;
						  newDirection = Directions.UP;
					  }
			      break;
				  }
	    		  
	    	  } else if (dir.equals(Directions.UP)) {
	    		  switch(r.nextInt(3)) {
				  case 0:  
					  if (!setWall.contains(right) && right.getX() <= xMap) {
						  newPosition = right;
						  newDirection = Directions.RIGHT;
					  }	 
			      break;
				  case 1:  
					  if (!setWall.contains(up) && up.getY() <= yMap) {
						  newPosition = up;
						  newDirection = Directions.UP;
					  }
			      break;
				  case 2:  
					  if (!setWall.contains(left) && left.getX() >= 0) {
						  newPosition = left;
						  newDirection = Directions.LEFT;
					  }
			      break;
	    		  }
	    	  } else {
	    		  switch(r.nextInt(3)) {
				  case 0:  
					  if (!setWall.contains(right) && right.getX() <= xMap) {
						  newPosition = right;
						  newDirection = Directions.RIGHT;
					  }	 
			      break;
				  case 1:  
					  if (!setWall.contains(up) && up.getY() <= yMap) {
						  newPosition = up;
						  newDirection = Directions.UP;
					  }
			      break;
				  case 2:  
					  if (!setWall.contains(down)  && down.getY() >= 0) {
						  newPosition = down;
						  newDirection = Directions.DOWN;
					  }
			      break;
	    		  }
	    	  }
  	  	}
	}

	@Override
	public Directions getNewDirection() {
		return newDirection;
	}

	@Override
	public Pair<Integer, Integer> getNewPosition() {
		return newPosition;
	}

}
