package asteroids.model;

<<<<<<< HEAD
import be.kuleuven.cs.som.annotate.*;
=======
import asteroids.exceptions.*;
>>>>>>> afbf603264e3abcaf228e3fd7c62fddf61ae7396

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

//TODO: COMMENTS VERDER AFWERKEN
public class Bullet extends Entity {

	/**
	 * Variable holding the minimum radius from a bullet.
	 */
	private static final double MIN_RADIUS_BULLET = 1;
	
	/**
	 * Variable holding the parent from the bullet.
	 */
	private final Ship parent;
	
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
	 * Variable holding whether the bullet is loaded in his parent.
	 */
	private boolean loadedInParent = true;

	/**
	 * Returns whether the bullet is loaded.
	 * @return True if and only if the bullet is loaded in his parent.
	 */
	@Basic
	@Raw
	public boolean isLoadedInParent() {
		return loadedInParent;
	}
	
<<<<<<< HEAD
	/**
	 * Sets the isLoadedInParent variable to true or false
	 * 
	 * @param  loadedInParent
	 * 		 | The state of the ship (loaded is true and unloaded is false)
	 */
	void setLoadedInParent(boolean loadedInParent) {
		this.loadedInParent = loadedInParent;
=======
	void load() {
		if (this.parent == null)
			throw new NoParentShipException();
		this.parent.loadBullet(this);
		this.loadedInParent = true;
	}
	
	void shoot() {
		this.loadedInParent = false;
		this.parent.unloadBullet(this);
		//TODO: SET VELOCITY etc
>>>>>>> afbf603264e3abcaf228e3fd7c62fddf61ae7396
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
	 * @param  orientation
	 * 		   The direction that the new bullet is initially pointed at.
	 * @param  radius
	 * 		   The size of the newly created bullet.
	 * @param  mass
	 * 		   The mass of the newly created bullet.
	 * @parent 
	 * @effect This new bullet is initialized as a new Entity with given position, velocity, radius and orientation.
	 * 		 | super(x,y,xVelocity,yVelocity,radius,orientation)
	 * @post   The minimum radius of the bullet is set to MIN_RADIUS_BULLET.
	 * @post   The parent of this bullet will be set to the given parent.
	 * @post   The mass density from this bullet is set to 7.8*10^12 kg/km^3
	 */
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, Ship parent) {
		super(x, y, xVelocity, yVelocity, radius, orientation);
		this.setMinRadius(MIN_RADIUS_BULLET);		

		setRho(7.8 * Math.pow(10,  12)); //constant for this class
		
		this.parent = parent;
	}





	/**
	 * Function that handles when a bullet hits a ship.
	 * @post   If the ship that got hit by the bullet is the bullet's own parent, then the bullet will be added to the magazine of the ship again.
	 * @post   Otherwise, the bullet and the hit on the ship gets handled:
	 * @effect ship.triggerHit()
	 * 		   See @post
	 * @param  ship
	 */
	public void hit(Ship ship) {
		assert(ship != parent); //"Loaded bullets should be excluded from the collision engine.")
		
		parent.triggerScoreOn(ship); //does nothing for now (part 3?)
		ship.triggerHit();
		terminate();
	}



	/**
	 * Function to handle the collision of two bullets. Both bullets are terminated.
	 * @param firstBullet
	 * @param secondBullet
	 */
	public static void collideWithSameType(Bullet firstBullet, Bullet secondBullet) {
		firstBullet.terminate();
		secondBullet.terminate();
	}
}
