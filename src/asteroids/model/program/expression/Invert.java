package asteroids.model.program.expression;

import asteroids.exceptions.NotInvertableException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Invert extends Expression {

	private final Expression expression;
	
	public Invert(Expression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public Object evaluate(Program program) throws ProgramException {
		Object result = expression.evaluate(program);
		
		if (result instanceof Boolean)
			return !(boolean)result;
		else
			throw new NotInvertableException("Cannot get negative of expression because expression is not a number.");
	}

}
