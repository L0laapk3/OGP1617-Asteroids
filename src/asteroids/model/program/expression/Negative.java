package asteroids.model.program.expression;

import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.ExpressionContainer;

public class Negative extends ExpressionContainer<Double> implements IExpression<Double> {
	
	public Negative(IExpression<? extends Double> expression) throws ProgramException {
		super(expression);
	}

	@Override
	public Double evaluate(Program program) throws ProgramException {
		if (getResult(0) == null)
			throw new NullComputationException();
		return -getResult(0);
	}
}
