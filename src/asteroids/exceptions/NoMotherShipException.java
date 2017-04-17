package asteroids.exceptions;

@SuppressWarnings("serial")
public class NoMotherShipException extends IllegalArgumentException {

	  public NoMotherShipException() {
	    super("Cannot set load, Bullet does not have a parent");
	  }
	  
	  public NoMotherShipException(String message) {
	    super(message);
	  }

	  public NoMotherShipException(Throwable nested) {
	    super(nested);
	  }
}
