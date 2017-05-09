package asteroids.model.program.expression;

import asteroids.exceptions.NotInvertableException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Negative extends Number {

	private final Expression expression;
	
	public <T extends Expression & INumber> Negative(T expression) {
		super();
		this.expression = expression;
	}

	@Override
	public Double evaluate(Program program) throws ProgramException {
		Object result = expression.evaluate(program);
		
		if (result instanceof Double)
			return -(Double)result;
		else
			throw new NotInvertableException("Cannot get negative of expression because expression is not a number.");
	}

}
