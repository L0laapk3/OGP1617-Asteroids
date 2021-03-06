package asteroids.model.program.statement;

import asteroids.exceptions.*;
import asteroids.model.program.Program;

public class Fire extends Statement implements IAction {
	
	public Fire() throws ProgramException {
		super();
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		try {
			program.getShip().shootBullet();
		} catch (NoWorldException | MisMatchWorldsException | BulletNotLoadedException | InvalidShipException e) {
			throw new ProgramException(e);
		}
		return false;
	}

}
