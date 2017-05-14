package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public interface IAction extends IStatement, IContainerStatement {

	@Override public default boolean step(Program program) throws ProgramException { return false; }
	@Override public default boolean selfStep(Program program) throws ProgramException { return false; }
	
	@Override public default double getRequiredTime() throws ProgramException { return 0.2; }
	@Override public default double selfGetRequiredTime() throws ProgramException { return 0.2; }
}
