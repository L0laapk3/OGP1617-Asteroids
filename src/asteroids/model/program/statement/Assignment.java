package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.ContextAwareExpression;
import asteroids.model.program.expression.Expression;

public class Assignment extends ContextAwareExpression {

	public final String varname;
	public final Expression expression;
	
	public Assignment(String varname, Expression expression) {
		super(expression);
		this.varname = varname;
		this.expression = expression;
	}

	@Override
	public boolean step(Program program) throws ProgramException {
		this.variableContext.setVariable(varname, expression.evaluate(program));
		return false;
	}
	
	@Override
	protected Statement[] getChildStatements() { return new Statement[] {expression}; }

	@Override
	public Object evaluate(Program program) throws ProgramException {
		throw new ProgramException("Internal program exception: evaluate was called on a statement.");
	}

}
