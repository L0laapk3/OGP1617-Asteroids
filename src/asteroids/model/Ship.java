package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import asteroids.exceptions.*;
import asteroids.util.Vector2;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;



/**
 * A class to define ships.
 * 
 * @effect   Entity
 * @invar    The radius must be bigger than MIN_RADIUS for Ship.
 * 		   | isValidRadius(getRadius())
 * 
 * @version  2.0
 * @author   Kris Keersmaekers
 * @author   Rik Pauwels
 */

//TODO: COMMENTS VERDER AFWERKEN, SHIT OVER BULLETS OOK NOG

public class Ship extends Entity {

	//TODO: EACH SHIP SHALL PROVIDE METHODS TO INSPECT THE SHIPS ACCELERATION ?
	
	private static final double MIN_RADIUS_SHIP = 10;
	
	
	private List<Bullet> loadedBullets = new ArrayList<Bullet>();
	
	
	public List<Bullet> getLoadedBullets() {
		return new ArrayList<Bullet>(loadedBullets);
	}
	
	void loadBullet(Bullet bullet) {
		loadedBullets.add(bullet);
	}

	void unloadBullet(Bullet bullet) {
		loadedBullets.remove(bullet);
	}
	
	
	/**
	 * Checks if there is a bullet available in the ship's magazine.
	 * @return true if and only if there is at least 1 bullet loaded in the ship.
	 */
	public boolean hasBullet() {
		return loadedBullets.size() > 0;
	}
	
	/**
	 * Attempts to shoot a bullet from the ship.
	 * @post   Shoots a bullet, only if there is one available.
	 * @return false if there is no bullet loaded in the ship.
	 * @return true if it succesfully shot a bullet from the ship.
	 */
	public boolean shootBullet() {
		if (!hasBullet())
			return false;
		shootBullet(loadedBullets.get(0));
		return true;
	}
	/**
	 * Shoots the given bullet from the ship.
	 * @param bullet
	 * 	      The bullet to shoot
	 * @note  defensive
	 */
	public void shootBullet(Bullet bullet) {
		//throw error if not in shit
		//TODO: shootbullet hier nog alles schrijven
		updateLoadMass();
	}
	
	
	

	/**
	 * The total mass of the load.
	 */
	private double loadMass = 0;
	
	/**
	 * Gets the total mass of the loaded bullets.
	 */
	public double getLoadMass() {
		return this.loadMass;
	}

	/**
	 * A function to update the total mass of the loaded bullets.
	 * @post Computes the total mass of the loaded bullets.
	 */
	void updateLoadMass() {
		loadMass = 0;
		for (Bullet bullet : loadedBullets)
			loadMass += bullet.getMass();
	}
	


	/**
	 * Gets the total mass of the ship.
	 */
	@Raw
	@Override
	public double getMass() {
		return super.getMass() + this.getLoadMass();
	}
	
	
	
	
	
	
	
	@Deprecated
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)  throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		this(x, y, xVelocity, yVelocity, radius, orientation, 1.42*Math.pow(10, 12));
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
	 * @effect This new ship is initialized as a new Entity with given position, velocity, radius and orientation.
	 * 		 | super(x,y,xVelocity,yVelocity,radius,orientation)
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
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double rho) throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, orientation);
		this.setMinRadius(MIN_RADIUS_SHIP);
		
		//TOTAL
		if (isValidRho(rho)) {
			setRho(rho);
		} else {
			setRho(1.42*Math.pow(10, 12));
		}
		
		
		
		//DEFENSIVE
		if (radius < MIN_RADIUS_SHIP)
			throw new InvalidRadiusException();
		
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
	public boolean getThrusterstate() {
		return this.thrusterState;
	}
	
	/**
	 * Variable that holds the thrustforce from the ship
	 */
	private static final double thrustforce = 1.1*(Math.pow(10, 21));
	
	
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
		//add score, this does nothing for now since there is no score system
		
	}
}