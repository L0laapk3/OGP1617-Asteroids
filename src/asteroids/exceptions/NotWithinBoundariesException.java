package asteroids.exceptions;

@SuppressWarnings("serial")
public class NotWithinBoundariesException extends IllegalArgumentException {

	  public NotWithinBoundariesException() {
	    super("The given enity does not lay fully within the boundaries of the selected world.");
	  }
	  
	  public NotWithinBoundariesException(String message) {
	    super(message);
	  }

	  public NotWithinBoundariesException(Throwable nested) {
	    super(nested);
	  }
}
