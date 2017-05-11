package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.Expression;
import asteroids.model.program.expression.FunctionContextAwareExpression;

public class Return extends FunctionContextAwareExpression {

	public final Expression expression;
	
	public Return(Expression expression) {
		super();
		this.expression = expression;
	}

	public boolean step(Program program) throws ProgramException {
		this.functionContext.doReturn(expression.evaluate(program));
		return false;
	}

	@Override
	public Object evaluate(Program program) throws ProgramException {
		throw new ProgramException("Internal program exception: evaluate was called on a statement.");
	}
}
