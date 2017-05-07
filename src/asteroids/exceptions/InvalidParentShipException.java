package asteroids.exceptions;

@SuppressWarnings("serial")
public class InvalidParentShipException extends InvalidShipException {

	  public InvalidParentShipException() {
	    super("A parent ship must be assigned to a bullet before it can be loaded to that parent ship.");
	  }
	  
	  public InvalidParentShipException(String message) {
	    super(message);
	  }

	  public InvalidParentShipException(Throwable nested) {
	    super(nested);
	  }
}
