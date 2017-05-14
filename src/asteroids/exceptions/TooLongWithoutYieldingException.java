package asteroids.exceptions;

@SuppressWarnings("serial")
public class TooLongWithoutYieldingException extends ProgramException {

	  public TooLongWithoutYieldingException() {
	    super("Program went too long without yielding. Perhaps it is stuck in an infinite loop?");
	  }
	  
	  public TooLongWithoutYieldingException(String message) {
	    super(message);
	  }

	  public TooLongWithoutYieldingException(Throwable nested) {
	    super(nested);
	  }
}
