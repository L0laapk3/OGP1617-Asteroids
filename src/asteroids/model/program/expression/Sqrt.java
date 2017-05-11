package asteroids.model.program.expression;

import asteroids.exceptions.NotDoubleException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Sqrt extends Numeric {
	
	private final Expression expression1;
	
	public <T extends Expression> Sqrt(T expression1) {
		super();
		this.expression1 = (Expression) expression1;	
	}
	
	@Override
	public Double evaluate(Program program) throws ProgramException {
		Object result1 = expression1.evaluate(program);
		
		if (result1 instanceof Double) {
			return (double)result1;
		} else {
			throw new NotDoubleException("The variable is not a double.");
		}
	
	}
}
