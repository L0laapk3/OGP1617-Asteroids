package asteroids.exceptions;

@SuppressWarnings("serial")
public class NotOverlapException extends Exception {

	  public NotOverlapException() {
	    super("The entities overlap.");
	  }
	  
	  public NotOverlapException(String message) {
	    super(message);
	  }

	  public NotOverlapException(Throwable nested) {
	    super(nested);
	  }
}
