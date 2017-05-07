package asteroids.exceptions;

@SuppressWarnings("serial")
public class NegativeTimeException extends InvalidTimeException {

	  public NegativeTimeException() {
	    super("The time parameter cannot be negative.");
	  }
	  
	  public NegativeTimeException(String message) {
	    super(message);
	  }

	  public NegativeTimeException(Throwable nested) {
	    super(nested);
	  }
}
