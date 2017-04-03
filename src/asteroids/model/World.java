package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import asteroids.exceptions.*;
import asteroids.util.*;
import be.kuleuven.cs.som.annotate.*;


//TODO: DOOR ALLE FILES GAAN EN FUNCTIES ORDEREN ZODAT LUIE KUTASSISTEN DE FUNCTIES KAN VINDEN



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
	
	
	
	
	
	
	
	
	
	
	//--------------basisfuncties entitylist
	
	/**
	 * Variable referencing a list collecting all the ships and bullets
	 * in this world.
	 * 
	 * @invar Each entity registered in the referenced list is not terminated 	//
	 * @invar The position of all entities must lay fully within the world.
	 * @invar Entites cannot overlap, with the exception of bullets loaded in their ships.
	 */
	private final List<Entity> entities = new ArrayList<Entity>();
	
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
	 * @return The entities on given position if there is an entity on that position.
	 * @return null if no entity is found.
	 */
	public List<Entity> getEntityFromPosition(Vector2 position) {									
		if (!isValidHeight(position.y) || !isValidWidth(position.x))
			return null;
		List<Entity> entitiesFound = new ArrayList<Entity>();
		for (Entity entity : entities)
			if (Vector2.equals(entity.getPosition(), position))
				entitiesFound.add(entity);
		if (entitiesFound.isEmpty()){
			return null;
		} else {
			return entitiesFound;
		}
	}
	
	
	/**
	 * Gets all entities from the world.
	 * @return A list with all the entities in the world.
	 */
	public List<Entity> getAllEntities() {
		return new ArrayList<Entity>(entities);							
	}
	
	/**
	 * Gets all ships from the world.
	 * @return A list with all the entities in the world.
	 */
	public List<Ship> getAllShips() {
		List<Ship> ships = new ArrayList<Ship>();
		for (Entity entity: entities)
			if (entity instanceof Ship)
				ships.add((Ship)entity);
		return ships;
	}
	
	/**
	 * Gets all bullets from the world.
	 * @return A list with all the entities in the world.
	 */
	public List<Bullet> getAllBullets() {
		List<Bullet> bullets = new ArrayList<Bullet>();
		for (Entity entity: entities)
			if (entity instanceof Bullet)
				bullets.add((Bullet)entity);
		return bullets;
	}
	
	
	/**
	 * Gets all entities on which collision physics has to apply on.
	 * @return A list of entites on which collision physics has to apply on.
	 */
	public List<Entity> getAllEntitiesWithCollision() {
		List<Entity> entitiesWithCollision = new ArrayList<Entity>();
		for (Entity entity: entities)
			if (!(entity instanceof Bullet) || !((Bullet)entity).isLoadedInParent())
				entitiesWithCollision.add(entity);
		return entitiesWithCollision;
	}
	

	
	/**
	 * Gets all loaded bullets. The opposite of getAllEntitiesWithCollision.
	 * @return A list of all the loaded bullets in this world.
	 */
	public List<Bullet> getAllLoadedBullets() {
		List<Bullet> entitiesWithoutCollision = new ArrayList<Bullet>();
		for (Entity entity: entities)
			if ((entity instanceof Bullet) && ((Bullet)entity).isLoadedInParent())
				entitiesWithoutCollision.add((Bullet)entity);
		return entitiesWithoutCollision;
	}
	
	
	
	
	
	
	
	
	
	
	
	//----------------Advancing time
	
	
	
	
	/**
	 * Simulates the world for time dt in a deterministic way.
	 * @param Dt
	 * 		  The time to simulate the world for.
	 */
	public void advanceTime(Double Dt) {
		do {
			//do not simulate collision physics for loaded bullets.
			List<Entity> entitiesWithCollision = getAllEntitiesWithCollision();
			
			double earliestCollisionTime = Double.POSITIVE_INFINITY;
			Entity collisionFirstEntity = null;
			Entity collisionSecondEntity = null;
			
			//if firstentity is not null but secondentity is, there is a wall collision
			try {
				for (int i = 0; i < entitiesWithCollision.size(); i++) {
					
					
					//detect wall collisions
					double collisionTime = entitiesWithCollision.get(i).getTimeToWallCollision();
					if (earliestCollisionTime > collisionTime) {
						earliestCollisionTime  = collisionTime;
						collisionFirstEntity = entitiesWithCollision.get(i);
						collisionSecondEntity = null;
					}
					for(int j = i + 1; j < entitiesWithCollision.size(); j++) {
						//detect entity collisions
						double collisionTime1 = Entity.getTimeToCollision(entitiesWithCollision.get(i), entitiesWithCollision.get(j));
						if (earliestCollisionTime > collisionTime1) {
							earliestCollisionTime = collisionTime1;
							collisionFirstEntity = entitiesWithCollision.get(i);
							collisionSecondEntity = entitiesWithCollision.get(j);
						}
					}
				}
			} catch (EntitiesOverlapException ex) { //should never happen..
				throw new RuntimeException(ex); //FATAL ERROR
				//TODO: opt einde zie ofda dees klopt
			}
			
			
			if (earliestCollisionTime < Dt) { //collision happens before end Dt
				for (Entity entity: entitiesWithCollision)
					entity.move(earliestCollisionTime);
				Dt -= earliestCollisionTime;
				
				if (collisionSecondEntity == null) //wall collision
					collisionFirstEntity.collideWithWall();
				else //entity collision
					collideEntities(collisionFirstEntity, collisionSecondEntity);
			} else
				for (Entity entity: entitiesWithCollision)
					entity.move(Dt);
				Dt = (double)0;
		} while (Dt > 0);
		
		
		//set loaded bullets' location to their parent.
		for (Bullet loadedBullet : getAllLoadedBullets())
			loadedBullet.setPosition(loadedBullet.getParent().getPosition());
	}
	
	
	/**
	 * Collides the two entities and calls the proper handlers depending on the collision type.
	 * 
	 * @param collisionFirstEntity
	 * @param collisionSecondEntity
	 * 	      These are the two entities to collide.
	 */
	private void collideEntities(Entity collisionFirstEntity, Entity collisionSecondEntity) {
		
		if (collisionFirstEntity instanceof Ship && collisionSecondEntity instanceof Ship) {
			Ship.collideWithSameType((Ship)collisionFirstEntity, (Ship)collisionSecondEntity);
			
		} else if (collisionFirstEntity instanceof Bullet && collisionSecondEntity instanceof Bullet) {
			Bullet.collideWithSameType((Bullet)collisionFirstEntity, (Bullet)collisionSecondEntity);
			
		} else {
			Ship ship;
			Bullet bullet;
			if (collisionFirstEntity instanceof Ship) {
				ship = (Ship)collisionFirstEntity;
				bullet = (Bullet)collisionSecondEntity;
			} else {
				ship = (Ship)collisionSecondEntity;
				bullet = (Bullet)collisionFirstEntity;
			}
			bullet.hit(ship);
		}
	}
	
}