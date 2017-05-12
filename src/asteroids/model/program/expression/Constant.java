package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Constant<T extends Object> extends Expression {

	public final T value;
	
	public Constant(T value) throws ProgramException {
		super();
		this.value = value;
	}
	@Override
	public T evaluate(Program program) {
		return value;
	}

}
