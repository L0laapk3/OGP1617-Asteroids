package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
import asteroids.exceptions.*;


/**
 * A class to define bullets.
 * 
 * @effect   Entity
 * @invar    The radius must be bigger than MIN_RADIUS for Bullet.
 * 		   | isValidRadius(getRadius())
 * 
 * @version  1.0
 * @author   Kris Keersmaekers
 * @author   Rik Pauwels
 */

public class Bullet extends Entity {
	
	/**
	 * Function to make a new bullet.
	 * @param  x
	 * 	       The x coordinate where the new bullet has to be created.
	 * @param  y
	 *         The y coordinate where the new bullet has to be created.
	 * @param  xVelocity
	 * 		   The initial speed in the x direction of the new bullet.
	 * @param  yVelocity
	 * 		   The initial speed in the y direction of the new bullet.
	 * @param  radius
	 * 		   The size of the newly created bullet.
	 * @param  mass
	 * 		   The mass of the newly created bullet.
	 * @param  motherShip
	 * 		   The mothership of the newly created bullet
	 * @effect This new bullet is initialized as a new Entity with given position, velocity, radius and 0 as his orientation.
	 * 		 | super(x,y,xVelocity,yVelocity,radius,0)
	 * @post   The minimum radius of the bullet is set to MIN_RADIUS_BULLET.
	 * @post   The mothership of this bullet will be set to the given mothership.
	 * @post   The mass density from this bullet is set to 7.8*10^12 kg/km^3
	 */
	
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius, Ship motherShip) throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, 0);
		this.setMinRadius(MIN_RADIUS_BULLET);		

		setRho(7.8 * Math.pow(10,  12)); //constant for this class
		
		this.motherShip = motherShip;
		
		if (motherShip != null){
			motherShip.loadBullet(this);
		}
	}

	/**
	 * Constant holding the minimum radius from a bullet.
	 */
	private static final double MIN_RADIUS_BULLET = 1;
	
	/**
	 * Constant holding the max amount of bounces before a bullet dissapears.
	 */
	private static final double MAX_BOUNCES = 3;
	
	/**
	 * Variable holding the amount of bounces after being shot.
	 */
	private int bounces = 0;
	
	/**
	 * Gets the number of bounces after the bullet was shot.
	 */
	@Raw
	@Basic
	public int getBounces() {
		return this.bounces;
	}
	
	/**
	 * Resets the amount of bounces.
	 * @post getBounces() == 0
	 */
	@Raw
	@Basic
	void resetBounces() {
		this.bounces = 0;
	}
	
	/**
	 * Adds one bounce to the bounce counter.
	 * @post getBounces()++
	 */
	@Raw
	@Basic
	void addBounce() {
		this.bounces++;
	}
	
	/**
	 * Variable holding the parent from the bullet.
	 */
	private Ship motherShip;
	
	/**
	 * Returns the ship that originally shot this bullet.
	 * @return The mothership of the bullet.
	 */
	@Basic
	@Raw
	public Ship getMotherShip() {
		return this.motherShip;
	}
	
	/**
	 * Sets the ship that this bullet belongs to.
	 */
	@Basic
	@Raw
	void setMotherShip(Ship ship) {
		this.motherShip = ship;
	}
	
	/**
	 * Variable holding whether the bullet is loaded in his motherShip.
	 */
	private boolean loadedInMotherShip = false;

	/**
	 * Returns whether the bullet is loaded.
	 * @return True if and only if the bullet is loaded in his mothership.
	 */
	@Basic
	@Raw
	public boolean isLoadedInMotherShip() {
		return loadedInMotherShip;
	}
	
	/**
	 * Sets the isLoadedInMotherShip variable to true or false
	 * 
	 * @param  loadedInMotherShip
	 * 		 | The state of the ship (loaded is true and unloaded is false)
	 */
	void setLoadedInMotherBoard(boolean loadedInMotherShip) throws NoParentException {
		if (this.getMotherShip() == null)
			throw new NoParentException();
		this.loadedInMotherShip = loadedInMotherShip;
		this.setCollision(!loadedInMotherShip);
	}
	
	
	
	/**
	 * Internal function that bounces the bullet off a wall.
	 * 
	 * @post Changes the velocity when a bullet bounces against the wall of the world.
	 */
	@Override
	void collideWithWall() {
		this.addBounce();
		if (this.getBounces() >= MAX_BOUNCES)
			this.terminate();
		else
			super.collideWithWall();
	}
	

	/**
	 * Function that handles when a bullet hits a ship.
	 * @post   If the ship that got hit by the bullet is the bullet's own mothership, then the bullet will be added to the magazine of the ship again.
	 * @post   Otherwise, the bullet and the hit on the ship gets handled:
	 * @effect ship.triggerHit()
	 * 		   See @post
	 * @param  ship
	 * @throws NullPointerException  
	 * 		   If ship is null.
	 */
	void hit(Ship ship) throws NullPointerException {
		System.out.println("---hit---"); //TODO: weg
		//System.out.println(this.getParent());
		//System.out.println(ship);
		if (ship == this.getMotherShip())
			try {
				ship.loadBullet(this);
			} catch (DoubleEntityException | MisMatchWorldsException ex) { //these should never happen //TODO: zie da da nog just is
				throw new RuntimeException(ex);
			}
		else {
			if (this.getMotherShip() != null)
				this.getMotherShip().triggerScoreOn(ship); //does nothing for now (part 3?)
			ship.triggerHit();
			this.terminate();
		}
	}

	
	/**
	 * Handles the collision between two bullets.
	 * @param  first
	 * 		   The first bullet to collide.
	 * @param  second
	 *         The second bullet to collide.
	 * @post   Both bullets are terminated.
	 */
	static void collideBullets(Bullet first, Bullet second) {
		first.terminate();
		second.terminate();
	}
}
