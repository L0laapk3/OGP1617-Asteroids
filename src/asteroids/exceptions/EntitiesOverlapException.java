package asteroids.exceptions;

@SuppressWarnings("serial")
public class EntitiesOverlapException extends Exception {

	  public EntitiesOverlapException() {
	    super("The given entities should not overlap.");
	  }
	  
	  public EntitiesOverlapException(String message) {
	    super(message);
	  }

	  public EntitiesOverlapException(Throwable nested) {
	    super(nested);
	  }
}
