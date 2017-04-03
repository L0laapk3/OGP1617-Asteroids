package asteroids.facade;

import java.util.Collection;
import java.util.Set;

import asteroids.exceptions.EntitiesOverlapException;
import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;
import asteroids.util.Vector2;

public class Facade implements asteroids.part2.facade.IFacade  {
	
	
	
	/**
	 * Helper function to throw an error when the ship variable is null.
	 * 
	 * @param  ship
	 * 		   The ship that has to be checked.
	 * @throws ModelException
	 *         The function throws a ModelException if the ship is null.
	 * @note   This is written in a defensive fashion.
	 */
	private void throwErrorIfInvalidShip(Ship ship) throws ModelException {
		if (isInvalidShip(ship))
			throw new ModelException(new NullPointerException("ship cannot be null."));
	}

	
	
	

	
	
	
	

	/**
	 * Helper function to see if the ship is invalid.
	 * 
	 * @param  ship
	 * 		   The ship that has to be checked.
	 * @return True if the ship is invalid, otherwise false.
	 */
	private boolean isInvalidShip(Ship ship) {
		return ship == null;
	}
	
	
	
	
	

	/**
	 * Create a new ship with a default position, velocity, radius and
	 * direction.
	 * 
	 * Result is a unit circle centered on <code>(0, 0)</code> facing right. Its
	 * speed is zero.
	 * @throws ModelException 
	 * 		   This function will never throw this error.
	 * @effect This new ship is initialized with 0 as its x and y coordinates and with a speed of zero. The radius of
	 * 		   this new ship is 10 and the ship is facing the right side of the screen.
	 * 		 | createShip(0, 0, 0, 0, 10, 0);
	 * @return The newly created ship.
	 */
	//not needed anymore
	/*@Override 
	@Deprecated
	public Ship createShip() {
		return new Ship();
	}*/

	
	
