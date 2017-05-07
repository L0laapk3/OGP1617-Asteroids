package asteroids.exceptions;

@SuppressWarnings("serial")
public class InvalidTimeException extends Exception {

	  public InvalidTimeException() {
	    super("The time parameter should be an number.");
	  }
	  
	  public InvalidTimeException(String message) {
	    super(message);
	  }

	  public InvalidTimeException(Throwable nested) {
	    super(nested);
	  }
}
