package asteroids.model.program.statement;

import asteroids.model.program.Program;
import asteroids.model.program.expression.Expression;

public class Assignment extends Statement {

	public final String varname;
	public final Expression expression;
	
	public Assignment(String varname, Expression expression) {
		super();
		this.varname = varname;
		this.expression = expression;
	}

	@Override
	public boolean step(Program program) {
		program.variables.put(varname, expression.evaluate(program));
		return false;
	}

}
