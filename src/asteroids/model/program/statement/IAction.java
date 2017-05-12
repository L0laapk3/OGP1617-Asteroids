package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public interface IAction extends IStatement {


	@Override
	public default boolean step(Program program) throws ProgramException {
		return false;
	}
	
	@Override
	public default double getRequiredTime() {
		return 0.2;
	}
}
