package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public interface IContainerStatement extends IStatement {
	
	public abstract boolean selfStep(Program program) throws ProgramException;
	
	public default double selfGetRequiredTime() throws ProgramException { return 0; }
	
}
