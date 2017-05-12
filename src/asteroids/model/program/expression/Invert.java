package asteroids.model.program.expression;

import asteroids.exceptions.NotInvertableException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Invert extends Condition {

	private final Expression expression;
	
	public <T extends Expression & ICondition> Invert(T expression) throws ProgramException {
		super(expression);
		this.expression = expression;
	}

	@Override
	public Boolean evaluate(Program program) throws ProgramException {
		Object result = expression.evaluate(program);
		
		if (result instanceof Boolean)
			return !(boolean)result;
		else
			throw new NotInvertableException("Cannot get negative of expression because expression is not a number.");
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		return expression.step(program);
	}
	
	@Override
	public double getRequiredTime() {
		return expression.getRequiredTime();
	}
}
