package asteroids.model;

import asteroids.exceptions.UndefinedCollisionBehaviourException;


/**
 * Helper class to define the behavior between two entities.
 */
public class Collisions {
	/**
	 * Executes the appropriate handler for the collision of two entities.
	 * @param first
	 * 		  The first entity.
	 * @param second
	 *        The second entity.
	 * @effect
	 * +--------------+------------------+------------------------------+---------------+----------+-----------+
	 * |              |       Ship       |            Bullet            |  MinorPlanet  | Asteroid | Planetoid |
	 * +--------------+------------------+------------------------------+---------------+----------+-----------+
	 * | Entity       |                  | bullet.collideEntity(entity) |               |          |           |
	 * | -Ship        | Entity.bounce    |                              |               |          |           |
	 * | -Bullet      | bullet.hit(ship) |                              |               |          |           |
	 * | -MinorPlanet |                  |                              | Entity.bounce |          |           |
	 * |  -Asteroid   | ship.die()       |                              |               |          |           |
	 * |  -Planetoid  | ship.teleport()  |                              |               |          |           |
	 * +--------------+------------------+------------------------------+---------------+----------+-----------+
	 */
	public static void collide(Entity first, Entity second) {
		if (!collideOne(first, second) && !collideOne(second, first))
			throw new RuntimeException(new UndefinedCollisionBehaviourException(first, second));
	}

    /**
     * Helper function that tries to execute the appropriate handler for the collision of two entities.
     * @param  first
     *             The first entity.
     * @param  second
     *         The second entity.
     * @return true if and only if the appropriate collision handler has not has been executed.
     */

	private static boolean collideOne(Entity first, Entity second) {
		
		if (first instanceof Bullet)
			((Bullet)first).collideEntity(second);
		
		else if (first instanceof Ship) {
			if (second instanceof Ship)
				Entity.bounce(first, second);
			else if (second instanceof Asteroid)
				first.die();
			else if (second instanceof Planetoid)
				((Ship)first).teleport();
			else
				return false;
		} else if ((first instanceof MinorPlanet) && (second instanceof MinorPlanet))
			Entity.bounce(first, second);
		else
			return false;
		return true;
	}
}
