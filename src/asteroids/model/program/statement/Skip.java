package asteroids.model.program.statement;

import asteroids.model.program.Program;

public class Skip extends Action {
	
	@Override
	public boolean step(Program program) {
		return false;
	}

}
