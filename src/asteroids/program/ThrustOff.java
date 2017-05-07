package asteroids.program;


public class ThrustOff extends Action {
	
	@Override
	protected boolean step(Program program) {
		program.ship.setThruster(false);
		return false;
	}

}
