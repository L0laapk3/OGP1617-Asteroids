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
 * @invar The mass of the ship will always be greater than 4/3*PI*radius^3*(mass density)
 * 		| this.isValidMass(mass)
 * 
 * @version  1.0
 * @author   Kris Keersmaekers
 * @author   Rik Pauwels
 */

public class Ship extends Entity {

	private static final double MIN_RADIUS = 10;


	
	
	@Deprecated
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)  throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		this(x, y, xVelocity, yVelocity, radius, orientation, 1.42*Math.pow(10,12),1);
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
	 * @param  rho
	 * 		   The mass density of the newly created ship
	 * @effect This new ship is initialized as a new Entity with given position, velocity, radius, orientation and mass
	 * 		 | super(x,y,xVelocity,yVelocity,radius,orientation,mass)
	 * @post   The radius of this new ship is equal to the given radius if the radius is valid otherwise this function throws an InvalidRadiusException.
	 * 		 | if (radius < MIN_RADIUS)
	 * 		 |    new.getRadius=radius
	 * 		 | else 
	 * 		 | 	  throw new InvalidRadiusException
	 * @post   The mass of this new ship is equal to the given mass if the mass is invalid it will be set to a default value of 1.
	 * 		 | if (isValidMass)
	 * 		 |    new.getMass = mass
	 * 		 | else
	 * 		 |    new.getMass = 1
	 * @post   The mass density of this new ship is equal to the given mass density if the mass density is invalid it will be set to a default value of 1.42*10^12.
	 * 		 | if (isValidMass)
	 * 		 |	  new.getRho=rho
	 * 		 | else
	 * 		 |	  new.getRho=1.42*10^12
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double rho, double mass) throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, orientation);
		
		//TOTAL
		if (isValidRho(rho)) {
			this.rho = rho;
		} else {
			this.rho = 1.42*Math.pow(10,12);
		}
		
		//TOTAL
		if (this.isValidMass(mass)) {
			this.mass = mass;
		} else {
			this.mass = 1;
		}
		
		//DEFENSIVE
		if (radius < MIN_RADIUS)
			throw new InvalidRadiusException();
		
	}
	
	//------------------RHO
	/**
	 * Variable holding the mass density of the ship
	 */
	private double rho;
	
	/**
	 * Set the mass density of the ship to the given mass density
	 * 
	 * @param 	rho
	 * 			The new mass density.
	 * @post 	The mass density of this ship is equal to the given rho.
	 * 		  | new.getRho() == rho
	 */
	@Raw
	public void setRho(double rho){
		if (isValidMass(rho)) {
			this.rho=rho;
		}
	}
	
	/**
	 * Check whether the mass density is valid for a ship.
	 * 
	 * @param  rho
	 * 		   The mass density to check.
	 * @return True if and only if the given rho is between 1.42*10^12 kg/km^3 and positive infinity.
	 *       | result == (mass >= 0) && (mass < Double.POSITIVE_INFINITY)
	 */
	@Raw
	public static boolean isValidRho(double rho){
		if (( rho >= 1.42*Math.pow(10, 12)) && (rho < Double.POSITIVE_INFINITY)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Return the mass density of the ship.
	 * 
	 * @return the mass density of the ship
	 */
	@Basic
	@Raw
	public double getRho() {
		return this.rho;
	}
	
	//---------------MASS
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
	 * 		  | new.getMass=mass
	 */
	@Raw
	public void setMass(double mass) {
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
	public boolean isValidMass(double mass) {
		if (mass >= 4/3*Math.PI*Math.pow(this.getRadius(), 3)*this.rho) {
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

	public static void collideEachother(Ship firstShip, Ship secondShip) {
		// TODO shit for bounce
		
	}
	
	//-------------Thrust functions
	/**
	 * Variable that holds whether the thruster is on or of
	 */
	private boolean thrusterState=false;
	
	/**
	 * function that returns the Thrusterstate of the ship
	 * @return 	thrusterstate
	 * 			Returns the thrusterstate
	 */
	@Basic
	@Raw
	public boolean getThrusterstate(){
		return this.thrusterState;
	}
	
	/**
	 * Variable that holds the thrustforce from the ship
	 */
	private double thrustforce=1.1*(Math.pow(10, 21));
	
	
	/**
	 * function that calculates the acceleration of the ship
	 * @effect this function calculates the acceleration of the ship and sets it using setAccelaration
	 * 		 | this.setAcceleration(this.getAcceleration()* thrustforce/this.getMass()));
			 | thrusterState = true;
	 */
	public void thrustOn() {
		this.setAcceleration(Vector2.multiply(this.getAcceleration(), thrustforce/this.getMass()));
		thrusterState = true;
	} 

	/**
	 * function that sets the acceleration of the ship to (0,0)
	 * @effect this function sets the acceleration to (0,0) using setAccelaration
	 * 		 | this.setAcceleration(new Vector2(0,0));
			 | thrusterState = false;
	 */
	public void thrustOff() {
		this.setAcceleration(new Vector2(0,0));
		thrusterState = false;
	}

	public void triggerHit() {
		this.terminate();
	}

	public void triggerScoreOn(Ship ship) {
		// TODO add score, this does nothing for now since there is no score system
		
	}
}