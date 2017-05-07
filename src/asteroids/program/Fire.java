package asteroids.program;

import asteroids.exceptions.*;

public class Fire extends Action {

	public Fire() {
		super();
	}
	
	@Override
	protected boolean step(Program program) throws ProgramErrorException {
		try {
			program.ship.shootBullet();
		} catch (NoWorldException | MisMatchWorldsException | BulletNotLoadedException | InvalidParentShipException e) {
			throw new ProgramErrorException(e);
		}
		return false;
	}

}
