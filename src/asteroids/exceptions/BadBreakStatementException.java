package asteroids.exceptions;

@SuppressWarnings("serial")
public class BadBreakStatementException extends ProgramException {

	  public BadBreakStatementException() {
	    super("A break statement is not allowed in this location.");
	  }
	  
	  public BadBreakStatementException(String message) {
	    super(message);
	  }

	  public BadBreakStatementException(Throwable nested) {
	    super(nested);
	  }
}
