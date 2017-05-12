package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.Expression;
import asteroids.model.program.expression.FunctionContextAwareExpression;
import asteroids.util.OGUtil;

public class Return extends FunctionContextAwareExpression {

	public final Expression expression;
	
	public Return(Expression expression) throws ProgramException {
		super(expression);
		this.expression = expression;
	}

	public boolean step(Program program) throws ProgramException {
		if (expression.step(program))
			return true;
		OGUtil.println("returningg " + expression.evaluate(program) + " for " + this.functionContext);
		this.functionContext.doReturn(expression.evaluate(program));
		return false;
	}

	@Override
	public Object evaluate(Program program) throws ProgramException {
		throw new ProgramException("Internal program exception: evaluate was called on a statement.");
	}
	
	@Override
	public double getRequiredTime() {
		return expression.getRequiredTime();
	}
}
