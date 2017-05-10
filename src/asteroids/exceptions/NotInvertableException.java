package asteroids.exceptions;

@SuppressWarnings("serial")
public class NotInvertableException extends ProgramException {

	  public NotInvertableException() {
	    super("Variable is not a double.");
	  }
	  
	  public NotInvertableException(String message) {
	    super(message);
	  }

	  public NotInvertableException(Throwable nested) {
	    super(nested);
	  }
}
