package asteroids.model.program.expression;

import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.ExpressionContainer;

public class Sqrt extends ExpressionContainer<Double> implements IExpression<Double> {
	
	public Sqrt(IExpression<? extends Double> expression) throws ProgramException {
		super(expression);
	}
	
	
	public Double evaluate(Program program) throws ProgramException {
		if (getResult(0) == null)
			throw new NullComputationException();
		return Math.sqrt(getResult(0));
	}
}
