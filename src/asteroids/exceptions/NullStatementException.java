package asteroids.exceptions;

@SuppressWarnings("serial")
public class NullStatementException extends ProgramException {

	  public NullStatementException() {
	    super("One of the Statement arguments was null.");
	  }
	  
	  public NullStatementException(String message) {
	    super(message);
	  }

	  public NullStatementException(Throwable nested) {
	    super(nested);
	  }
}
