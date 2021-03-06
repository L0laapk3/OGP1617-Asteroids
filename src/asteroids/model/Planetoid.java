package asteroids.model;

import asteroids.exceptions.*;
import asteroids.util.OGUtil;
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
	 * @param  totalTraveledDistance
	 * 		   The total distance travelled that the planetoid has to be initialised with. Defaults to 0.
	 * @throws InvalidPositionException 
	 * @throws InvalidRadiusException 
	 * @throws IllegalArgumentException 
	 * @effect This new Planetoid is initialized as a new MinorPlanet with given position, velocity and radius.
	 * 		 | super(x, y, xVelocity, yVelocity, radius, 0.917E12)
	 */
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException
		{ this(x, y, xVelocity, yVelocity, radius, 0); };
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalTraveledDistance)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, (radius - totalTraveledDistance * 0.000001) , RHO_PLANETOID);
		
		OGUtil.throwErrorIfInvalidNumbers(totalTraveledDistance);
		this.setTotalTraveledDistance(totalTraveledDistance);
	}
	
	/**
	 * Variable that holds the total traveled distance of the planetoid.
	 */
	private double totalTraveledDistance = 0;
	
	/**
	 * Function that returns the total traveled distance of the planetoid.
	 * 
	 * @return the total traveled distance of the planetoid
	 */
	public double getTotalTraveledDistance() {
		return this.totalTraveledDistance;
	}
	
	/**
	 * Function that sets the total traveled distance of the planetoid to the given distance;
	 * 
	 * @param totalTraveledDistance
	 * 		  The new total traveled distance of the planetoid.
	 * @post  The new totalTraveledDistance of the planetoid is equal to the given value.
	 */
	void setTotalTraveledDistance(double totalTraveledDistance) {
		this.totalTraveledDistance = totalTraveledDistance;
	}
	
	/**
	 * Function that adds a given distance to the original totalTraveledDistance of the planetoid.
	 * 
	 * @param distance
	 * 		  The distance that has to be added to the original totalTraveledDistance of the planetoid.
	 * @effect The totalTraveldDistance will be added by the given amount.
	 */
	void addTraveledDistance(double distance) {
		this.setTotalTraveledDistance(this.getTotalTraveledDistance() + distance);
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

		this.addTraveledDistance(dt*this.getVelocity());
		
		double newRadius = this.getRadius() - dt * this.getVelocity() * 0.000001;
		this.setRadius(newRadius);
	}
	
}
