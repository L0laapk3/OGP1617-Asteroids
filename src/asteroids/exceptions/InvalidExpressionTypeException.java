package asteroids.exceptions;

@SuppressWarnings("serial")
public class InvalidExpressionTypeException extends Exception {

	  public InvalidExpressionTypeException() {
	    super("The expression did not return an object with a valid type.");
	  }
	  
	  public InvalidExpressionTypeException(String message) {
	    super(message);
	  }

	  public InvalidExpressionTypeException(Throwable nested) {
	    super(nested);
	  }
}
