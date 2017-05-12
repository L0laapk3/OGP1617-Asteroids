package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.*;

public class ReadParameter extends FunctionContextAwareExpression {
	
	private final String argName;
	
	public ReadParameter(String argName) throws ProgramException {
		super();
		this.argName = argName;
	}
	
	public Object evaluate(Program program) throws ProgramException {
		return this.functionContext.getArgument(argName);
	}
}
