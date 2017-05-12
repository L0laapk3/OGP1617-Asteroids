package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;

public class ConstantNumber extends Constant<Double> implements INumeric {

	public ConstantNumber(Double value) throws ProgramException {
		super(value);
	}
}
