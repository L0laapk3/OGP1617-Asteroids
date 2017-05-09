package asteroids.model.program.statement;

import asteroids.model.program.Program;

public class SetThruster extends Action {
	
	public final boolean state;

	public SetThruster(boolean state) {
		this.state = state;
	}
	
	@Override
	public boolean step(Program program) {
		program.ship.setThruster(state);
		return false;
	}

}
