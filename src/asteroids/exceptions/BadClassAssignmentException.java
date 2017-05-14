package asteroids.exceptions;

@SuppressWarnings("serial")
public class BadClassAssignmentException extends ProgramException {

	  public BadClassAssignmentException() {
	    super("The variable was already assigned a value of a different type, needs to match types.");
	  }
	  
	  public BadClassAssignmentException(String message) {
	    super(message);
	  }

	  public BadClassAssignmentException(Throwable nested) {
	    super(nested);
	  }
}
