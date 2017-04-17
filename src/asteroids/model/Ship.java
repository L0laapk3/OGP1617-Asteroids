package asteroids.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import asteroids.exceptions.AlreadyTerminatedException;
import asteroids.exceptions.BulletNotLoadedException;
import asteroids.exceptions.DoubleEntityException;
import asteroids.exceptions.EntitiesOverlapException;
import asteroids.exceptions.InvalidParentShipException;
import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;
import asteroids.exceptions.MisMatchWorldsException;
import asteroids.exceptions.NoWorldException;
import asteroids.exceptions.NotWithinBoundariesException;
import asteroids.util.OGUtil;
import asteroids.util.Vector2;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;


//TODO: OVERAL IMMUTABLE
/**
 * A class to define ships.
 * 
 * @effect Entity
 * @invar The radius must be bigger than MIN_RADIUS for Ship.
 *      | isValidRadius(getRadius())
 * @version 2.0
 * @author Kris Keersmaekers
 * @author Rik Pauwels
 */

public class Ship extends Entity {
	

	
	
	private static final double MIN_RHO = 1.42E12;
	
	
	
	@Deprecated
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		this(x, y, xVelocity, yVelocity, radius, orientation, 1.42 * Math.pow(10, 12));
	}

	/**
	 * Create a new ship with the given position, velocity, radius and orientation (in radians).
	 * 
	 * @param  x
	 *         The x coordinate where the new ship has to be created.
	 * @param  y
	 *         The y coordinate where the new ship has to be created.
	 * @param  xVelocity
	 *         The initial speed in the x direction of the new ship.
	 * @param  yVelocity
	 *         The initial speed in the y direction of the new ship.
	 * @param  orientation
	 *         The direction that the new ship is initially pointed at.
	 * @param  radius
	 *         The size of the newly created ship.
	 * @param  mass
	 *         The mass of the newly created ship.
	 * @invar  The mass of the entity will always be equal to or greater than 4/3*PI*radius^3*(mass density)
	 * 		 | this.isValidMass(mass)
	 * @effect This new ship is initialized as a new Entity with given position, velocity, radius and orientation.
	 *       | super(x, y, xVelocity, yVelocity, radius, orientation, mass)
	 * @post   If the mass density of the ship is lower than MIN_RHO, the mass will be adjusted so that the mass density of the ship will equal MIN_RHO.
	 * @throws InvalidRadiusException
	 *         If the radius is less than MIN_RADIUS.
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, orientation, Math.max(mass, calculateBassMass(MIN_RHO, radius))); //total: if the mass is too low, it will be set to a higher value
		this.setMinRadius(MIN_RADIUS);
	}

	/**
	 * Constant that defines how fast the ship shoots its bullets.
	 */
	private static final double BULLET_LAUNCHING_SPEED = 250;
	
	/**
	 * Constant that defines the minimum radius of a ship.
	 */
	private static final double MIN_RADIUS = 10;

	/**
	 * HashSet holding the loaded bullets from the ship
	 */
	private Set<Bullet> loadedBullets = new HashSet<Bullet>();

	/**
	 * function to load a bullet to this ship
	 * 
	 * @param  bullet
	 *         The bullet that has to be loaded.
	 * @post   The given bullet will be added to the Set with loaded bullets from this ship.
	 *       | new.getLoadedBullets.contains(bullet) = true
	 * @effect If the bullet is in a different world than the ship, the world of the bullet will be set to the world of the ship.
	 * 		 | new.bullet.getWorld() == ship.getWorld()
	 * @throws NullPointerException
	 *         bullet must not be null.
	 * @note defensive
	 */
	@Raw
	public void loadBullet(Bullet bullet) throws NullPointerException, RuntimeException {
		if (bullet == null)
			throw new NullPointerException();

		OGUtil.println("LOAD " + this + " " + bullet);

		bullet.setMotherShip(this);
		loadedBullets.add(bullet);
		this.updateLoadMass();
		bullet.setLoadedInMotherShip(true);
		bullet.resetBounces();

		if (this.getWorld() != bullet.getWorld())
			try {
				this.getWorld().addEntity(bullet);
				// These exceptions should never happen as the bullet has been set to be  loaded in this ship.
			} catch (DoubleEntityException | NotWithinBoundariesException | EntitiesOverlapException ex) {
				throw new RuntimeException(ex);
			}
	}
	
	/**
	 * Gets the number of bullets in ship.
	 * 
	 * @return The number of bullets that the ship has loaded.
	 */
	@Raw
	@Basic
	public int getNumberOfBullets() {
		return loadedBullets.size();
	}

	/**
	 * Returns a Set of all the loaded bullets in the ship.
	 */
	@Raw
	@Basic
	public Set<Bullet> getLoadedBullets() {
		return new HashSet<Bullet>(loadedBullets);
	}

	/**
	 * function to load a bullet to this ship
	 * 
	 * @param  A collection of bullets The collection of bullets that has to be loaded.
	 * @effect loadBullet(bullet)
	 * 		 | ship.getLoadedBullets.contains(bullet) = true
	 * @post   The given bullets will be added to the Set with loaded bullets from this ship.
	 *       | new.getLoadedBullets.contains(bullets) = true
	 */
	@Raw
	public void loadBullet(Collection<Bullet> bullets) throws NullPointerException {
		for (Bullet bullet : bullets) {
			this.loadBullet(bullet);
		}
	}

	/**
	 * function to unload a bullet from this ship
	 * 
	 * @param  bullet
	 *         The bullet that has to be unloaded.
	 * @post   The given bullet will be removed to the Set with loaded bullets from this ship.
	 *       | new.getLoadedBullets.contains(bullet) = false
	 * @throws NullPointerException
	 *         bullet must not be null.
	 */
	@Raw
	public void unloadBullet(Bullet bullet) throws NullPointerException {
		if (bullet == null)
			throw new NullPointerException();
		bullet.setLoadedInMotherShip(false);
		loadedBullets.remove(bullet);
		updateLoadMass();
	}

	/**
	 * Checks if there is a bullet available in the ship's magazine.
	 * 
	 * @return true if and only if there is at least 1 bullet loaded in the ship.
	 */
	@Raw
	@Basic
	public boolean hasBullet() {
		return loadedBullets.size() > 0;
	}

	/**
	 * Attempts to shoot a bullet from the ship.
	 * 
	 * @post 	Shoots a bullet, only if there is one available.
	 * @return false if there is no bullet loaded in the ship.
	 * @return true if it successfully shot a bullet from the ship.
	 */
	@Raw
	public boolean shootBullet() throws NoWorldException, MisMatchWorldsException, InvalidParentShipException, BulletNotLoadedException {
		//Bullets are always red because facade.getBulletShip(bullet) is only called in createBulletVisualization,
		//and the method is required to return null when the bullets are not loaded in mothership.
		if (!hasBullet())
			return false;
		OGUtil.println("bullets left: " + loadedBullets.size());
		shootBullet(loadedBullets.iterator().next()); // gets one (pseudo)random bullet from hashset
		return true;
	}

	/**
	 * Shoots the given bullet from the ship.
	 * 
	 * @param  bullet
	 *         The bullet to shoot
	 * @effect The world of the given bullet will be set to the world of the given ship.
	 * 		 | bullet.setWorld(this.getWorld())
	 * @effect The bullet will not be unloaded in his motherboard if there is no error Exception.
	 * 		 | bullet.setLoadedInMotherBoard(false);
	 * @effect The bullets position will be set so it touches the ship from the outside in the direction of the orientation of the mothership.
	 * 		 | see implementation
	 * @effect Set the speed of the bullet to the BULLET_LAUNCHING_SPEED
	 * 		 | bullet.setVelocity(Vector2.multiply(unitDirection, BULLET_LAUNCHING_SPEED))
	 * @effect If the sudden replacing of the bullet will trigger collisions they will happen.
	 * 		 | Collisions.collide(bullet, collidesWith);
	 * @effect The mass of the mothership will be updated since there is one less bullet in the ship.
	 * 		 | updateLoadMass()
	 * @throws NoWorldException
	 * @throws BulletNotLoadedException
	 * @throws InvalidParentShipException
	 * 
	 * @note defensive
	 */
	@Raw
	public void shootBullet(Bullet bullet) throws NoWorldException, InvalidParentShipException, BulletNotLoadedException {
		OGUtil.println("SHOOT " + bullet);
		if (isNullOrTerminated(this.getCollisionWorld()))
			throw new NoWorldException();
		if (this.isTerminated())
			throw new AlreadyTerminatedException("Ship cannot fire because it is terminated.");
		if (bullet.isTerminated())
			throw new AlreadyTerminatedException("Cannot fire bullet because it is terminated.");
		bullet.setWorld(this.getWorld());
		if (bullet.getMotherShip() != this)
			throw new InvalidParentShipException();
		if (!loadedBullets.contains(bullet))
			throw new BulletNotLoadedException("Cannot shoot bullet because it is not loaded in the ship.");

		bullet.setLoadedInMotherShip(false);
		this.unloadBullet(bullet);
		Vector2 unitDirection = new Vector2(Math.cos(this.getOrientation()), Math.sin(this.getOrientation()));
		bullet.setPosition(Vector2.add(this.getPosition(), Vector2.multiply(unitDirection, this.getRadius() + bullet.getRadius())));
		OGUtil.println("radius: " + bullet.getRadius());
		OGUtil.println(bullet.getPosition());
		bullet.mirrorPositionWall();
		OGUtil.println(bullet.getPosition());
		OGUtil.println("/SHOOT");
		bullet.setVelocity(Vector2.multiply(unitDirection, BULLET_LAUNCHING_SPEED));

		Entity collidesWith = bullet.getCollisionWorld().findOverlap(bullet);
		while (!isNullOrTerminated(collidesWith)) {
			OGUtil.println(collidesWith);
			OGUtil.println(bullet.getMotherShip());
			Collisions.collide(bullet, collidesWith);
			OGUtil.println("---");
			OGUtil.println(bullet);
			OGUtil.println(bullet.isLoadedInMotherShip());
			OGUtil.println(bullet.getCollisionWorld());
			collidesWith = (bullet.isTerminated() || bullet.isLoadedInMotherShip()) ? null : bullet.getWorld().findOverlap(bullet);
		}

		updateLoadMass();
	}

	


	/**
	 * Check whether the mass density is valid for a ship.
	 * 
	 * @param  rho
	 * 		   The mass density to check.
	 * @param  radius
	 *         The radius to use to calculate the amss.
	 * @return True if and only if mass is finite, and the mass density is bigger than MIN_RHO.
	 *       | see implementation
	 */
	@Raw
	@Basic
	public static boolean isValidBaseMass(double mass, double radius) {
		return mass >= calculateBassMass(MIN_RHO, radius) && OGUtil.isInvalidNumber(mass);
	}
	
	
	/**
	 * The total mass of the load.
	 */
	private double loadMass = 0;

	/**
	 * Gets the total mass of the loaded bullets.
	 */
	@Raw
	@Basic
	public double getLoadMass() {
		return this.loadMass;
	}

	/**
	 * A function to update the total mass of the loaded bullets.
	 * 
	 * @post  Computes the total mass of the loaded bullets.
	 * 		 | loadMass+=bullet.getMass() for each bullet in ship.getBullets()
	 */
	@Raw
	void updateLoadMass() {
		this.loadMass = 0;
		for (Bullet bullet : loadedBullets)
			this.loadMass += bullet.getMass();
	}

	/**
	 * Gets the total mass of the ship.
	 */
	@Raw
	@Override
	public double getMass() {
		//OGUtil.println("super mass " + super.getMass() + ", bullet mass " + this.getLoadMass() + " "  + this);
		return super.getMass() + this.getLoadMass();
	}


	/**
	 * Recalculates the velocity of the two ships, when they bounce
	 * 
	 * @param firstShip
	 *        The first ship that collides.
	 * @param secondShip
	 *        The second ships that collides.
	 * @post  The Velocity of the two ships will be updated according to the laws of physics.
	 * 		| see implementation
	 */
	public static void collideShips(Ship firstShip, Ship secondShip) {

		double sigma = firstShip.getRadius() + secondShip.getRadius();
		Vector2 J = Vector2.multiply(Vector2.subtract(firstShip.getPosition(), secondShip.getPosition()),
				2 * firstShip.getMass() * secondShip.getMass()
						* Vector2.dot(Vector2.subtract(firstShip.getVelocity(), secondShip.getVelocity()), Vector2.subtract(firstShip.getPosition(), secondShip.getPosition()))
						/ (sigma * sigma * (firstShip.getMass() + secondShip.getMass())));

		firstShip.setVelocity(Vector2.subtract(firstShip.getVelocity(), Vector2.divide(J, firstShip.getMass())));
		secondShip.setVelocity(Vector2.add(secondShip.getVelocity(), Vector2.divide(J, secondShip.getMass())));
	}

	/**
	 * Variable that holds whether the thruster is on or of
	 */
	private boolean thrusterState = false;

	/**
	 * function that returns the Thrusterstate of the ship
	 * 
	 * @return thrusterstate Returns the thrusterstate
	 */
	@Raw
	@Basic
	public boolean getThrusterstate() {
		return this.thrusterState;
	}

	/**
	 * Variable that holds the thrustforce from the ship
	 */
	private static final double THRUSTFORCE = 1.1 * Math.pow(10, 21);

	/**
	 * function that calculates the acceleration of the ship
	 * 
	 * @param state
	 * 		  The state in which the thrusters must be set.
	 * 
	 * @effect Sets the thruster state of the ship.
	 *       | thrusterState = true;
	 * @effect Sets the thrusting force of the ship.
	 *       | this.setThrust(state ? thrustforce : 0);
	 */
	@Raw
	public void setThruster(boolean state) {
		this.thrusterState = state;
		this.setThrust(state ? THRUSTFORCE : 0);
	}

	/**
	 * This function defines what will happen when the ship gets hit by a bullet: The ship gets terminated.
	 * 
	 * @post Terminate the ship. 
	 *     | this.terminate();
	 */
	@Raw
	@Basic
	public void triggerHit() {
		this.terminate();
	}

	/**
	 * This function defines the behaviour that happens when the ship gets a kill on another ship.
	 * 
	 * @post Nothing will happen. (for now)
	 * @param ship
	 *      | The ship that was killed.
	 */
	@Raw
	public void triggerScoreOn(Ship ship) {
		// add score, this does nothing for now since there is no score system

	}

	
	/**
	 * Terminates the given ship and all of the bullets in this ship.
	 * 
	 * @post  Sets the variable terminate to true.
	 * 		| ship.isTerminated() == true 
	 * @effect Terminates all the bullets in this ship.
	 * 		| bullet.terminate() for bullet in loadedBullets
	 */
	@Raw
	@Override
	public void terminate() {
		//naar onze interpretatie moeten de bullets mee sterven met het schip
		for (Bullet bullet : loadedBullets)
			bullet.terminate();
		super.terminate();
	}
}