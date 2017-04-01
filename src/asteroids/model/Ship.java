package asteroids.model;

import asteroids.exceptions.*;



/**
 * A class to define ships.
 * 
 * @invar Both the x and y coordinate of the position of the ship must not be NaN or infinity.
 *      | isValidPosition(getPosition())
 * @invar The initial orientation of the ship must be between 0 and 2*PI.
 *      | isValidOrientation(getOrientation())
 * @invar The radius must be bigger than MIN_RADIUS.
 * 		| isValidRadius(getRadius())
 * 
 * @version  1.0
 * @author   Kris Keersmaekers
 * @author   Rik Pauwels
 */

public class Ship extends Entity {

	private static final double MIN_RADIUS = 10;


	
	
	@Deprecated
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)  throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		this(x, y, xVelocity, yVelocity, radius, orientation, 1);
	}

	/**
	 * Create a new ship with the given position, velocity, radius and
	 * orientation (in radians).
	 * 
	 * @param  x
	 * 	       The x coordinate where the new ship has to be created.
	 * @param  y
	 *         The y coordinate where the new ship has to be created.
	 * @param  xVelocity
	 * 		   The initial speed in the x direction of the new ship.
	 * @param  yVelocity
	 * 		   The initial speed in the y direction of the new ship.
	 * @param  orientation
	 * 		   The direction that the new ship is initially pointed at.
	 * @param  radius
	 * 		   The size of the newly created ship.
	 * @effect This new ship is initialized as a new Entity with given position, velocity, radius, orientation and mass
	 * 		 | super(x,y,xVelocity,yVelocity,radius,orientation,mass)
	 * @post   The radius of this new ship is equal to the given radius.
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass) throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, orientation, mass);

		//TODO: VRAAG: argument checking before super, gaat niet..

		//DEFENSIVE
		if (radius < MIN_RADIUS)
			throw new InvalidRadiusException();

	}

	public void kill() {
		// separate fun
		terminate();
	}	
	
	public void addBullet() {
		// TODO add one bullet, when bullet returns to own ship
		
	}

	public static void collideEachother(Ship firstShip, Ship secondShip) {
		// TODO shit for bounce
		
	}
	
	public static void collideEachother(Ship collisionFirstEntity, Ship collisionSecondEntity) {
		// TODO Auto-generated method stub
		
	}
	
	//-------------Thrust functions
	private double thrustforce=1.1*(Math.pow(10, 21));
	
	public void thrustOn(){
		this.acceleration=thrustforce/this.mass;
	}
	
	public void thrustOff(){
		this.acceleration;
	}

	public void triggerHit() {
		this.terminate();
	}

	public void triggerScoreOn(Ship ship) {
		// TODO add score, this does nothing for now since there is no score system
		
	}
}