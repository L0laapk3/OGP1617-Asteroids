package asteroids.model;

import asteroids.exceptions.DoubleEntityException;
import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;
import asteroids.exceptions.MisMatchWorldsException;
import asteroids.exceptions.NoMotherShipException;
import asteroids.exceptions.NotOverlapException;
import asteroids.util.OGUtil;
import asteroids.util.Vector2;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;


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

public class Bullet extends EntityWithConstantDensity {


	private static final double MIN_RADIUS_BULLET = 1;
	private static final double RHO_BULLET = 7.8E12;
	
	/**
	 * Constant holding the max amount of bounces before a bullet dissapears.
	 */
	private static final double MAX_BOUNCES = 3;

	
	
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
	 * @param  motherShip
	 * 		   The mothership of the newly created bullet
	 * @throws NotOverlapException 
	 * 		   This exception will be thrown if the bullet and the ship do not overlap.
	 * @throws RuntimeException 
	 * @throws NullPointerException 
	 * @effect This new bullet is initialized as a new Entity with given position, velocity and radius.
	 * 		 | super(x, y, xVelocity, yVelocity, radius)
	 * @post   The minimum radius of the bullet is set to MIN_RADIUS_BULLET.
	 * @post   The mothership of this bullet will be set to the given mothership.
	 */

	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius, Ship motherShip) throws InvalidRadiusException, InvalidPositionException, NullPointerException, NotOverlapException {
		super(x, y, xVelocity, yVelocity, radius, RHO_BULLET, MIN_RADIUS_BULLET);	

		
		this.motherShip = motherShip;
		
		if (!isNullOrTerminated(motherShip)) {
			motherShip.loadBullet(this);
		}
	}
	
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
	@Raw
	
	void setLoadedInMotherShip(boolean loadedInMotherShip) throws NoMotherShipException {
		if (isNullOrTerminated(this.getMotherShip()))
			throw new NoMotherShipException();
		this.loadedInMotherShip = loadedInMotherShip;
		this.setCollision(!loadedInMotherShip);
	}
	
	
	
	/**
	 * Internal function that bounces the bullet off a wall.
	 * 
	 * @post Changes the velocity when a bullet bounces against the wall of the world.
	 */
	@Raw
	@Override
	void collideWithWall() {
		this.addBounce();
		OGUtil.println("BOUNCE " + this + " " + this.getBounces());
		if (this.getBounces() >= MAX_BOUNCES)
			this.die();
		else
			super.collideWithWall();
	}
	
	
	/**
	 * Handles the collision between a bullet and another entity
	 * @param  entity
	 *         The entity that this bullet collides with.
	 * @effect If the other entity is the bullet's mothership, then the bullet will get loaded into the ship.
	 * @effect Otherwise, both the bullet and the other entity are terminated.
	 */
	@Raw
	void collideEntity(Entity entity) {
		if (entity instanceof Ship && (Ship)entity == this.getMotherShip()) {
			try {
				this.setPosition(entity.getPosition());
				((Ship)entity).loadBullet(this);
			} catch (DoubleEntityException | MisMatchWorldsException | NotOverlapException ex) { //these should never happen
				throw new AssertionError(ex);
			}
		} else {
			entity.die();
			this.die();
		}
	}
	/**
	 * Variable holding the maximum speed of all bullets.
	 */
	static private double maxSpeed;
	
	/**
	 * returns the maximum speed of the bullet.
	 * @return maxSpeed
	 */
	@Raw
	@Basic
	@Override
	public double getMaxSpeed() {
		return maxSpeed;
	}
	
	/**
	 * sets the maximum speed of all bullets to newMaxSpeed.
	 * @param newMaxSpeed
	 * 		  The new maximum speed of all bullets.
	 */
	@Raw
	@Basic
	public static void setMaxSpeedBullet(double newMaxSpeed) {
		maxSpeed=newMaxSpeed;
	}
	
	/**
	 * Function that terminates a bullet properly.
	 * @post This bullet will be unloaded from his mothership.
	 * @effect super.terminate
	 */
	
	@Override
	public void terminate() {
		if (this.isLoadedInMotherShip()) {
			this.getMotherShip().unloadBullet(this);
		} 
		super.terminate();
	}

	/**
	 * Function that gets called on each entity when world.evolve(dt) gets called.
	 * @param  dt
	 * 	       The time that the world has evolved with.
	 * @effect Sets the position of this bullet to follow the mothership, if the bullet is loaded in that ship.
	 */
	@Raw
	@Override
	void evolve(double dt) {
		if (this.isLoadedInMotherShip())
			this.setPosition(this.getMotherShip().getPosition());
	}
}




