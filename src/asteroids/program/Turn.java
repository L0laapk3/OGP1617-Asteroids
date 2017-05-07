package asteroids.program;

public class Turn extends Action {

	public final double angle;
	
	public Turn(double angle) {
		super();
		this.angle = angle;
	}
	
	@Override
	protected boolean step(Program program) {
		program.ship.turn(angle);
		return false;
	}

}
