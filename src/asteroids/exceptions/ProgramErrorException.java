package asteroids.exceptions;

@SuppressWarnings("serial")
public class ProgramErrorException extends Exception {
	  
	  public ProgramErrorException(String message) {
	    super(message);
	  }

	  public ProgramErrorException(Throwable nested) {
	    super(nested);
	  }

}
