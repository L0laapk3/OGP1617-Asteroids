package asteroids.model.program.statement;

import asteroids.exceptions.*;
import asteroids.model.program.Program;

public class EmptyStatement extends Statement implements IAction {
	
	public EmptyStatement() throws ProgramException {
		super();
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		return false;
	}

}
