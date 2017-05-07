package asteroids.program;


public class ThrustOn extends Action {
	
	@Override
	protected boolean step(Program program) {
		program.ship.setThruster(true);
		return false;
	}

}
