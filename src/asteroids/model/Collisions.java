package asteroids.model;

import asteroids.exceptions.UndefinedCollisionBehaviourException;

/*
+--------+-------------------------------+---------------------------------------+
|        |             Ship              |                Bullet                 |
+--------+-------------------------------+---------------------------------------+
| Ship   | Ship.collideShips(ship, ship) | bullet.hit(ship)                      |
+--------+-------------------------------+---------------------------------------+
| Bullet |                               | Bullet.collideBullets(bullet, bullet) |
+--------+-------------------------------+---------------------------------------+
*/


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
	 * @post  Ship.collideShips(first, second) if both entities are ships.
	 * @post  bullet.hit(ship) if one entity is a bullet and the other one is a ship.
	 * @post  Bullet.collideBullets(first, second) if both entities are bullets.
	 * 
	 */
	public static void collide(Entity first, Entity second) {
		if (first instanceof Ship && second instanceof Ship)
			Ship.collideShips((Ship)first, (Ship)second);
		else if (first instanceof Bullet && second instanceof Bullet)
			Bullet.collideBullets((Bullet)first, (Bullet)second);
		else if (first instanceof Bullet && second instanceof Ship)
			((Bullet)first).hit((Ship)second);
		else if (first instanceof Ship && second instanceof Bullet)
			((Bullet)second).hit((Ship)first);
		else
			throw new RuntimeException(new UndefinedCollisionBehaviourException(first, second));
	}
}