	/**
	 * 	
	 * Create a new non-null ship with the given position, velocity, radius,
	 * direction and mass.
	 * 
	 * The thruster of the new ship is initially inactive. The ship is not
	 * located in a world.
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
	 * @pre    The orientation parameter must be between 0 and 2*PI.
	 * @effect Creates a new ship.
	 * 		 | result = new Ship(x, y, xVelocity, yVelocity, radius, orientation)
	 * @post   The orientation of this new ship is equal to the given orientation.
	 * @post   The radius of this new ship is equal to the given radius.
	 * @return The newly created ship.
	 * @throws ModelException
	 * 		   If new Ship() throws an exception. see @effect
	 */
	@Override
	@Deprecated
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation) throws ModelException {
		try {
			return new Ship(x, y, xVelocity, yVelocity, radius, orientation);
		} catch (IllegalArgumentException ex) {
			throw new ModelException(ex);
		}
	}
	
	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction, double mass) throws ModelException {
		try {
			return new Ship(x, y, xVelocity, yVelocity, radius, direction, mass);;
		} catch (IllegalArgumentException ex) {
			throw new ModelException(ex);
		}
	}

	/**
	 * Return the position of <code>ship</code> as an array of length 2, with the
	 * x-coordinate at index 0 and the y-coordinate at index 1.
	 * 
	 * @param  ship
	 * 		   The ship of which the position should be returned.
	 * @effect Gets the ships position.
	 * 		 | result == ship.getPosition()
	 * @return an array containing 2 doubles, which are respectively the x and y coordinate of the ship.
	 * @throws ModelException
	 * 		   The ship parameter should not be null.
	 * @note   This is written in a defensive fashion.
	 */
	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		throwErrorIfInvalidShip(ship);
		return ship.getPosition().toArray();
	}

	
	
	
	
	
	
	

	/**
	 * Return the velocity of <code>ship</code> as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 * 
	 * @param  ship
	 * 		   The ship of which the velocity should be returned.
	 * @effect Gets the ships velocity.
	 * 		 | result == ship.getVelocity()
	 * @return an array containing 2 doubles, which are the velocities in respectively the x and y direction of the ship.
	 * @post   If the ship parameter is a null pointer, the method will return null.
	 * @note   This is written in a total fashion.
	 */
	@Override
	public double[] getShipVelocity(Ship ship) {
		if(isInvalidShip(ship))
			return null;
		return ship.getVelocity().toArray();
	}

	
	
	
	/**
	 * Return the orientation of <code>ship</code> (in radians).
	 * @param  ship
	 * 		   The ship of which the orientation should be returned.
	 * @pre    Ship parameter should not be null.
	 * @effect Gets the ships orientation.
	 *       | result == ship.getOrientation()
	 * @return A double with the orientation of the ship. This double will always be between 0 and 2*PI.
	 * @note   This is written in a nominal fashion.
	 */
	@Override
	public double getShipOrientation(Ship ship) {
		assert ship != null;
		return ship.getOrientation();
	}
	
	
	

	/**
	 * Return the radius of <code>ship</code>.
	 * 
	 * @param  ship
	 * 		   The ship of which the radius should be returned.
	 * @effect Gets the ships radius
	 *       | ship.getRadius()
	 * @return The radius of given ship.
	 * @throws ModelException
	 * 		   Ship parameter should not be null.
	 * @note   This is written in a defensive fashion.
	 */
	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		throwErrorIfInvalidShip(ship);
		return ship.getRadius();
	}
	
	
	


	
	
	
	
	

	/**
	 * Update <code>ship</code>'s position, assuming it moves <code>dt</code>
	 * seconds at its current velocity.
	 * 
	 * @param  ship
	 * 		   The ship that should move forward.
	 * @param  dt
	 * 		   The amount of time that the ship should move forward.
	 * @effect Moves the ship forward in time by given amount.
	 *       | ship.move(dt) 
	 * @throws ModelException
	 * 		   Ship parameter should not be null.
	 * @throws ModelException
	 * 		   If ship.move() throws an exception. See @effect
	 * @note   This is written in a defensive manner.
	 */
	@Override
	public void move(Ship ship, double dt) throws ModelException {
		throwErrorIfInvalidShip(ship);
		try {
			ship.move(dt);
		} catch (IllegalArgumentException ex) {
			throw new ModelException(ex);
		}
	}

	
	/**
	 * Update the direction of <code>ship</code> by adding <code>angle</code>
	 * (in radians) to its current direction. <code>angle</code> may be
	 * negative.
	 * 
	 * @param  angle
	 * 	       The angle that the ship has to turn.
	 * @param  ship
	 * 		   The ship that should be turned.
	 * @pre    The angle must not be Infinity or NaN.
	 * @pre    The ship must not be null.
	 * @effect Turns the ship by given angle.
	 *       | ship.turn(angle)
	 * @note   This is written in a nominal manner.
	 * 
	 */
	@Override
	public void turn(Ship ship, double angle) {
		assert ship != null;
		ship.turn(angle);
	}
	
	
	/**
	 * Update <code>ship</code>'s velocity based on its current velocity, its
	 * direction and the given <code>amount</code>.
	 * 
	 * @param  amount
	 * 		   The amount that the ship should accelerate.
	 * @param  ship
	 * 		   The ship that should be accelerated.
	 * @effect Thrusts the ship forward by the given amount.
	 * 		 | ship.thrust(amount)
	 * @post   if ship is null, then nothing will happen.
	 * @note   This is written in a total manner.
	 */
	@Override
	public void thrust(Ship ship, double amount) {
		if (ship == null)
			return;
		ship.thrust(amount);
	}

	
	
	
	
	
	
	
	
	

	/**
	 * Return the distance between <code>ship1</code> and <code>ship2</code>.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either ship should move such that both ships are adjacent. Note that the
	 * result must be negative if the ships overlap. The distance between a ship
	 * and itself is 0.
	 * 
	 * @param  ship1
	 * 		   The first ship to measure the distance from.
	 * @param  ship2
	 *         The second ship to measure the distance to.
	 * @effect Calculates the distance between two ships.
	 * 		 | result == Ship.getDistanceBetween(ship1, ship2)
	 * @throws ModelException
	 * 		   If Ship.getDistanceBetween() throws an exception. See @effect
	 */
	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		try {
			return Ship.getDistanceBetween(ship1, ship2);
		} catch (IllegalArgumentException ex) {
			throw new ModelException(ex);
		}
	}

	
	
	
	

	/**
	 * Check whether <code>ship1</code> and <code>ship2</code> overlap. A ship
	 * always overlaps with itself.
	 * 
	 * @param  ship1
	 * 		   The first ship to measure the distance from.
	 * @param  ship2
	 *         The second ship to measure the distance to.
	 * @effect Checks if the two given ships overlap
	 *       | result - Ship.overlap(ship1, ship2)
	 * @throws ModelException
	 * 		   if Ships.overlap() throws an exception. See @effect
	 */
	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
			return Ship.overlap(ship1, ship2);
		} catch (IllegalArgumentException ex) {
			throw new ModelException(ex);
		}
	}

	
	
	

	/**
	 * Return the number of seconds until the first collision between
	 * <code>ship1</code> and <code>ship2</code>, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 * 
	 * @param  ship1
	 * 		   The first ship to measure the distance from.
	 * @param  ship2
	 *         The second ship to measure the distance to.
	 * @effect Calculates the time to the next collision.
	 *       | result = Ship.getTimeToCollision(ship1, ship2)
	 * @throws ModelException
	 * 		   if Ships.getTimeToCollision() throws an exception. See @effect
	 */
	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try {
			return Ship.getTimeToCollision(ship1, ship2);
		} catch (IllegalArgumentException | EntitiesOverlapException ex) {
			throw new ModelException(ex);
		}
	}


	
	
	

	/**
	 * 
	 * 
	 * Return the first position where <code>ship1</code> and <code>ship2</code>
	 * collide, or <code>null</code> if they never collide. A ship never
	 * collides with itself.
	 * 
	 * The result of this method is either null or an array of length 2, where
	 * the element at index 0 represents the x-coordinate and the element at
	 * index 1 represents the y-coordinate.
	 * 
	 * @param  ship1
	 * 		   The first ship of the collision.
	 * @param  ship2
	 *         The second ship of the collision.
	 * @effect Calculates the collision position.
	 *       | result = Ship.getCollisionPosition(ship1, ship2)
	 * @throws ModelException
	 * 		   if Ships.getCollisionPosition() throws an exception. See @effect
	 */
	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
			Vector2 collisionPosition = Ship.getCollisionPosition(ship1, ship2);
			if (collisionPosition == null)
				return null;
			return collisionPosition.toArray();
		} catch (IllegalArgumentException | EntitiesOverlapException ex) {
			throw new ModelException(ex);
		}
	}
	


		
	/**
	 * Terminate <code>ship</code>.
	 */
	public void terminateShip(Ship ship) {
		ship.terminate();
	}

	/**
	 * Check whether <code>ship</code> is terminated.
	 */
	public boolean isTerminatedShip(Ship ship) {
		return ship.isTerminated();
	}

	/**
	 * Return the total mass of <code>ship</code> (i.e., including bullets
	 * loaded onto the ship).
	 */
	public double getShipMass(Ship ship) {
		return ship.getMass();		
	}

	/**
	 * Return the world of <code>ship</code>.
	 */
	public World getShipWorld(Ship ship) {
		return ship.getWorld();
	}

	/**
	 * Return whether <code>ship</code>'s thruster is active.
	 */
	public boolean isShipThrusterActive(Ship ship) {
		return ship.getThrusterstate();
	}

	/**
	 * Enables or disables <code>ship</code>'s thruster depending on the value
	 * of the parameter <code>active</code>.
	 */
	public void setThrusterActive(Ship ship, boolean active) {
		ship.tr
	}

	/**
	 * Return the acceleration of <code>ship</code>.
	 */
	public double getShipAcceleration(Ship ship) throws ModelException;

	/**
	 * This method is deprecated; you should not implement nor use it.
	 * 
	 * 
	 * @deprecated Since part 2 of the project. This behavior is now triggered
	 *             through the {@link #evolve(World, double, CollisionListener)}
	 *             method.
	 */
	@Override
	@Deprecated
	default void move(Ship ship, double dt) throws ModelException {
		// do nothing; this behaviour is now triggered by the evolve method.
	}

	/**************
	 * BULLET: Basic methods
	 *************/

	/**
	 * Create a new non-null bullet with the given position, velocity and
	 * radius,
	 * 
	 * The bullet is not located in a world nor loaded on a ship.
	 */
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException;

	/**
	 * Terminate <code>bullet</code>.
	 */
	public void terminateBullet(Bullet bullet) throws ModelException;

	/**
	 * Check whether <code>bullet</code> is terminated.
	 */
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException;

	/**
	 * Return the position of <code>ship</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	public double[] getBulletPosition(Bullet bullet) throws ModelException;

	/**
	 * Return the velocity of <code>ship</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	public double[] getBulletVelocity(Bullet bullet) throws ModelException;

	/**
	 * Return the radius of <code>bullet</code>.
	 */
	public double getBulletRadius(Bullet bullet) throws ModelException;

	/**
	 * Return the mass of <code>bullet</code>.
	 */
	public double getBulletMass(Bullet bullet) throws ModelException;

	/**
	 * Return the world in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned in a world, or
	 * if it is positioned on a ship.
	 */
	public World getBulletWorld(Bullet bullet) throws ModelException;

	/**
	 * Return the ship in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned on a ship.
	 */
	public Ship getBulletShip(Bullet bullet) throws ModelException;

	/**
	 * Return the ship that fired <code>bullet</code>.
	 */
	public Ship getBulletSource(Bullet bullet) throws ModelException;


	/**************
	 * WORLD: Basic methods
	 *************/

	/**
	 * Create a new world with the given <code>width</code> and
	 * <code>height</code>.
	 */
	public World createWorld(double width, double height) throws ModelException;

	/**
	 * Terminate <code>world</code>.
	 */
	public void terminateWorld(World world) throws ModelException;

	/**
	 * Check whether <code>world</code> is terminated.
	 */
	public boolean isTerminatedWorld(World world) throws ModelException;

	/**
	 * Return the size of <code>world</code> as an array containing the width,
	 * followed by the height.
	 */
	public double[] getWorldSize(World world) throws ModelException;

	/**
	 * Return all ships located within <code>world</code>.
	 */
	public Set<? extends Ship> getWorldShips(World world) throws ModelException;

	/**
	 * Return all bullets located in <code>world</code>.
	 */
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException;

	/**
	 * Add <code>ship</code> to <code>world</code>.
	 */
	public void addShipToWorld(World world, Ship ship) throws ModelException;

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeShipFromWorld(World world, Ship ship) throws ModelException;

	/**
	 * Add <code>bullet</code> to <code>world</code>.
	 */
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException;

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException;

	
	/**************
	 * SHIP: Methods related to loaded bullets
	 *************/

	/**
	 * Return the set of all bullets loaded on <code>ship</code>.
	 * 
	 * For students working alone, this method may return null.
	 */
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException;

	/**
	 * Return the number of bullets loaded on <code>ship</code>.
	 */
	public int getNbBulletsOnShip(Ship ship) throws ModelException;

	/**
	 * Load <code>bullet</code> on <code>ship</code>.
	 */
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException;

	/**
	 * Load <code>bullet</code> on <code>ship</code>.
	 * 
	 * For students working alone, this method must not do anything.
	 */
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException;

	/**
	 * Remove <code>ship</code> from <code>ship</code>.
	 */
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException;

	/**
	 * <code>ship</code> fires a bullet.
	 */
	public void fireBullet(Ship ship) throws ModelException;
	

	/******************
	 * COLLISIONS
	 **************/

	/**
	 * Return the shortest time in which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double getTimeCollisionBoundary(Object object) throws ModelException;

	/**
	 * Return the first position at which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double[] getPositionCollisionBoundary(Object object) throws ModelException;

	/**
	 * Return the shortest time in which the first entity will collide with the
	 * second entity.
	 */
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException;

	/**
	 * Return the first position at which the first entity will collide with the
	 * second entity.
	 */
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException;

	/**
	 * Return the time that must pass before a boundary collision or an entity
	 * collision will take place in the given world. Positive Infinity is
	 * returned if no collision will occur.
	 */
	public double getTimeNextCollision(World world) throws ModelException;

	/**
	 * Return the position of the first boundary collision or entity collision
	 * that will take place in the given world. Null is returned if no collision
	 * will occur.
	 */
	public double[] getPositionNextCollision(World world) throws ModelException;

	/**
	 * Advance <code>world</code> by <code>dt<code> seconds. 
	 * 
	 * To enable explosions within the UI, notify <code>collisionListener</code>
	 * whenever an entity collides with a boundary or another entity during this
	 * method. <code>collisionListener</code> may be null. If
	 * <code>collisionListener</code> is <code>null</code>, do not call its
	 * notify methods.
	 */
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException;

	/**
	 * Return the entity at the given <code>position</code> in the given
	 * <code>world</code>.
	 */
	public Object getEntityAt(World world, double x, double y) throws ModelException;

	/**
	 * Return a set of all the entities in the given world.
	 */
	public Set<? extends Object> getEntities(World world) throws ModelException;

}
