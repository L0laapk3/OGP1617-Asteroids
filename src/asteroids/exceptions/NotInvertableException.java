package asteroids.exceptions;

@SuppressWarnings("serial")
public class NotInvertableException extends ProgramException {

	  public NotInvertableException() {
	    super("The time parameter cannot be negative.");
	  }
	  
	  public NotInvertableException(String message) {
	    super(message);
	  }

	  public NotInvertableException(Throwable nested) {
	    super(nested);
	  }
}
