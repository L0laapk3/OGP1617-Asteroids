package asteroids.model.program.statement;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.Expression;
import asteroids.model.program.expression.ICondition;

public class SetThruster extends Action {
	
	public final Expression state;

	public <T extends Expression & ICondition> SetThruster(T state) throws ProgramException {
		super(state);
		this.state = state;
	}
	
	private boolean done = false;
	
	@Override
	public boolean step(Program program) throws ProgramException {
		if (!done) {
			if (!state.step(program))
				done = true;
			return true;
		}
		try {
			program.getShip().setThruster(((Expression & ICondition)state).evaluate(program));
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
		return state.getRequiredTime();
	}

}
