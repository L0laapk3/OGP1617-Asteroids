package asteroids.model.program.expression;

import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.ExpressionContainer;
import asteroids.util.OGUtil;

public class LessThan extends ExpressionContainer<Double> implements IExpression<Boolean> {
	
	
	public LessThan(IExpression<? extends Double> expression1, IExpression<? extends Double> expression2) throws ProgramException {
		super(expression1, expression2);
	}
	
	@Override
	public Boolean evaluate(Program program) throws ProgramException {
		OGUtil.println(getResult(0) + " < " + getResult(1));
		if (getResult(0) == null || getResult(1) == null)
			throw new NullComputationException();
		return getResult(0) < getResult(1);
	}
}
