package asteroids.model.program.statement;

import asteroids.model.program.Program;

public class ThrustOff extends Action {
	
	@Override
	public boolean step(Program program) {
		program.ship.setThruster(false);
		return false;
	}

}
