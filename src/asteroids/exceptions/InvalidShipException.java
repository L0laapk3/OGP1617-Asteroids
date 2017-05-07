package asteroids.exceptions;

@SuppressWarnings("serial")
public class InvalidShipException extends Exception {

	  public InvalidShipException() {
	    super("Ship parameter is not valid.");
	  }
	  
	  public InvalidShipException(String message) {
	    super(message);
	  }

	  public InvalidShipException(Throwable nested) {
	    super(nested);
	  }
}
