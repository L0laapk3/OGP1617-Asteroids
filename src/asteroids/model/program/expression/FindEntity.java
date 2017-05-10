package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.*;
import asteroids.model.program.Program;

public class FindEntity extends EntityExpression {

	public enum Filter {
		NULL,
		SELF,
		SHIP,
		ASTEROID,
		PLANETOID,
		BULLET,
		PLANET,
		ANY
	}
	
	public final Filter filter;

	public FindEntity(Filter filter) {
		this.filter = filter;
	}
	
	
	@Override
	public Entity evaluate(Program program) throws ProgramException {

		switch(filter) {
		case ANY:		return program.ship.getNearestEntity();
		case ASTEROID:	return program.ship.getNearestEntity(Asteroid.class);
		case BULLET:	return program.ship.getNearestEntity(bullet -> ((Bullet)bullet).getMotherShip() == program.ship, Bullet.class);
		case PLANET:	return program.ship.getNearestEntity(Ship.class, MinorPlanet.class);
		case PLANETOID:	return program.ship.getNearestEntity(Planetoid.class);
		case SELF:		return program.ship;
		case SHIP:		return program.ship.getNearestEntity(Ship.class);
		default:		return null; //case NULL:
		}
	}

}
