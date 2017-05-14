package asteroids.exceptions;

@SuppressWarnings("serial")
public class NotAFunctionException extends ProgramException {

	  public NotAFunctionException() {
	    super("Variable is not a function.");
	  }
	  
	  public NotAFunctionException(String message) {
	    super(message);
	  }

	  public NotAFunctionException(Throwable nested) {
	    super(nested);
	  }
}
