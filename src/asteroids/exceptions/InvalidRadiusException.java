package asteroids.exceptions;

@SuppressWarnings("serial")
public class InvalidRadiusException extends IllegalArgumentException {

	  public InvalidRadiusException() {
	    super("The Radius of the ship should be at least MIN_RADIUS.");
	  }
	  
	  public InvalidRadiusException(String message) {
	    super(message);
	  }

	  public InvalidRadiusException(Throwable nested) {
	    super(nested);
	  }
}
