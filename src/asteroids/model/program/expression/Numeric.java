package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public abstract class Numeric extends Expression implements INumeric {

	protected Numeric() {
		super();
	}

	@Override
	public abstract Double evaluate(Program program) throws ProgramException;

}
