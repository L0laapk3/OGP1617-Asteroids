package asteroids.exceptions;

@SuppressWarnings("serial")
public class MisMatchWorldsException extends IllegalArgumentException {

	  public MisMatchWorldsException() {
	    super("Entities are not in the same world.");
	  }
	  
	  public MisMatchWorldsException(String message) {
	    super(message);
	  }

	  public MisMatchWorldsException(Throwable nested) {
	    super(nested);
	  }
}
