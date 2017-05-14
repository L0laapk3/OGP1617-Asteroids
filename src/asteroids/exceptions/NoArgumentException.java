package asteroids.exceptions;

@SuppressWarnings("serial")
public class NoArgumentException extends ProgramException {

	  public NoArgumentException() {
	    super("Not enough arguments passed to function.");
	  }
	  
	  public NoArgumentException(String message) {
	    super(message);
	  }

	  public NoArgumentException(Throwable nested) {
	    super(nested);
	  }
}
