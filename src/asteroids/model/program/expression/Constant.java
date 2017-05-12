package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.Statement;

public class Constant<T extends Object> extends Statement implements IExpression<T> {

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
