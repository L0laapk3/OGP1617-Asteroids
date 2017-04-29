package asteroids.model;

import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;
import asteroids.util.Vector2;

public class Planetoid extends MinorPlanet {

	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, orientation, mass);
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * Function that handles when the entity dies.
	 * 
	 * @effect Entity is terminated.
	 */
	@Override
	void die() {
		World world = this.getWorld();
		if ((world != null) && (this.getRadius() >= 30)) {
			double direction = Math.random() * 2 * Math.PI;
			Vector2 firstPosition = Vector2.add(this.getPosition(), Vector2.fromPolar(direction, this.getRadius() / 2));
			Vector2 secondPosition = Vector2.add(this.getPosition(), Vector2.fromPolar(direction + Math.PI, this.getRadius() / 2));
			Vector2 firstVelocity = Vector2.fromPolar(direction, this.getVelocity().pythagoras() * 1.5);
			Vector2 SecondVelocity = Vector2.fromPolar(direction + Math.PI, this.getVelocity().pythagoras() * 1.5);
			
			//TODO: RADIUS, orientation, die hebbe toch geen orientation, in constructor van minorplanets aanpassen
			
			Asteroid first = new Asteroid(firstPosition.x, firstPosition.y, firstVelocity.x, firstVeloticy.y, this.getRadius() / 2, 0, ); //TODO: AFWERKEN
			Asteroid second = new Asteroid();
			super.die();
			world.addEntity(first);
			world.addEntity(second);
		} else
			super.die();
	}
}
