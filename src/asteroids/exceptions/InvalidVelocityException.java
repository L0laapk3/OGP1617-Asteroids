package asteroids.exceptions;

@SuppressWarnings("serial")
public class InvalidVelocityException extends IllegalArgumentException {

	  public InvalidVelocityException() {
	    super("The Velocity of the ship is not valid.");
	  }
	  
	  public InvalidVelocityException(String message) {
	    super(message);
	  }

	  public InvalidVelocityException(Throwable nested) {
	    super(nested);
	  }
}
