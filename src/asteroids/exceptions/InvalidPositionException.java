package asteroids.exceptions;

@SuppressWarnings("serial")
public class InvalidPositionException extends IllegalArgumentException {

	  public InvalidPositionException() {
	    super("The Position of the ship is not valid.");
	  }
	  
	  public InvalidPositionException(String message) {
	    super(message);
	  }

	  public InvalidPositionException(Throwable nested) {
	    super(nested);
	  }
}
