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
	public int getBounces() {
		return this.bounces;
	}
	
	/**
	 * Resets the amount of bounces.
	 * @post getBounces() == 0
	 */
	void resetBounces() {
		this.bounces = 0;
	}
	
	/**
	 * Adds one bounce to the bounce counter.
	 * @post getBounces()++
	 */
	void addBounce() {
		this.bounces++;
	}
	
	/**
	 * Variable holding the parent from the bullet.
	 */
	private Ship parent;
	
	/**
	 * Returns the ship that originally shot this bullet.
	 * @return The parent of the bullet.
	 */
	@Basic
	@Raw
	public Ship getParent() {
		return this.parent;
	}
	
	/**
	 * Sets the ship that this bullet belongs to.
	 */
	@Basic
	@Raw
	void setParent(Ship parent) {
		this.parent = parent;
	}
	
	/**
	 * Variable holding whether the bullet is loaded in his parent.
	 */
	private boolean loadedInParent = false;

	/**
	 * Returns whether the bullet is loaded.
	 * @return True if and only if the bullet is loaded in his parent.
	 */
	@Basic
	@Raw
	public boolean isLoadedInParent() {
		return loadedInParent;
	}
	
	/**
	 * Sets the isLoadedInParent variable to true or false
	 * 
	 * @param  loadedInParent
	 * 		 | The state of the ship (loaded is true and unloaded is false)
	 */
	void setLoadedInParent(boolean loadedInParent) throws NoParentException {
		if (this.getParent() == null)
			throw new NoParentException();
		this.loadedInParent = loadedInParent;
	}
	
	
	
	
	
	
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
	 * @effect This new bullet is initialized as a new Entity with given position, velocity, radius and orientation.
	 * 		 | super(x,y,xVelocity,yVelocity,radius,orientation)
	 * @post   The minimum radius of the bullet is set to MIN_RADIUS_BULLET.
	 * @post   The parent of this bullet will be set to the given parent.
	 * @post   The mass density from this bullet is set to 7.8*10^12 kg/km^3
	 */
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius, Ship parent) throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, 0);
		this.setMinRadius(MIN_RADIUS_BULLET);		

		setRho(7.8 * Math.pow(10,  12)); //constant for this class
		
		this.parent = parent;
	}




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
	 * @post   If the ship that got hit by the bullet is the bullet's own parent, then the bullet will be added to the magazine of the ship again.
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
		if (ship == this.getParent())
			try {
				ship.loadBullet(this);
			} catch (DoubleEntityException | MisMatchWorldsException ex) { //these should never happen //TODO: zie da da nog just is
				throw new RuntimeException(ex);
			}
		else {
			if (this.getParent() != null)
				this.getParent().triggerScoreOn(ship); //does nothing for now (part 3?)
			ship.triggerHit();
			this.terminate();
		}
	}

	
	/**
	 * Handles the collision between two bullets..
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
