package asteroids.model;

import asteroids.exceptions.*;
import asteroids.util.Vector2;
import be.kuleuven.cs.som.annotate.Raw;

public class Planetoid extends MinorPlanet {

	private static final double RHO_PLANETOID = 0.917E12;
	
	/**
	 * Function to make a new Planetoid.
	 * @param  x
	 * 	       The x coordinate where the new Planetoid has to be created.
	 * @param  y
	 *         The y coordinate where the new Planetoid has to be created.
	 * @param  xVelocity
	 * 		   The initial speed in the x direction of the new Planetoid.
	 * @param  yVelocity
	 * 		   The initial speed in the y direction of the new Planetoid.
	 * @param  radius
	 * 		   The size of the newly created Planetoid.
	 * @effect This new Planetoid is initialized as a new MinorPlanet with given position, velocity and radius.
	 * 		 | super(x, y, xVelocity, yVelocity, radius, 0.917E12)
	 */
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, RHO_PLANETOID);
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
			Vector2 firstVelocity = Vector2.fromPolar(direction, this.getVelocityVector().pythagoras() * 1.5);
			Vector2 SecondVelocity = Vector2.fromPolar(direction + Math.PI, this.getVelocityVector().pythagoras() * 1.5);
			
			//TODO: RADIUS, orientation, die hebbe toch geen orientation, in constructor van minorplanets aanpassen (opgelost denk ik)
			
			try {
				Asteroid first = new Asteroid(firstPosition.x, firstPosition.y, firstVelocity.x, firstVelocity.y, this.getRadius() / 2);
				Asteroid second = new Asteroid(secondPosition.x, secondPosition.y, SecondVelocity.x, SecondVelocity.y, this.getRadius() / 2);
				super.die();
				world.addEntity(first);
				world.addEntity(second);
			} catch (IllegalArgumentException | InvalidRadiusException | InvalidPositionException | EntitiesOverlapException e) {
				//these should never happen and are not a result of input errors in public functions, but rather a result of internal errors in the code
				throw new RuntimeException(e);
			}
		} else
			super.die();
	}
	
	
	

	/**
	 * Update <code>entity</code>'s position, assuming it moves <code>dt</code>
	 * seconds at its current velocity.
	 * 
	 * @param  	dt
	 * 		   	The amount of time that the entity should move forward.
	 * @effect  Entity.move()
	 */
	@Override
	@Raw
	void move(double dt) {

		super.move(dt);
		
		double newRadius = this.getRadius() - dt * this.getVelocity() * 0.000001;
		this.setRadius(newRadius);
		
		
	}
	
}
