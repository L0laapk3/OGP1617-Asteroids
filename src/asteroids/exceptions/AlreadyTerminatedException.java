package asteroids.exceptions;

@SuppressWarnings("serial")
public class AlreadyTerminatedException extends IllegalArgumentException {

	  public AlreadyTerminatedException() {
	    super("Entity is already terminated");
	  }
	  
	  public AlreadyTerminatedException(String message) {
	    super(message);
	  }

	  public AlreadyTerminatedException(Throwable nested) {
	    super(nested);
	  }
}
