package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.*;

public class ReadVariable extends ContextAwareExpression {
	
	private final String varname;
	
	public ReadVariable(String varname) {
		super();
		this.varname = varname;
	}
	
	public Object evaluate(Program program) throws ProgramException {
		return this.variableContext.getVariable(varname);
	}
}
