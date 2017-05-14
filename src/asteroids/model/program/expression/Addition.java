package asteroids.model.program.expression;

import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Addition extends MultiContainerExpression<Double> implements IExpression<Double> {
	
	
	public Addition(IExpression<? extends Double> expression1, IExpression<? extends Double> expression2) throws ProgramException {
		super(expression1, expression2);
	}
	
	@Override
	public Double evaluate(Program program) throws ProgramException {
		if (getResult(0) == null || getResult(1) == null)
			throw new NullComputationException();
		return getResult(0) + getResult(1);
	}
}