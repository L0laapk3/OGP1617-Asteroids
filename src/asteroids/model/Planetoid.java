package asteroids.model;

import asteroids.exceptions.*;
import asteroids.util.Vector2;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class Planetoid extends MinorPlanet {
	
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
	 * 		 | super(x,y,xVelocity,yVelocity,radius,MASS_DENSITY_PLANETOID)
	 */
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, MASS_DENSITY_PLANETOID);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Variable holding the mass density of a planetoid.
	 */
	private final static double MASS_DENSITY_PLANETOID = 0.917E12;
	
	/**
	 * Function that will update the mass of a planetoid after its radius is changed.
	 */
	public void updateMass() {
		this.baseMass = calculateBassMass(MASS_DENSITY_PLANETOID, this.getRadius());
	}

	/**
	 * Sets the radius of this entity to the given radius.
	 * 
	 * @param radius
	 * 		  The new radius of the entity.
	 */
	@Raw
	@Basic
	public void setRadius(double radius) {
		this.radius=radius;
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
	
}
