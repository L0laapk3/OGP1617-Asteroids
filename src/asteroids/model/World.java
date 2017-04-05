package asteroids.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import asteroids.exceptions.DoubleEntityException;
import asteroids.exceptions.EntitiesOverlapException;
import asteroids.exceptions.IllegalEntityException;
import asteroids.exceptions.NoWorldException;
import asteroids.exceptions.NotWithinBoundariesException;
import asteroids.part2.CollisionListener;
import asteroids.util.Vector2;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;


//TODO: DOOR ALLE FILES GAAN EN FUNCTIES ORDEREN ZODAT LUIE KUTASSISTEN DE FUNCTIES KAN VINDEN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//TODO: ZIEN DA ALLE VARIABELEN SETTERS EN GETTERS HEBBEN EN DA DIE OVERAL (!!) GEBRUIKT WORDNE
//TODO: OVERAL RAW????
//TODO: OVERAL THROWS KUUTT
//TODO: Ik denk da er iets fout is met de velocities overal; ze horen niet in dezelfde richting te gaan blijkbaar (rondvragen ofda waar is)
//TODO: Botsen met rand scherm werkt niet
//TODO: Alle andere schepen vliegen precies in dezelfde richting bij het spawnen, als ze botsen hebben ze precies een oneindige massa, of anders is de botscode fout ofzo voor nieuwe snelheden
//TODO: bullet-ship Explosion is in the wrong position O.o


