package asteroids.exceptions;

@SuppressWarnings("serial")
public class DoubleEntityException extends IllegalArgumentException {

	  public DoubleEntityException() {
	    super("The time parameter cannot be negative.");
	  }
	  
	  public DoubleEntityException(String message) {
	    super(message);
	  }

	  public DoubleEntityException(Throwable nested) {
	    super(nested);
	  }
}
