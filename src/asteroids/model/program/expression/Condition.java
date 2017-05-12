package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.Statement;

public abstract class Condition extends Expression implements ICondition {
	
	protected Condition(Statement... statements) throws ProgramException {
		super(statements);
	}

	@Override
	public abstract Boolean evaluate(Program program) throws ProgramException;

}
