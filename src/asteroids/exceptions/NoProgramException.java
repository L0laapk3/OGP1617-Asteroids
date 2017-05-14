package asteroids.exceptions;

@SuppressWarnings("serial")
public class NoProgramException extends IllegalArgumentException {

	  public NoProgramException() {
	    super("This ship does not have a program assigned to it.");
	  }
	  
	  public NoProgramException(String message) {
	    super(message);
	  }

	  public NoProgramException(Throwable nested) {
	    super(nested);
	  }
}
