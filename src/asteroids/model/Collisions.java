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
	
	private static boolean collideOne(Entity first, Entity second) {
		System.out.println("hij doet collide");
		
		System.out.println(first.getClass());
		System.out.println(second.getClass());
		
		
		
		if (((first instanceof Bullet) && (second instanceof Ship) && !(((Bullet)first).getMotherShip() == second))) {
			System.out.println("hij gaat collideEntity doen");
			((Bullet)first).collideEntity(second);
			
		} else if ((first instanceof Bullet) && !(second instanceof Ship)) {
			((Bullet)first).collideEntity(second);
		}
		
		else if (first instanceof Ship) {
			if (second instanceof Ship)
				Entity.bounce(first, second);
			else if (second instanceof Bullet) {
				System.out.print(second);
				System.out.print(" gaat hit doen met: ");
				System.out.println(first);
				((Bullet)second).hit((Ship)first);

			}
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
