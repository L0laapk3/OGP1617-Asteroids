package asteroids.exceptions;

@SuppressWarnings("serial")
public class VariableDoesntExistException extends ProgramException {

	  public VariableDoesntExistException() {
	    super("Tried to read a variable that doesnt exist");
	  }
	  
	  public VariableDoesntExistException(String message) {
	    super(message);
	  }

	  public VariableDoesntExistException(Throwable nested) {
	    super(nested);
	  }
}
