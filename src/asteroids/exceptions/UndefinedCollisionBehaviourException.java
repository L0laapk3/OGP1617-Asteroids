package asteroids.exceptions;

import asteroids.model.Entity;

@SuppressWarnings("serial")
public class UndefinedCollisionBehaviourException extends Exception {
	  
	  public UndefinedCollisionBehaviourException() {
	    super("A collision has happened of which the behaviour of the collision between the two entity types is not defined");
	  }
	
	  public UndefinedCollisionBehaviourException(Entity first, Entity second) {
	    super("A collision has happened of which the behaviour of the collision between the two entity types is not defined: "
	    		+ first.getClass().getName() + ", "  + second.getClass().getName());
	  }
	  
	  public UndefinedCollisionBehaviourException(String message) {
	    super(message);
	  }

	  public UndefinedCollisionBehaviourException(Throwable nested) {
	    super(nested);
	  }
}
