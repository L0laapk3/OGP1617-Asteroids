package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Equality extends MultiContainerExpression<Object> implements IExpression<Boolean> {
	
	
	public <T extends Object> Equality(IExpression<? extends T> expression1, IExpression<? extends T> expression2) throws ProgramException {
		super(expression1, expression2);
	}
	
	@Override
	public Boolean evaluate(Program program) throws ProgramException {
		if (getResult(0) == null && getResult(1) == null)
			return true;
		if (getResult(0) == null || getResult(1) == null)
			return false;
		return getResult(0).equals(getResult(1));
	}
}