public class World {
	
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
		if (isValidWidth(width)) {		//moet het this.checkValidWidth zijn omdat die finctie niet static is?
			this.width = width;
		} else if (width > UPPER_BOUND_WIDTH) {
			this.width = UPPER_BOUND_WIDTH;		
		} else {
			this.width = 0;
		}
		if (isValidHeight(height)) {
			this.height = height;
		} else if (width > UPPER_BOUND_HEIGHT) {
			this.height = UPPER_BOUND_HEIGHT;		
		} else {
			this.height = 0;
		}
	}
	
	//--------------MAX HEIGHT
	
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
	public static boolean isValidUpperBoundHeight(double height) {
		if ((height >= 0) && (height < Double.POSITIVE_INFINITY)) {
			return true;
		} else {
			return false;
		}
	}
	
	//----------------MAX WIDTH
	/**
	 * Variable referencing the lower bound of width
	 */
	private static double LOWER_BOUND_WIDTH = 0;
	
	/**
	 * Variable referencing the lower bound of width
	 */
	private static double UPPER_BOUND_WIDTH = Double.MAX_VALUE;
	
	/**
	 * Return the upper bound width of a world
	 */
	@Basic
	@Raw	
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
	public static boolean isValidUpperBoundWidth(double width) {
		if ((width >= 0) && (width < Double.POSITIVE_INFINITY)) {
			return true;
		} else {
			return false;
		}
	}

	
	
	
	
	
	
	
	
	
	//----------------HEIGHT
	
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
	
	
	
	
	
	
	
	
	
	
	
	//----------------WIDTH
	
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
	private static boolean isValidWidth(double width) {
		if (width >= LOWER_BOUND_WIDTH && width <= UPPER_BOUND_WIDTH) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	//---------------Terminating
	
	/**
	 * Variable reflecting whether or not the instance is terminated.
	 */
	private boolean isTerminated = false;
	
	
	/**
	 * Terminate this world.
	 *
	 * @post   The instance is terminated.
	 * @post   All the Entities in this world are no longer bound to this world.
	 * @post   Each of the entities of the instance is terminated.
	 */
	public void terminate() {
		if (!isTerminated()) {
			this.isTerminated = true;
			for(Entity entity : entities) {
				//do not terminate, just set world to null
				entity.setWorld(null);
			}
		}
	}
	
	/**
	 * Check whether this world is terminated.
	 */
	@Basic
	@Raw
	public boolean isTerminated() {
		return isTerminated;
	}
	
	
	
	
	
	
	
	
	
	
	//--------------basisfuncties entitySet
	
	/**
	 * Variable referencing a Set collecting all the ships and bullets
	 * in this world.
	 * 
	 * @invar Each entity registered in the referenced Set is not terminated 	//
	 * @invar The position of all entities must lay fully within the world.
	 * @invar Entites cannot overlap, with the exception of bullets loaded in their ships.
	 */
	private final Set<Entity> entities = new HashSet<Entity>();
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Adds an entity to the world.
	 * @param 	entity
	 * 		  	Entity to add to the world.
	 * @throws	DoubleEntityException
	 * 			An entity can only be ones in a world.
	 * @throws  NotWithinBoundariesException
	 * 		    An entity must lay completely in a world.
	 * @throws  EtitiesOverlapException
	 * 			A new entity can not overlap with an existing entity in that world
	 * @note    Defensive
	 */
	public void addEntity(Entity entity) throws DoubleEntityException, EntitiesOverlapException, NotWithinBoundariesException {
		
		if (entities.contains(entity)) {
			throw new DoubleEntityException();
		}
		
		for (Entity entityToCheck : entities) {
			if (Entity.overlap(entity, entityToCheck)												      //mag niet overlappen
					&& !((entity instanceof Bullet) && (((Bullet)entity).getParent() == entityToCheck))   //behalve als het een bullet geladen in dat ship
					&& !((entityToCheck instanceof Bullet) && (((Bullet)entityToCheck).getParent() == entity))) {
				throw new EntitiesOverlapException();
			}
		}
		
		double centerX = entity.getPosition().x;
		double centerY = entity.getPosition().y;
		
		//TODO: 99% SHIT???????,
		if (((centerX+entity.getRadius())>this.getWidth()) || ((centerX-entity.getRadius())<0) ||
				((centerY+entity.getRadius())>this.getHeight()) || ((centerY-entity.getRadius())<0)){
			throw new NotWithinBoundariesException();			
		} else {
			entities.add(entity);
			entity.setWorld(this);
		}
	}

	/**
	 * Removes an entity from the world.
	 * @param  entity
	 * 		   Entity to remove from the world.
	 * @throws IllegalEntityException
	 * 		   The given entity must be in entities.
	 * @note   Defensive
	 */
	public void removeEntity(Entity entity) throws IllegalEntityException {
		if (entities.contains(entity)) {
			entities.remove(entity);
			entity.setWorld(null);
		} else {
			throw new IllegalEntityException("An entity that did not exist in this world has been tried to remove from that world.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//---------------specifieke functies
	
	
	/**
	 * Gets entity from given position in world.
	 * @param  position
	 * 		   The position to scan for entities.
	 * @return If there is a ship on the given position, a ship will always be returned.
	 * @return If there are only non-ship entities on that position, a
	 * @return null if no entity is found.
	 */
	public Entity getEntityFromPosition(Vector2 position) {									
		if (!isValidHeight(position.y) || !isValidWidth(position.x))
			return null;
		
		for (Entity entity : entities)
			if (Vector2.equals(entity.getPosition(), position)) {
				if (entity instanceof Bullet && ((Bullet)entity).isLoadedInParent())
					return ((Bullet)entity).getParent();
				else
					return entity;
			}
		return null;
	}
	
	
	/**
	 * Gets all entities from the world.
	 * @return A Set with all the entities in the world.
	 */
	public Set<Entity> getAllEntities() {
		return new HashSet<Entity>(entities);							
	}
	
	/**
	 * Gets all ships from the world.
	 * @return A Set with all the entities in the world.
	 */
	public Set<Ship> getAllShips() {
		Set<Ship> ships = new HashSet<Ship>();
		for (Entity entity: entities)
			if (entity instanceof Ship)
				ships.add((Ship)entity);
		return ships;
	}
	
	/**
	 * Gets all bullets from the world.
	 * @return A Set with all the entities in the world.
	 */
	public Set<Bullet> getAllBullets() {
		Set<Bullet> bullets = new HashSet<Bullet>();
		for (Entity entity: entities)
			if (entity instanceof Bullet)
				bullets.add((Bullet)entity);
		return bullets;
	}
	
	
	/**
	 * Gets all entities on which collision physics has to apply on.
	 * @return A Set of entites on which collision physics has to apply on.
	 */
	public Set<Entity> getAllEntitiesWithCollision() {
		Set<Entity> entitiesWithCollision = new HashSet<Entity>();
		for (Entity entity: entities)
			if (!(entity instanceof Bullet) || !((Bullet)entity).isLoadedInParent())
				entitiesWithCollision.add(entity);
		return entitiesWithCollision;
	}
	

	
	/**
	 * Gets all loaded bullets. The opposite of getAllEntitiesWithCollision.
	 * @return A Set of all the loaded bullets in this world.
	 */
	public Set<Bullet> getAllLoadedBullets() {
		Set<Bullet> entitiesWithoutCollision = new HashSet<Bullet>();
		for (Entity entity: entities)
			if ((entity instanceof Bullet) && ((Bullet)entity).isLoadedInParent())
				entitiesWithoutCollision.add((Bullet)entity);
		return entitiesWithoutCollision;
	}
	
	
	
	
	
	
	
	
	
	
	
	//----------------Advancing time
	
	
	public CollisionInformation getNextCollision(Set<Entity> entities) throws EntitiesOverlapException, NoWorldException {
		double earliestCollisionTime = Double.POSITIVE_INFINITY;
		Entity collisionFirstEntity = null;
		Entity collisionSecondEntity = null;
		//if firstEntity is not null but secondEntity is, there is a wall collision
		
		Iterator<Entity> it = entities.iterator();
		Set<Entity> past = new HashSet<Entity>();
		while (it.hasNext()) {
			
			Entity first = it.next();
			
			//detect wall collisions
			double collisionTime = first.getTimeToWallCollision();
			if (earliestCollisionTime > collisionTime) {
				earliestCollisionTime  = collisionTime;
				collisionFirstEntity = first;
				collisionSecondEntity = null;
			}
			for(Entity second : past) {
				//detect entity collisions
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
	 * @param Dt
	 * 		  Time to move and accelerate all entities with.
	 * @param entitiesWithCollision
	 * 		  All entities that need to be moved and accelerated (usually all entities with collision.)
	 */
	void doTime(double Dt, Set<Entity> entitiesWithCollision) {
		for (Entity entity: entitiesWithCollision) {
			entity.move(Dt);
			entity.accelerate(Dt);
		}
	}
	
	
	
	
	/**
	 * Simulates the world for time Dt.
	 * @param Dt
	 * 		  The time to simulate the world for.
	 * @post  Simulates the world for Dt seconds.
	 */
	public void evolve(Double Dt, CollisionListener collisionListener) throws NoWorldException {
		
		try {
		
		//do not simulate collision physics for loaded bullets.
		
		//TODO: SOMS BLIJFT HIJ VASTZITTEN IN EEN LOOP, IK VERMOED DAT TIMETONEXTCOLLISION 0 IS HEEL DE TIJD OFZO?? DOOR AFRONDING/ NEE DOOR FOUTE VELOCITY NA BOTSEN
		do {
			Set<Entity> entitiesWithCollision = getAllEntitiesWithCollision();
			CollisionInformation collInfo = getNextCollision(entitiesWithCollision);

			
			if (collInfo.timeToCollision < Dt) { //collision happens before end Dt
				doTime(collInfo.timeToCollision, entitiesWithCollision);
				Dt -= collInfo.timeToCollision;
				
				if (collInfo.isWallCollision()) { //wall collision
					if (collisionListener != null) {
						Vector2 collPos = collInfo.firstEntity.getWallCollisionPosition(0);
						assert(collPos != null);
						collisionListener.boundaryCollision(collInfo.firstEntity, collPos.x, collPos.y);
					}
					collInfo.firstEntity.collideWithWall();
				} else { //entity collision
					if (collisionListener != null) {
						Vector2 collPos = Entity.getCollisionPosition(collInfo.firstEntity, collInfo.secondEntity, 0);
						assert(collPos != null);
						collisionListener.objectCollision(collInfo.firstEntity, collInfo.secondEntity, collPos.x, collPos.y);
					}
					collideEntities(collInfo.firstEntity, collInfo.secondEntity);
				}
			} else {
				doTime(Dt, entitiesWithCollision);
				Dt = (double)0;
			}
		} while (Dt > 0);
		
		
		//set loaded bullets' location to their parent.
		for (Bullet loadedBullet : getAllLoadedBullets()) {
			loadedBullet.setPosition(loadedBullet.getParent().getPosition());
		}
		
		
		} catch (EntitiesOverlapException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	
	/**
	 * Collides the two entities and calls the proper handlers depending on the collision type.
	 * @invar Both entities must be in the same world. (not null)
	 * @param first
	 * 	      This is the first entity to collide.
	 * @param collisionSecondEntity
	 *        This is the second entity to collide.
	 */
	private void collideEntities(Entity first, Entity second) {
		Collisions.collide(first, second);
	}

	
	/**
	 * Searches if given entity is colliding with another entity.
	 * @param entity
	 * @return The entity that it overlaps with if there is one.
	 * @return Null if there is no entity that overlaps with the given entity.
	 */
	@Raw
	public Entity findOverlap(Entity entity) {
		for (Entity other : entities)
				if (Entity.overlap(entity, other) && (entity != other))
					return other;
		return null;
	}
	
}