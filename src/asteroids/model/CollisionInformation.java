package asteroids.model;

import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

/**
 * Helper class that contains information about a collision.
 * 
 * @author Kris Keersmaekers
 * @author Rik Pauwels
 */
@Value
public class CollisionInformation {
      public final Entity firstEntity;
      public final Entity secondEntity;
      public final double timeToCollision;

      /**
       * Creates the collision information struct.
       * @effect this.firstEntity = firstEntity
       * @effect this.secondEntity = secondEntity
       * @effect if this.secondEntity == null, then the collision is a wallcollision.
       * @effect this.timeToCollision = timeToCollision
       */
      public CollisionInformation(Entity firstEntity, Entity secondEntity, double timeToCollision) {
            this.firstEntity = firstEntity;
            this.secondEntity = secondEntity;
            this.timeToCollision = timeToCollision;
      }
      
      /**
       * Returns true if and only if the CollisionInformation contains information about a wall collision.
       * @return secondEntity == null
       */
      @Raw
      public boolean isWallCollision() {
            return secondEntity == null;
      }
}