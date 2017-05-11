package asteroids.model.program.statement;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class SetThruster extends Action {
	
	public final boolean state;

	public SetThruster(boolean state) {
		this.state = state;
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		try {
			program.getShip().setThruster(state);
		} catch (InvalidShipException e) {
			throw new ProgramException(e);
		}
		return false;
	}

}
