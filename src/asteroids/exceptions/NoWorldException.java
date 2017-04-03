package asteroids.exceptions;

@SuppressWarnings("serial")
public class NoWorldException extends IllegalArgumentException {

	  public NoWorldException() {
	    super("Entity is not in a world.");
	  }
	  
	  public NoWorldException(String message) {
	    super(message);
	  }

	  public NoWorldException(Throwable nested) {
	    super(nested);
	  }
}
