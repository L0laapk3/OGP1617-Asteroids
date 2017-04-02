package asteroids.exceptions;

@SuppressWarnings("serial")
public class NotWithinBoundariesException extends IllegalArgumentException {

	  public NotWithinBoundariesException() {
	    super("The time parameter cannot be negative.");
	  }
	  
	  public NotWithinBoundariesException(String message) {
	    super(message);
	  }

	  public NotWithinBoundariesException(Throwable nested) {
	    super(nested);
	  }
}
