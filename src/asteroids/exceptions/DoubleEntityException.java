package asteroids.exceptions;

@SuppressWarnings("serial")
public class DoubleEntityException extends IllegalArgumentException {

	  public DoubleEntityException() {
	    super("This entity is already in this world.");
	  }
	  
	  public DoubleEntityException(String message) {
	    super(message);
	  }

	  public DoubleEntityException(Throwable nested) {
	    super(nested);
	  }
}
