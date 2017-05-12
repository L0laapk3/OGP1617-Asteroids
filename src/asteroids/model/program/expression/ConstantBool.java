package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;

public class ConstantBool extends Constant<Boolean> implements ICondition {

	public ConstantBool(Boolean value) throws ProgramException {
		super(value);
	}
}
