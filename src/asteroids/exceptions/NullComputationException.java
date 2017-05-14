package asteroids.exceptions;

@SuppressWarnings("serial")
public class NullComputationException extends ProgramException {

	  public NullComputationException() {
	    super("Tried to do a computation on null.");
	  }
	  
	  public NullComputationException(String message) {
	    super(message);
	  }

	  public NullComputationException(Throwable nested) {
	    super(nested);
	  }
}
