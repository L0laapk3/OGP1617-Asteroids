package asteroids.model;




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

	
	private static final double MIN_RADIUS_BULLET = 1;
	
	/**
	 * Returns the ship that originally shot this bullet.
	 */
	public Ship getParent() {
		return this.parent;
	}
	
	private final Ship parent;
	private boolean loadedInParent = true;

	public boolean isLoadedInParent() {
		return loadedInParent;
	}
	
	void setLoadedInParent(boolean loadedInParent) {
		this.loadedInParent = loadedInParent;
	}
	
	
	//TODO COMMENTS
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius, double direction, Ship parent) {
		super(x, y, xVelocity, yVelocity, radius, direction);
		this.setMinRadius(MIN_RADIUS_BULLET);
		// MOET ER HIER NOG IETS GEBEUREN OFWA
		

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
