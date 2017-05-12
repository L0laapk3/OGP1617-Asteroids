package asteroids.model.program.expression;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.ProgramException;
import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.MinorPlanet;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.model.program.Program;
import asteroids.model.program.statement.Statement;

public class FindEntity extends Statement implements IExpression<Entity> {

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

	public FindEntity(Filter filter) throws ProgramException {
		super();
		this.filter = filter;
	}
	
	
	@Override
	public Entity evaluate(Program program) throws ProgramException {
		try {
			Ship ship = program.getShip();
			switch(filter) {
				case ANY:		return ship.getNearestEntity();
				case ASTEROID:	return ship.getNearestEntity(Asteroid.class);
				case BULLET:	return ship.getNearestEntity(bullet -> ((Bullet)bullet).getMotherShip() == ship, Bullet.class);
				case PLANET:	return ship.getNearestEntity(Ship.class, MinorPlanet.class);
				case PLANETOID:	return ship.getNearestEntity(Planetoid.class);
				case SELF:		return ship;
				case SHIP:		return ship.getNearestEntity(Ship.class);
				default:		return null; //case NULL:
			}
		} catch (InvalidShipException e) {
			throw new ProgramException(e);
		}
	}

}
