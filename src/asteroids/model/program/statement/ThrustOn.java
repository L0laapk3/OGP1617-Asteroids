package asteroids.model.program.statement;

import asteroids.model.program.Program;

public class ThrustOn extends Action {
	
	@Override
	public boolean step(Program program) {
		program.ship.setThruster(true);
		return false;
	}

}
