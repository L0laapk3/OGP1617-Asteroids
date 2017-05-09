package asteroids.model.program.statement;

import asteroids.model.program.Program;

public class Turn extends Action {

	public final double angle;
	
	public Turn(double angle) {
		super();
		this.angle = angle;
	}
	
	@Override
	public boolean step(Program program) {
		program.ship.turn(angle);
		return false;
	}

}
