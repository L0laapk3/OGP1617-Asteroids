package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;

public class ConstantNull extends Constant<Object> {

	public ConstantNull() throws ProgramException {
		super(null);
	}
}
