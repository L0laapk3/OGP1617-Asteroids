package asteroids.model.program.expression;

import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.ExpressionContainer;

public class Multiplication extends ExpressionContainer<Double> implements IExpression<Double> {
	
	
	public Multiplication(IExpression<? extends Double> expression1, IExpression<? extends Double> expression2) throws ProgramException {
		super(expression1, expression2);
	}
	
	@Override
	public Double evaluate(Program program) throws ProgramException {
		if (getResult(0) == null || getResult(1) == null)
			throw new NullComputationException();
		return getResult(0) * getResult(1);
	}
}
