package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.*;

public class Turn extends Action {

	public final Expression angle;
	
	public <T extends Expression & INumeric> Turn(Expression angle) {
		super();
		this.angle = angle;
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		program.ship.turn(((Numeric)angle).evaluate(program));
		return false;
	}

}
