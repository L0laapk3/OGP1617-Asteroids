package asteroids.model.program.expression;

import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.ExpressionContainer;

public class Invert extends ExpressionContainer<Boolean> implements IExpression<Boolean> {
	
	public Invert(IExpression<? extends Boolean> expression) throws ProgramException {
		super(expression);
	}

	@Override
	public Boolean evaluate(Program program) throws ProgramException {
		if (getResult(0) == null)
			throw new NullComputationException();
		return !getResult(0);
	}
}
