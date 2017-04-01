package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import asteroids.exceptions.DoubleEntityException;
import asteroids.exceptions.IllegalEntityException;
import asteroids.util.Vector2;
import be.kuleuven.cs.som.annotate.*;

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
		} else if (width>UPPER_BOUND_WIDTH) {
			this.width=UPPER_BOUND_WIDTH;		
		} else {
			this.width=0;
		}
		if (isValidHeight(height)) {
			this.height = height;
		} else if (width>UPPER_BOUND_HEIGHT) {
			this.height=UPPER_BOUND_HEIGHT;		
		} else {
			this.height=0;
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
		if ((height>=0) && (height<Double.POSITIVE_INFINITY)) {
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
		if ((width>=0) && (width<Double.POSITIVE_INFINITY)) {
			return true;
		} else {
			return false;
		}
	}

	
	//----------------HEIGHT
	
	/**
	 * Variable referencing the height of the given world
	 */
	private double height;
	
	/**
	 * Return the height of this world.
	 */
	@Basic
	@Raw
	public double getHeight(){
		return this.getHeight();
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
	private double width;
	
	/**
	 * Return the width of this world.
	 */
	@Basic
	@Raw
	public double getWidth(){
		return this.getWidth();
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
	 * Variable reflecting whether or not this world is terminated.
	 */
	private boolean isTerminated = false;
	
	
	/**
	 * Terminate this world.
	 *
	 * @post   This world is terminated.
	 * @post   Each of the entities of this world is terminated.	//TODO alle entities daarin terminaten
	 */
	public void terminate() {		//person line 49
		if (!isTerminated()) {
			this.isTerminated = true;
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
	 * @invar Each entity registered in the referenced list is not terminated 	//TODO: dit in orde brengen
	 */
	private final List<Entity> entities = new ArrayList<Entity>();
	
	/**
	 * Adds an entity to the world.
	 * @param 	entity
	 * 		  	Entity to add to the world.
	 * @throws	DoubleEntityException
	 * 			An entity can only be ones in a world.
	 */
	public void addEntity(Entity entity) throws DoubleEntityException {
		if (entities.contains(entity)) {
			throw new DoubleEntityException();
		} else {
			entities.add(entity);
		}
	}

	/**
	 * Removes an entity from the world.
	 * @param 	entity
	 * 		  	Entity to remove from the world.
	 * @throws 	IllegalEntityException
	 * 			The given entity must be in entities.
	 */
	public void removeEntity(Entity entity) throws IllegalEntityException {
		if (entities.contains(entity)) {
			entities.remove(entity);
		} else {
			throw new IllegalEntityException();
		}
	}
	
	//---------------specifieke functies
	
	
	/**
	 * Gets entity from given position in world.
	 * @param  position
	 * 		   The position to scan for entities.
	 * @return The entity on given position if there is an entity on that position.
	 * @return null if no entity is found.
	 */
	public Entity getEntityFromPosition(Vector2 position) {									//TODO: er van uit gegaan dat er maar 1 ding op dezelfde plaats kan zijn
		if (!isValidHeight(position.y) || !isValidWidth(position.x))
			return null;
		
		for (Entity entity : entities)
			if (Vector2.equals(entity.getPosition(), position))
				return entity;
		return null;
	}
	
	
	/**
	 * Gets all entities from the world.
	 * @return A list with all the entities in the world.
	 */
	public List<Entity> getAllEntities() {
		return new ArrayList<Entity>(entities);							//betekent dit dat er een nieuwe arraylist wordt gemaakt en deze wordt dan
																		//gevuld met alle entities?
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
	
	//----------------Advancing time
	
	
	
	
}