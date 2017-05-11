package asteroids.exceptions;

@SuppressWarnings("serial")
public class NotDoubleException extends ProgramException {

	  public NotDoubleException() {
	    super("Variable is not a double.");
	  }
	  
	  public NotDoubleException(String message) {
	    super(message);
	  }

	  public NotDoubleException(Throwable nested) {
	    super(nested);
	  }
}
