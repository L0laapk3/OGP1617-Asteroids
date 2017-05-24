package asteroids.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

import asteroids.exceptions.DoubleEntityException;
import asteroids.exceptions.EntitiesOverlapException;
import asteroids.exceptions.IllegalEntityException;
import asteroids.exceptions.InvalidTimeException;
import asteroids.exceptions.NegativeTimeException;
import asteroids.exceptions.NoWorldException;
import asteroids.exceptions.NotWithinBoundariesException;
import asteroids.part2.CollisionListener;
import asteroids.util.OGUtil;
import asteroids.util.Vector2;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class to define worlds.
 * 
 * @effect   Instance
 * 
 * @invar    Each entity registered in the referenced Set is not terminated 
 * @invar 	 The position of all entities must lay fully within the world.
 * @invar 	 Entities cannot overlap, with the exception of bullets loaded in their ships.
 * @invar 	 Both entities must be in the same world for colliding. (not null)
 * 
 * @version  1.0
 * @author   Kris Keersmaekers
 * @author   Rik Pauwels
 */
public class World extends Instance {

	/**
	 * Initialize a new world with given width and given height. 
	 * 
	 * @param 	width
	 * 			The width of the new world.
	 * @param	height
	 * 			The height of the new world.
	 * @post	If the given height is less than LOWER_BOUND_HEIGHT then new.height equals LOWER_BOUND_HEIGHT.
	 * @post	If the given height is greater than UPPER_BOUND_HEIGHT then new.height equals UPPER_BOUND_HIEGHT.
	 * @post	If the given width is less than LOWER_BOUND_WIDTH then new.width equals LOWER_BOUND_WIDTH.
	 * @post	If the given width is greater than UPPER_BOUND_WIDTH then new.width equals UPPER_BOUND_WIDTH.
	 *
	 * @note	This constructor is written in a total manner.
	 */
	public World(double width, double height) {
		if (isValidWidth(width)) {
			this.width = width;
		} else if (width > UPPER_BOUND_WIDTH) {
			this.width = UPPER_BOUND_WIDTH;
		} else {
			this.width = 0;
		}
		if (isValidHeight(height)) {
			this.height = height;
		} else if (height > UPPER_BOUND_HEIGHT) {
			this.height = UPPER_BOUND_HEIGHT;
		} else {
			this.height = 0;
		}
	}


	/**
	 * Variable referencing the lower bound of height 
	 */
	private static double LOWER_BOUND_HEIGHT = 0;

	/**
	 * Variable referencing the upper bound of height 
	 */
	private static double UPPER_BOUND_HEIGHT = Double.MAX_VALUE;

	/**
	 * Return the upper bound height of a world.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getUpperBoundHeight() {
		return UPPER_BOUND_HEIGHT;
	}

	/**
	 * Set the upper bound of height of the given height.
	 * 
	 * @param 	height
	 * 			The new upper bound height
	 * @post	The upper bound of the height is the same as 
	 * 			the given height
	 */
	@Basic
	@Raw
	public static void setUpperBoundHeight(double height) {
		if (isValidUpperBoundHeight(height)) {
			UPPER_BOUND_HEIGHT = height;
		}
	}

