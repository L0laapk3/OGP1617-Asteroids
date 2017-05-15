package asteroids.exceptions;

@SuppressWarnings("serial")
public class ForSomeReasonNotAllowedError extends ProgramException {

	  public ForSomeReasonNotAllowedError() {
	    super("This action is for some reason not allowed by the tests");
	  }
	  
	  public ForSomeReasonNotAllowedError(String message) {
	    super(message);
	  }

	  public ForSomeReasonNotAllowedError(Throwable nested) {
	    super(nested);
	  }
}
