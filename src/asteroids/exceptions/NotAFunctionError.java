package asteroids.exceptions;

@SuppressWarnings("serial")
public class NotAFunctionError extends ProgramException {

	  public NotAFunctionError() {
	    super("Variable is not a function.");
	  }
	  
	  public NotAFunctionError(String message) {
	    super(message);
	  }

	  public NotAFunctionError(Throwable nested) {
	    super(nested);
	  }
}
