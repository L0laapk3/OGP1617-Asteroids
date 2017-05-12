package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Skip extends Action {
	
	public Skip() throws ProgramException {
		super();
	}
	
	@Override
	public boolean step(Program program) {
		return false;
	}

}
