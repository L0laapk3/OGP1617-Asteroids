package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;

public abstract class Action extends Statement {
	
	protected Action(Statement... statements) throws ProgramException {
		super(statements);
	}

	@Override
	public double getRequiredTime() {
		return 0.2;
	}
}
