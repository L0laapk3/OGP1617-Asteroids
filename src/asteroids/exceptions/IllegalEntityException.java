package asteroids.exceptions;

@SuppressWarnings("serial")
public class IllegalEntityException extends IllegalArgumentException {

	  public IllegalEntityException() {
	    super("The given Entity must be a valid entity.");
	  }
	  
	  public IllegalEntityException(String message) {
	    super(message);
	  }

	  public IllegalEntityException(Throwable nested) {
	    super(nested);
	  }
}
