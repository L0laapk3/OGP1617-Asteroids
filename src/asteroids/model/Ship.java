package asteroids.model;

import java.util.HashSet;
import java.util.Collection;
import java.util.Set;

import asteroids.exceptions.*;
import asteroids.util.Vector2;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;


//TODO: ALLE FUNCTIES MOETEN ECHT WEL GEORDEND WORDEN ZODAT DIE LUIE ASSISTENTEN HET KUNNEN VINDEN


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
	
	/**
	 * Constant that defines how fast the ship shoots its bullets.
	 */
	private static final double BULLET_LAUNCHING_SPEED = 250;
	
	private static final double MIN_RADIUS_SHIP = 10;
	
	/**
	 * HashSet holding the loaded bullets from the ship
	 */
	private Set<Bullet> loadedBullets = new HashSet<Bullet>();
	
	
	/**
	 * function to load a bullet to this ship
	 * @param  bullet
	 * 		   The bullet that has to be loaded.
	 * @post   The given bullet will be added to the Set with loaded bullets from this ship.
	 * 		 | new.getLoadedBullets.contains(bullet) = true
	 * @effect If the bullet is in a different world than the ship, the world of the bullet will be set to the world of a ship.
	 * @throws NullPointerException
	 * 		   bullet must not be null.      
	 * @throws MisMatchWorldsException
	 * 		   If the bullet and the ship are not in the same world.
	 * @note   defensive
	 */
	public void loadBullet(Bullet bullet) throws NullPointerException  {
		if (bullet == null)
			throw new NullPointerException();

		System.out.println("LOAD " + this + " " + bullet); //TODO: weg
		
		bullet.setParent(this);
		loadedBullets.add(bullet);
		bullet.setLoadedInParent(true);

		if (this.getWorld() != bullet.getWorld())
			try {
				this.getWorld().addEntity(bullet); //These exceptions should never happen as the bullet has been set to be loaded in this ship
			} catch (DoubleEntityException | NotWithinBoundariesException | EntitiesOverlapException ex) {
				throw new RuntimeException(ex);
			}
	}
	
	/**
	 * function to load a bullet to this ship
	 * @param A collection of bullets
	 * 		  The collection of bullets that has to be loaded.
	 * @effect loadBullet(bullet) //TODO wa moet er hier??
	 * @post  The given bullets will be added to the Set with loaded bullets from this ship.
	 * 		| new.getLoadedBullets.contains(bullets) = true
	 */
	public void loadBullet(Collection<Bullet> bullets) throws NullPointerException {
		for (Bullet bullet : bullets) {
			this.loadBullet(bullet);
		}
	}
	
	/**
	 * function to unload a bullet to this ship
	 * @param  bullet
	 * 		   The bullet that has to be unloaded.
	 * @post   The given bullet will be removed to the Set with loaded bullets from this ship.
	 * 		 | new.getLoadedBullets.contains(bullet) = false
	 * @throws NullPointerException
	 * 		   bullet must not be null.
	 */
	public void unloadBullet(Bullet bullet) throws NullPointerException {
		if (bullet == null)
			throw new NullPointerException();
		bullet.setLoadedInParent(false);
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
	 * @return true if it successfully shot a bullet from the ship.
	 */
	public boolean shootBullet() throws NoWorldException, MisMatchWorldsException, InvalidParentShipException, BulletNotLoadedException {
		if (!hasBullet())
			return false;
		shootBullet(loadedBullets.iterator().next()); //gets one (pseudo)random bullet from hashset
		return true;
	}
	/**
	 * Shoots the given bullet from the ship.
	 * @param bullet
	 * 	      The bullet to shoot
	 * @throws UndefinedCollisionBehaviourException 
	 * @note  defensive
	 */
	public void shootBullet(Bullet bullet) throws NoWorldException, MisMatchWorldsException, InvalidParentShipException, BulletNotLoadedException {
		if (this.getWorld() == null)
			throw new NoWorldException();
		if (this.getWorld() != bullet.getWorld())
			throw new MisMatchWorldsException("Bullet and ship must be in the same world.");
		if (bullet.getParent() != this)
			throw new InvalidParentShipException();
		if (!loadedBullets.contains(bullet))
			throw new BulletNotLoadedException("Cannot shoot bullet because it is not loaded in the ship.");
		
		bullet.setLoadedInParent(false);
		this.unloadBullet(bullet);
		Vector2 unitDirection = new Vector2(Math.cos(this.getOrientation()), Math.sin(this.getOrientation()));
		bullet.setPosition(Vector2.add(this.getPosition(), Vector2.multiply(unitDirection, this.getRadius() + bullet.getRadius())));
		bullet.mirrorPositionWall();
		bullet.setVelocity(Vector2.multiply(unitDirection, BULLET_LAUNCHING_SPEED));
		System.out.println("SHOOT " + bullet); //TODO: weg
		
		Entity collidesWith = bullet.getWorld().findOverlap(bullet);
		while (collidesWith != null) {
			System.out.println(collidesWith); //TODO: weg
			System.out.println(bullet.getParent());
			Collisions.collide(bullet, collidesWith);
			System.out.println("---");
			System.out.println(bullet);
			System.out.println(bullet.isLoadedInParent());
			System.out.println(bullet.getWorld());
			collidesWith = (bullet.isTerminated() || bullet.isLoadedInParent()) ? null : bullet.getWorld().findOverlap(bullet);
		}

		
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
	
	
	
	/**
	 * Gets the number of bullets in ship.
	 * @return The number of bullets that the ship has loaded.
	 */
	public int getNumberOfBullets() {
		return loadedBullets.size();
	}
	
	/**
	 * Returns a Set of all the loaded bullets in the ship.
	 */
	public Set<Bullet> getLoadedBullets() {
		return new HashSet<Bullet>(loadedBullets);
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
	
	
	
	//createShip(width / 2., height / 2., 1, 6, 40, 0, 3e17);
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double rho) throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, orientation);
		this.setMinRadius(MIN_RADIUS_SHIP);
		
		//TOTAL
		if (isValidRho(rho)) {
			setRho(rho);
		} else {
			setRho(1.42*Math.pow(10, 12));
		}
		
	}
	
	
	

	
	
	
	
	
	
	/**
	 * Recalculates the velocity of the two ships, when they bounce
	 * @param firstShip
	 * 		  The first ship that collides.
	 * @param secondShip
	 * 		  The second ships that collides.
	 * @post  The Velocity of the two ships will be updated according to the laws of physics.
	 */
	public static void collideShips(Ship firstShip, Ship secondShip) {
				
		
		double sigma = firstShip.getRadius() + secondShip.getRadius();
		Vector2 J = Vector2.multiply(Vector2.subtract(firstShip.getPosition(), secondShip.getPosition()), 2 * firstShip.getMass() * secondShip.getMass() * Vector2.dot(
				Vector2.subtract(firstShip.getVelocity(), secondShip.getVelocity()), 
				Vector2.subtract(firstShip.getPosition(), secondShip.getPosition())
			) / (sigma*sigma * (firstShip.getMass() + secondShip.getMass())));
		
		firstShip.setVelocity(Vector2.subtract(firstShip.getVelocity(), Vector2.divide(J, firstShip.getMass())));
		secondShip.setVelocity(Vector2.add(secondShip.getVelocity(), Vector2.divide(J, secondShip.getMass())));
	}
	
	//-------------Thrust functions
	/**
	 * Variable that holds whether the thruster is on or of
	 */
	private boolean thrusterState = false;
	
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
	private static final double thrustforce = 1.1*(Math.pow(10, 24));	//TODO was 21 ipv 24 maar anders is er niks te zien dus wel terugzetten voor in te dienen
	
	
	/**
	 * function that calculates the acceleration of the ship
	 * @effect this function calculates the acceleration of the ship and sets it using setAccelaration when the thruster must be active 
	 * 		 | this.setAcceleration(this.getAcceleration()* thrustforce/this.getMass()));
	 *	     | thrusterState = true;
     * @effect this function sets the acceleration to (0,0) using setAccelaration when the thruster must be inactive
	 * 		 | this.setAcceleration(new Vector2(0,0));
	 *		 | thrusterState = false;
	 */
	public void thrustOnOff(boolean state) {
		if (state) {
			this.setAcceleration(thrustforce / this.getMass());
			thrusterState = true;
		} else {
			this.setAcceleration(0);
			thrusterState = false;
		}
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
	
	
	
	
	
	/*
	@Override
	public void terminate() {
		//TODO: dees werkt precies nie? als er schepen doodgaan wa moet er dan met de bullets gebeuren
		//while (loadedBullets.size() > 0)
		//	this.unloadBullet(loadedBullets.iterator().next());
		super.terminate();
	}
	*/
}