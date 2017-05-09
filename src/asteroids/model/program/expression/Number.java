package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public abstract class Number extends Expression implements INumber {

	protected Number() {
		super();
	}

	@Override
	public abstract Double evaluate(Program program) throws ProgramException;

}
