package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import asteroids.util.Vector2;

public class World {
	
	private double width;
	private double height;
	
	private static double LOWER_BOUND_HEIGHT = 0;
	private static double UPPER_BOUND_HEIGHT = Double.MAX_VALUE;
	
	private static double LOWER_BOUND_WIDTH = 0;
	private static double UPPER_BOUND_WIDTH = Double.MAX_VALUE;
	
	private boolean isTerminated = false;
	
	private final List<Entity> entities = new ArrayList<Entity>();		//TODO: class entity nog aanmaken
	
	public World(double width, double height) {
		if (isValidWidth(width)) {		//moet het this.checkValidWidth zijn omdat die finctie niet static is?
			this.width = width;
		}
		if (isValidHeight(height)) {
			this.height = height;
		}
	}
	
	
	
	public void setUpperBoundHeight(double height) {
		if (height >= LOWER_BOUND_HEIGHT && height <= UPPER_BOUND_HEIGHT) {
			UPPER_BOUND_HEIGHT = height;
		}
	}
	
	public void setUpperBoundWidth(double width) {
		if (width >= LOWER_BOUND_WIDTH && width <= UPPER_BOUND_WIDTH) {
			UPPER_BOUND_WIDTH = width;
		}
	}
	
	private static boolean isValidWidth(double width) {
		if (width >= LOWER_BOUND_WIDTH && width <= UPPER_BOUND_WIDTH) {
			return true;
		}
		return false;
	}
	
	private static boolean isValidHeight(double height) {
		if (height >= LOWER_BOUND_HEIGHT && height <= UPPER_BOUND_HEIGHT) {
			return true;
		}
		return false;
	}
	
	
	
	
	public void terminate() {		//person line 49
		if (!isTerminated()) {
			this.isTerminated = true;
		}
	}
	
	public boolean isTerminated() {
		return isTerminated;
	}
	
	
	
	/**
	 * Adds an entity to the world.
	 * @param entity
	 * 		  Entity to add to the world.
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	/**
	 * Removes an entity from the world.
	 * @param entity
	 * 		  Entity to remove from the world.
	 */
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	
	
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
	
	
	
	
	
	public void advanceTime(Double Dt) {
		do {
			
			double firstCollisionTime = Double.POSITIVE_INFINITY;
			Entity collisionFirstShip;
			Entity collisionSecondShip;
			for (int i = 0; i < entities.size(); i++)
				for(int j = 1; j < entities.size(); j++) {
					double collisionTime = Entity.getTimeToCollision(entities.get(i), entities.get(j));
					if (firstCollisionTime > collisionTime) {
						firstCollisionTime = collisionTime;
						collisionFirstShip = entities.get(i);
						collisionSecondShip = entities.get(j);
					}
				}
			
			
			if (firstCollisionTime < Dt) { //collision happens before end Dt
				for (Entity entity: entities)
					entity.move(firstCollisionTime);
				Dt -= firstCollisionTime;
				
				//TODO: BOTSEN YO
			} else
				for (Entity entity: entities)
					entity.move(Dt);
				Dt = (double)0;
		} while (Dt > 0);
	}
	
	
}