	/**
	 * Check whether the given height is a valid upper bound of height.
	 * 
	 * @param 	height
	 * 			The height to check.
	 * @return	True if and only if the given height is greater or equal than zero 
	 * 			and less than infinity.
	 */
	@Raw
	@Basic
	public static boolean isValidUpperBoundHeight(double height) {
		if ((height >= 0) && (height < Double.POSITIVE_INFINITY)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Variable referencing the lower bound of width.
	 */
	private static double LOWER_BOUND_WIDTH = 0;

	/**
	 * Variable referencing the upper bound of width.
	 */
	private static double UPPER_BOUND_WIDTH = Double.MAX_VALUE;

	/**
	 * Return the upper bound width of a world.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getUpperBoundWidth() {
		return UPPER_BOUND_WIDTH;
	}

	/**
	 * Set the upper bound of height of the given width.
	 * 
	 * @param 	width
	 * 			The new upper bound width
	 * @post	The upper bound of the width is the same as 
	 * 			the given width
	 */
	@Basic
	@Raw
	public static void setUpperBoundWidth(double width) {
		if (isValidUpperBoundWidth(width)) {
			UPPER_BOUND_WIDTH = width;
		}
	}

	/**
	 * Check whether the given width is a valid upper bound of width.
	 * 
	 * @param 	width
	 * 			The width to check.
	 * @return	True if and only if the given width is greater or equal than zero 
	 * 			and less than infinity.
	 */
	@Raw
	@Basic
	public static boolean isValidUpperBoundWidth(double width) {
		if ((width >= 0) && (width < Double.POSITIVE_INFINITY)) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Variable referencing the height of the given world
	 */
	private final double height;

	/**
	 * Return the height of this world.
	 */
	@Basic
	@Raw
	public double getHeight() {
		return this.height;
	}

	/**
	 * Check whether the given height is a valid height.
	 * 
	 * @param 	height
	 * 			The height to check.
	 * @return	If the given height is between LOWER_BOUND_HEIGHT and UPPER_BOUND_HEIGHT
	 * 			the function returns true.
	 */
	@Raw
	public static boolean isValidHeight(double height) {
		if (height >= LOWER_BOUND_HEIGHT && height <= UPPER_BOUND_HEIGHT) {
			return true;
		}
		return false;
	}


	/**
	 * Variable referencing the width of the given world
	 */
	private final double width;

	/**
	 * Return the width of this world.
	 */
	@Basic
	@Raw
	public double getWidth() {
		return this.width;
	}

	/**
	 * Check whether the given width is a valid width.
	 * 
	 * @param 	width
	 * 			The width to check.
	 * @return	If the given width is between LOWER_BOUND_WIDTH and UPPER_BOUND_WIDTH
	 * 			the function returns true.
	 */
	@Raw
	@Basic
	private static boolean isValidWidth(double width) {
		if (width >= LOWER_BOUND_WIDTH && width <= UPPER_BOUND_WIDTH) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the world's upper boundaries.
	 * 
	 * @return new Vector2(getWidth(), getHeight());
	 */
	@Raw
	@Basic
	public Vector2 getMaxBounds() {
		return new Vector2(this.getWidth(), this.getHeight());
	}


	/**
	 * Terminate this world.
	 *
	 * @effect super.terminate()
	 * @post   All the Entities in this world are no longer bound to this world.
	 */
	@Override
	public void terminate() {
		while (!entities.isEmpty()) {
			Iterator<Entity> it = entities.iterator();
			Entity entity = it.next();
			// do not terminate, just set world to null
			this.removeEntity(entity);
		}
		super.terminate();
	}


	/**
	 * Variable referencing a Set collecting all the ships and bullets
	 * in this world.
	 * 
	 * @invar Each entity registered in the referenced Set is not terminated 
	 * @invar The position of all entities must lay fully within the world.
	 * @invar Entities cannot overlap, with the exception of bullets loaded in their ships.
	 */
	private final Set<Entity> entities = new HashSet<Entity>();

	/**
	 * Adds an entity to the world.
	 * 
	 * @param 	entity
	 * 		  	Entity to add to the world.
	 * @post 	If the given entity does not overlap with one of the entities who is already in the given world and it lays fully in the world then its added to the given world.
	 * @post	If the entity is already in a world then the entity will remain in this world
	 * @throws	DoubleEntityException
	 * 			An entity can only be ones in a world.
	 * @throws  NotWithinBoundariesException
	 * 		    An entity must lay completely in a world.
	 * @throws  EtitiesOverlapException
	 * 			A new entity can not overlap with an existing entity in that world
	 * @note    Defensive
	 */
	@Raw
	public void addEntity(Entity entity) throws DoubleEntityException, EntitiesOverlapException, NotWithinBoundariesException, IllegalEntityException {
				
		if (entity == null) {
			throw new IllegalEntityException();
		}
		
		if (entities.contains(entity)) {
			throw new DoubleEntityException();
		}
		
		if (entity.getWorld() != null) {
			return;
		}

		for (Entity entityToCheck : entities) {
			if (Entity.overlap(entity, entityToCheck) // mag niet overlappen
					&& !((entity instanceof Bullet) && (((Bullet) entity).getMotherShip() == entityToCheck)) // behalve als het een bullet geladen in dat ship
					&& !((entityToCheck instanceof Bullet) && (((Bullet) entityToCheck).getMotherShip() == entity)) // behalve als het een bullet geladen in dat ship
					&& !((entity instanceof Bullet) && (entityToCheck instanceof Bullet) && ((Bullet) entity).isLoadedInMotherShip() // behalve als de 2 bullets in dezelfde parent geladen zijn
							&& ((Bullet) entityToCheck).isLoadedInMotherShip() && (((Bullet) entity).getMotherShip() == ((Bullet) entityToCheck).getMotherShip()))) {
				if (!(((entity instanceof Bullet) && (entityToCheck instanceof Bullet)) && (((Bullet) entity).getMotherShip() == ((Bullet) entityToCheck).getMotherShip())
						&& !isNullOrTerminated(((Bullet) entity).getMotherShip()))) {
					throw new EntitiesOverlapException();
				}

			}
		}

		double centerX = entity.getPosition().x;
		double centerY = entity.getPosition().y;

		if (((centerX + entity.getRadius() * 0.99) > this.getWidth()) || ((centerX - entity.getRadius() * 0.99) < 0) || ((centerY + entity.getRadius() * 0.99) > this.getHeight())
				|| ((centerY - entity.getRadius() * 0.99) < 0)) {
			throw new NotWithinBoundariesException();
		} else {
			entities.add(entity);
			entity.setWorld(this);
		}
	}

	/**
	 * Removes an entity from the world.
	 * 
	 * @param  entity
	 * 		   Entity to remove from the world.
	 * @throws IllegalEntityException
	 * 		   The given entity must be in entities.
	 * @note   Defensive
	 */
	@Raw
	public void removeEntity(Entity entity) throws IllegalEntityException {
		
		if (entities.contains(entity)) {
			entities.remove(entity);
			
			entity.setWorld(null);
		} else {
			throw new IllegalEntityException("An entity that did not exist in this world has been tried to remove from that world.");
		}
	}

	/**
	 * Gets entity from given position in world.
	 * 
	 * @param  position
	 * 		   The position to scan for entities.
	 * @return If there is a ship on the given position, a ship will always be returned.
	 * @return If there are only non-ship entities on that position, one of them will be returned.
	 * @return null if no entity is found.
	 */
	public Entity getEntityFromPosition(Vector2 position) {
		if (!isValidHeight(position.y) || !isValidWidth(position.x))
			return null;

		for (Entity entity : entities)
			if (Vector2.equals(entity.getPosition(), position)) {
				if (entity instanceof Bullet && ((Bullet) entity).isLoadedInMotherShip())
					return ((Bullet) entity).getMotherShip();
				else
					return entity;
			}
		return null;
	}

	
	/**
	 * Gets all objects of type c from the world.
	 * 
	 * @param  c
	 * 		   The class of which all instances in this world have to be returned of. If not specified, will return all entities in this world.
	 * @param  filter
	 * 		   Filter function. If not specified, will return all entities that are of type c.
	 * @Return with all the entities of type c in the world.
	 */
	@Raw public Set<Entity> getAllEntities() { return getAllEntities(Entity.class, x -> true); }
	@Raw public Set<Entity> getAllEntities(Predicate<? super Entity> filter) { return getAllEntities(Entity.class, filter); }
	@Raw public <T extends Entity> Set<T> getAllEntities(Class<T> c) { return getAllEntities(c, x -> true); }
	@SuppressWarnings("unchecked")
	@Raw public <T extends Entity> Set<T> getAllEntities(Class<T> c, Predicate<? super T> filter) {
		Set<T> filtered = new HashSet<T>();
		for (Entity entity : entities)
			if (c.isInstance(entity) && filter.test((T)entity))
				filtered.add((T)entity);
		return filtered;
	}

	
	/**
	 * Gets all ships from the world.
	 * 
	 * @return A set with all the ships in the world.
	 */
	@Raw
	public Set<Ship> getAllShips() { return getAllEntities(Ship.class); }

	/**
	 * Gets all bullets from the world.
	 * 
	 * @return A Set with all the entities in the world.
	 */
	@Raw
	public Set<Bullet> getAllBullets() { return getAllEntities(Bullet.class); }

	/**
	 * Gets all bullets with collision from the world.
	 * 
	 * @return A Set with all the entities in the world.
	 */
	@Raw
	public Set<Bullet> getAllBulletsWithCollision() { return getAllEntities(Bullet.class, bullet -> bullet.hasCollision());	}

	/**
	 * Gets all entities on which collision physics has to apply on.
	 * 
	 * @return A Set of entities on which collision physics has to apply on.
	 */
	@Raw
	public Set<Entity> getAllEntitiesWithCollision() { return getAllEntities(entity -> entity.hasCollision()); }

	/**
	 * Gets all entities without collision. The opposite of getAllEntitiesWithCollision.
	 * 
	 * @return A Set of all the loaded bullets in this world.
	 */
	@Raw
	public Set<Entity> getAllEntitiesWithoutCollision() { return getAllEntities(entity -> !entity.hasCollision()); }

	/**
	 * Gets all loaded bullets.
	 * 
	 * @return A Set of all the loaded bullets in this world.
	 */
	@Raw
	public Set<Bullet> getAllLoadedBullets() { return getAllEntities(Bullet.class, bullet -> bullet.isLoadedInMotherShip()); }


	
	/**
	 * This function gets the next collision.
	 * 
	 * @param   entities
	 * 		    The entities who has to be checked.
	 * @return  new collisionInformation containing all the collisioninformation.
	 * @throws  EntitiesOverlapException
	 * 			Entities may not overlap beforehand.
	 * @throws  NoWorldException
	 * 			Entities must lay in a world.
	 * @note    For obvious reasons, if you want a correct result, only entities that are elligable for collision should be in the entities set parameter.
	 */
	public CollisionInformation getNextCollision(Set<Entity> entities) throws EntitiesOverlapException, NoWorldException {
		double earliestCollisionTime = Double.POSITIVE_INFINITY;
		Entity collisionFirstEntity = null;
		Entity collisionSecondEntity = null;
		// if firstEntity is not null but secondEntity is, there is a wall collision

		Iterator<Entity> it = entities.iterator();
		Set<Entity> past = new HashSet<Entity>();
		while (it.hasNext()) {			
			
			Entity first = it.next();
			
			//TODO: dees mag weg (als da fatsoenlijk getest is)
			if ((first instanceof Bullet) && (((Bullet)first).isLoadedInMotherShip())) {
				throw new AssertionError("bullet cannot collide with the ship that it is loaded in..");
			}

			// detect wall collisions
			double collisionTime = first.getTimeToWallCollision();
			
			if (earliestCollisionTime > collisionTime) {
				earliestCollisionTime = collisionTime;
				collisionFirstEntity = first;
				collisionSecondEntity = null;
			}
			for (Entity second : past) {
				// detect entity collisions
				double collisionTime1 = Entity.getTimeToCollision(first, second);
				if (earliestCollisionTime > collisionTime1) {
					earliestCollisionTime = collisionTime1;
					collisionFirstEntity = first;
					collisionSecondEntity = second;
				}
			}

			past.add(first);
		}

		return new CollisionInformation(collisionFirstEntity, collisionSecondEntity, earliestCollisionTime);
	}

	/**
	 * Moves and then accelerates all entities.
	 * 
	 * @param Dt
	 * 		  Time to move and accelerate all entities with.
	 * @param entitiesWithCollision
	 * 		  All entities that need to be moved and accelerated (usually all entities with collision).
	 */
	@Raw
	void doTime(double Dt, Set<Entity> entitiesWithCollision) {
		for (Entity entity : entitiesWithCollision) {
			entity.move(Dt);
			if (entity instanceof AdvancedEntity)
				((AdvancedEntity)entity).accelerate(Dt);
		}
	}

	/**
	 * Simulates the world for time Dt.
	 * 
	 * @param  Dt
	 * 		   The time to simulate the world for.
	 * @effect this.evolve(Dt, null);
	 * @throws IllegalArgumentException
	 * 		   The time of the action should not be infinite.
	 * @throws IllegalArgumentException
	 * 		   The time of the action should not be NaN.
	 * @throws InvalidTimeException 
	 * @note   This is written in a defensive manner.
	 */
	public void evolve(double Dt) throws IllegalArgumentException, InvalidTimeException {
		
		this.evolve(Dt, null);
	}

	/**
	 * Simulates the world for time Dt.
	 * 
	 * @param  dt
	 * 		   The time to simulate the world for.
	 * @param  CollisionListener.
	 * 		   CollisionListener containing functions to callback where to render the collision particles.
	 * @throws InvalidTimeException
	 * 		   If the time parameter is not a positive double.
	 * @post   Simulates the world for Dt seconds.
	 * @post   if CollisionListener is not null, collisionListener.boundaryCollision will be called on wall collisions.
	 * @post   if CollisionListener is not null, collisionListener.objectCollision will be called on entity collisions.
	 * @note   defensive
	 */
	public void evolve(double dt, CollisionListener collisionListener) throws InvalidTimeException {
		
		try {
			OGUtil.throwErrorIfInvalidNumbers(dt);
		} catch (IllegalArgumentException ex) {
			throw new InvalidTimeException(ex);
		}
		
		if (!OGUtil.isValidDeltaTime(dt))
			throw new NegativeTimeException();
		
		double originalDt = dt;
		
		try {

			// do not simulate collision physics for loaded bullets.

			do {
				Set<Entity> entitiesWithCollision = getAllEntitiesWithCollision();
				CollisionInformation collInfo = getNextCollision(entitiesWithCollision);

				if (collInfo.timeToCollision < dt) { // collision happens before end Dt
					if (!collInfo.isWallCollision())
						OGUtil.println("explosion prediction: " + Entity.getCollisionPosition(collInfo.firstEntity, collInfo.secondEntity, collInfo.timeToCollision));

					doTime(collInfo.timeToCollision, entitiesWithCollision);
					dt -= collInfo.timeToCollision;
					if (collInfo.isWallCollision()) { // wall collision
						if (collisionListener != null) {
							Vector2 collPos = collInfo.firstEntity.getWallCollisionPosition(0);
							assert (collPos != null);
							collisionListener.boundaryCollision(collInfo.firstEntity, collPos.x, collPos.y);
						};
						OGUtil.println("MASS: " + collInfo.firstEntity + " " + collInfo.firstEntity.getMass());
						collInfo.firstEntity.collideWithWall();
					} else { // entity collision
						if (collisionListener != null) {
							Vector2 collPos = Entity.getCollisionPosition(collInfo.firstEntity, collInfo.secondEntity, 0);
							OGUtil.println("explosion: " + collPos);
							assert (collPos != null);
							//explosie gebeurt ook als ge uw eigen kogel opraapt, lijkt erop dat het een fout in src-provided is.
							collisionListener.objectCollision(collInfo.firstEntity, collInfo.secondEntity, collPos.x, collPos.y);
						}
						Collisions.collide(collInfo.firstEntity, collInfo.secondEntity);
					}
				} else {
					doTime(dt, entitiesWithCollision);
					dt = 0;
				}
			} while (dt > 0);

			// run evolve script on all entities
			for (Entity entity : entities)
				entity.evolve(originalDt);
			

		} catch (EntitiesOverlapException | NoWorldException ex) { // this should never happen, not user input fault if it happens but code fault.
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Searches if given entity is colliding with another entity.
	 * 
	 * @param entity
	 * @return The entity that it overlaps with if there is one.
	 * @return Null if there is no entity that overlaps with the given entity.
	 */
	@Raw
	public Entity findOverlap(Entity entity) {
		for (Entity other : this.getAllEntitiesWithCollision())
			if (Entity.overlap(entity, other) && (entity != other))
				return other;
		return null;
	}

}