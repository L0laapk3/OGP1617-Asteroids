package asteroids.exceptions;

@SuppressWarnings("serial")
public class ProgramException extends Exception {

	  public ProgramException() {
	    super("Generic fatal program error");
	  }
	  
	  public ProgramException(String message) {
	    super(message);
	  }

	  public ProgramException(Throwable nested) {
	    super(nested);
	  }

}
