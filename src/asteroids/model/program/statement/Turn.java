package asteroids.model.program.statement;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.*;

public class Turn extends Action {

	public final Expression angleExpression;
	
	public <T extends Expression & INumeric> Turn(T angleExpression) throws ProgramException {
		super(angleExpression);
		this.angleExpression = angleExpression;
	}
	
	private boolean done = false;
	
	@Override
	public boolean step(Program program) throws ProgramException {
		if (!done) {
			if (!angleExpression.step(program))
				done = true;
			return true;
		}
		try {
			program.getShip().turn(((Expression & INumeric)angleExpression).evaluate(program));
		} catch (InvalidShipException e) {
			throw new ProgramException(e);
		}
		return false;
	}
	
	@Override
	public void reset(Program program) {
		super.reset(program);
		done = false;
	}
	
	@Override
	public double getRequiredTime() {
		if (done)
			return super.getRequiredTime();
		return angleExpression.getRequiredTime();
	}

}
