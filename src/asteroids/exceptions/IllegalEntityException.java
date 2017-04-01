package asteroids.exceptions;

@SuppressWarnings("serial")
public class IllegalEntityException extends IllegalArgumentException {

	  public IllegalEntityException() {
	    super("The time parameter cannot be negative.");
	  }
	  
	  public IllegalEntityException(String message) {
	    super(message);
	  }

	  public IllegalEntityException(Throwable nested) {
	    super(nested);
	  }
}
