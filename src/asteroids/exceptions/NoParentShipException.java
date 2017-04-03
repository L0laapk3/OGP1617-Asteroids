package asteroids.exceptions;

@SuppressWarnings("serial")
public class NoParentShipException extends IllegalArgumentException {

	  public NoParentShipException() {
	    super("A parent ship must be assigned to a bullet before it can be loaded to that parent ship.");
	  }
	  
	  public NoParentShipException(String message) {
	    super(message);
	  }

	  public NoParentShipException(Throwable nested) {
	    super(nested);
	  }
}
