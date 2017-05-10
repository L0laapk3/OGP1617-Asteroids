package asteroids.exceptions;

@SuppressWarnings("serial")
public class NotComparableException extends ProgramException {

	  public NotComparableException() {
	    super("The variables are not comparable.");
	  }
	  
	  public NotComparableException(String message) {
	    super(message);
	  }

	  public NotComparableException(Throwable nested) {
	    super(nested);
	  }
}
