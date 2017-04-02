package asteroids.model;

import asteroids.exceptions.*;
import asteroids.util.Vector2;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;



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
	 * @param  mass
	 * 		   The mass of the newly created ship.
	 * @effect This new ship is initialized as a new Entity with given position, velocity, radius, orientation and mass
	 * 		 | super(x,y,xVelocity,yVelocity,radius,orientation,mass)
	 * @post   The radius of this new ship is equal to the given radius.
	 * 		 | new.radius=radius
	 * @post   The mass of this new ship is equal to the given mass.
	 * 		 | new.mass=mass
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass) throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, orientation);
		
		//TOTAL
		if (isValidMass(mass)) {
			this.mass=mass;
		} else {
			this.mass=0;
		}
		
		
		//DEFENSIVE
		if (radius < MIN_RADIUS)
			throw new InvalidRadiusException();

	}
	/**
	 * Variable holding the mass of the ship
	 */
	private double mass;
	
	/**
	 * Set the mass of the ship to the given mass
	 * 
	 * @param 	mass
	 * 			The new mass.
	 * @post 	The mass of this ship is equal to the given mass.
	 * 		  | new.getMaxSpeed() == maxSpeed
	 */
	@Raw
	public void setMass(double mass){
		if (isValidMass(mass)) {
			this.mass=mass;
		}
	}
	
	/**
	 * Check whether the mass is valid for a ship.
	 * 
	 * @param  mass
	 * 		   The mass to check.
	 * @return True if and only if the given orientation is between 0 and positive infinity.
	 *       | result == (mass >= 0) && (mass < Double.POSITIVE_INFINITY)
	 */
	public boolean isValidMass(double mass){
		if ((mass >= 0) && (mass < Double.POSITIVE_INFINITY)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Return the mass of the ship.
	 * 
	 * @return the mass of the ship
	 */
	@Basic
	@Raw
	public double getMass() {
		return this.mass;
	}
	public void kill() {
		// separate fun
		terminate();
	}	
	
	public void addBullet() {
		// TODO add one bullet, when bullet returns to own ship
		
	}

	public static void collideWithSameType(Ship firstShip, Ship secondShip) {
		
		
		// "als het fout is is het de prof zijn schuld dan moet hij de opgave maar fatsoenlijk schrijven" - rik
		
		double sigma = firstShip.getRadius() + secondShip.getRadius();
		Vector2 J = Vector2.multiply(Vector2.subtract(firstShip.getPosition(), secondShip.getPosition()), 2 * firstShip.getMass() * secondShip.getMass() * Vector2.dot(
				Vector2.subtract(firstShip.getVelocity(), secondShip.getVelocity()), 
				Vector2.subtract(firstShip.getPosition(), secondShip.getPosition())
			) / (sigma*sigma * (firstShip.getMass() + secondShip.getMass())));
		
		firstShip.setVelocity(Vector2.add(firstShip.getVelocity(), Vector2.divide(J, firstShip.getMass())));
		secondShip.setVelocity(Vector2.add(secondShip.getVelocity(), Vector2.divide(J, secondShip.getMass())));
	}
	
	//-------------Thrust functions
	private double thrustforce=1.1*(Math.pow(10, 21));
	
	public void thrustOn() {
		this.setAcceleration(Vector2.multiply(this.getAcceleration(), thrustforce/this.getMass()));
	} 
	
	public void thrustOff() {
		this.setAcceleration(new Vector2(0,0));
	}

	/**
	 * This function defines what will happen when the ship gets hit by a bullet: The ship gets terminated.
	 * @post Terminate the ship.
	 *     | this.terminate();
	 */
	public void triggerHit() {
		this.terminate();
	}

	/**
	 * This function defines the behaviour that happens when the ship gets a kill on another ship.
	 * @post  Nothing will happen. (for now)
	 * @param ship
	 * 		| The ship that was killed.
	 */
	public void triggerScoreOn(Ship ship) {
		// TODO add score, this does nothing for now since there is no score system
		
	}
}