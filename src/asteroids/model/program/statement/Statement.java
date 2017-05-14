package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;

public abstract class Statement implements IStatement {
	
	protected Statement() throws ProgramException { }
	
	public Statement clone() {
		try {
			return (Statement)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(e);
		}
	}
}
