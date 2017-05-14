package asteroids.model;

import asteroids.exceptions.DoubleEntityException;
import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;
import asteroids.exceptions.MisMatchWorldsException;
import asteroids.exceptions.NoMotherShipException;
import asteroids.util.OGUtil;
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
	 * @effect This new bullet is initialized as a new Entity with given position, velocity and radius.
	 * 		 | super(x, y, xVelocity, yVelocity, radius)
	 * @post   The minimum radius of the bullet is set to MIN_RADIUS_BULLET.
	 * @post   The mothership of this bullet will be set to the given mothership.
	 */

	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius, Ship motherShip) throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
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
	 * Function that handles when a bullet hits a ship.
	 * @post   If the ship that got hit by the bullet is the bullet's own mothership, then the bullet will be added to the magazine of the ship again.
	 * @post   Otherwise, the bullet and the hit on the ship gets handled:
	 * @effect ship.triggerHit()
	 * 		   See @post
	 * @param  ship
	 * @throws NullPointerException  
	 * 		   If ship is null.
	 */
	@Raw
	void hit(Ship ship) throws NullPointerException {
		OGUtil.println("---hit---");
		//OGUtil.println(this.getParent());
		//OGUtil.println(ship);
		
		System.out.print("Het moederschip in de hitfunctie: ");
		System.out.println(this.getMotherShip());
		System.out.print("Het meegegeven ship: ");
		System.out.println(ship);
		
		if (ship == this.getMotherShip())
			try {
				System.out.println("bullet terug laden");
				System.out.print("de bullets die al geladen zijn: ");
				System.out.println(ship.getLoadedBullets());
				ship.loadBullet(this);
			} catch (DoubleEntityException | MisMatchWorldsException ex) { //these should never happen
				System.out.println("fak");
				throw new RuntimeException(ex);
			}		
		else {
			
			if (!isNullOrTerminated(this.getMotherShip()))
				this.getMotherShip().triggerScoreOn(ship); //does nothing for now (part 3?)
			ship.triggerHit();
			this.die();
		}
	}

	/**
	 * Handles the collision between a bullet and another entity
	 * @param  entity
	 *         The entity that this bullet collides with.
	 * @post   Both the bullet and the other entity are terminated.
	 */
	@Raw
	void collideEntity(Entity entity) {
		entity.die();
		this.die();
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
	
	@Override
	public void terminate() {
		System.out.print("het moederschip in terminate is: ");
		System.out.println(this.getMotherShip());
		if (this.getMotherShip() != null) {
			this.getMotherShip().unloadBullet(this);
		}
		super.terminate();
	}

}
