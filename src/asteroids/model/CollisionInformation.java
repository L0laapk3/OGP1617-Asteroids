package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;

/**
 *  Helper class that contains information about a collision.
 * @author Kris Keersmaekers
 * @author Rik Pauwels
 */
@Value
public class CollisionInformation {
	public final Entity firstEntity;
	public final Entity secondEntity;
	public final double timeToCollision;

	public CollisionInformation(Entity firstEntity, Entity secondEntity, double timeToCollision) {
		this.firstEntity = firstEntity;
		this.secondEntity = secondEntity;
		this.timeToCollision = timeToCollision;
	}
	
	public boolean isWallCollision() {
		return secondEntity == null;
	}
}