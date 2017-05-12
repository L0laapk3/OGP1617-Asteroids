package asteroids.model.program.expression;

import asteroids.exceptions.NotDoubleException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Sqrt extends Numeric {
	
	private final Expression expression;
	
	public <T extends Expression> Sqrt(T expression) throws ProgramException {
		super(expression);
		this.expression = (Expression) expression;	
	}
	
	@Override
	public Double evaluate(Program program) throws ProgramException {
		Object result1 = expression.evaluate(program);
		
		if (result1 instanceof Double) {
			return (double)result1;
		} else {
			throw new NotDoubleException("The variable is not a double.");
		}
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
