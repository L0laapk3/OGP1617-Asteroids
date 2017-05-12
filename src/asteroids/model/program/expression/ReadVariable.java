package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.*;

public class ReadVariable extends ContextAwareExpression {
	
	private final String varname;
	
	public ReadVariable(String varname) throws ProgramException {
		super();
		this.varname = varname;
	}
	
	public Object evaluate(Program program) throws ProgramException {
		System.out.println(this.variableContext);
		return this.variableContext.getVariable(varname);
	}
}
