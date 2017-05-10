package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.Statement;

public abstract class Numeric extends Expression implements INumeric {

	public Numeric(Statement... statements) {
		super(statements);
	}

	@Override
	public abstract Double evaluate(Program program) throws ProgramException;

}
