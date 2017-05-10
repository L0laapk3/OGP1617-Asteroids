package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.Expression;

public class Assignment extends ContextAwareStatement {

	public final String varname;
	public final Expression expression;
	
	public Assignment(String varname, Expression expression) {
		super();
		this.varname = varname;
		this.expression = expression;
	}

	@Override
	public boolean step(Program program) throws ProgramException {
		this.context.setVariable(varname, expression.evaluate(program));
		return false;
	}
	
	@Override
	protected Statement[] getChildStatements() { return new Statement[] {expression}; }

}
