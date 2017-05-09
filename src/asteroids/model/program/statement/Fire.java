package asteroids.model.program.statement;

import asteroids.exceptions.*;
import asteroids.model.program.Program;

public class Fire extends Action {

	public Fire() {
		super();
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		try {
			program.ship.shootBullet();
		} catch (NoWorldException | MisMatchWorldsException | BulletNotLoadedException | InvalidParentShipException e) {
			throw new ProgramException(e);
		}
		return false;
	}

}