package asteroids.model;

public class Bullet extends Entity {

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
		// TODO Auto-generated constructor stub
		
		
		this.setRho(7.8 * Math.pow(10,  12)); //constant for this class

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
		if (ship == parent) {
			setPosition(parent.getPosition());
			setVelocity(parent.getVelocity());
			setAcceleration(parent.getAcceleration());
		} else {
			parent.triggerScoreOn(ship); //does nothing (part 3?)
			ship.triggerHit();
			terminate();
		}
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
