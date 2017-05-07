package asteroids.model.program.expression;

import asteroids.model.program.*;

public class Variable extends Expression {
	
	private final String varname;
	
	public Variable(String varname) {
		super();
		this.varname = varname;
	}
	
	public Object evaluate(Program program) {
		return program.variables.get(varname);
	}
}
