package asteroids.exceptions;

@SuppressWarnings("serial")
public class NoParentException extends IllegalArgumentException {

	  public NoParentException() {
	    super("Cannot set load, Bullet does not have a parent");
	  }
	  
	  public NoParentException(String message) {
	    super(message);
	  }

	  public NoParentException(Throwable nested) {
	    super(nested);
	  }
}
