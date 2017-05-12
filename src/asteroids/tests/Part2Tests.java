package asteroids.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import asteroids.exceptions.DoubleEntityException;
import asteroids.exceptions.EntitiesOverlapException;
import asteroids.exceptions.InvalidParentShipException;
import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;
import asteroids.exceptions.InvalidTimeException;
import asteroids.exceptions.NotWithinBoundariesException;
import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.util.Vector2;

/**
 * Test functions for our code
 * 
 * @version 1.0
 * @author Kris Keersmaekers
 * @author Rik Pauwels
 *
 */
public class Part2Tests {

	// ---------------------TESTS MAKEN SHIP

	private static final double EPSILON = 0.0001;

	@Test
	public void testNewShip2_GivenParameters() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {

		Ship ship = new Ship(10, 20, 30, 40, 50, Math.PI, 1);
		assertEquals(10, ship.getPosition().x, EPSILON);
		assertEquals(20, ship.getPosition().y, EPSILON);
		assertEquals(30, ship.getVelocityVector().x, EPSILON);
		assertEquals(40, ship.getVelocityVector().y, EPSILON);
		assertEquals(50, ship.getRadius(), EPSILON);
		assertEquals(Math.PI, ship.getOrientation(), EPSILON);
	}

	@Test(expected = InvalidPositionException.class)
	public void testNewShip3InvalidPosition1_XNaN() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Ship(Double.NaN, 0, 0, 0, 10, 0, 1);
	}

	@Test(expected = InvalidPositionException.class)
	public void testNewShip3InvalidPosition2_XInfinity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Ship(Double.POSITIVE_INFINITY, 0, 0, 0, 10, 0, 1);
	}

	@Test(expected = InvalidPositionException.class)
	public void testNewShip3InvalidPosition3_YNaN() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Ship(0, Double.NaN, 0, 0, 20, 0, 1);
	}

	@Test(expected = InvalidPositionException.class)
	public void testNewShip3InvalidPosition4_YInfinity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Ship(0, Double.NEGATIVE_INFINITY, 0, 0, 30, 0, 1);
	}

	@Test
	public void testNewShip4InvalidVelocity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(0, 0, Double.NaN, 0, 3478, 0, 1);
		assertTrue(ship1.getVelocityVector().pythagoras() - EPSILON < ship1.getMaxSpeed());

		Ship ship2 = new Ship(0, 0, 0, Double.NEGATIVE_INFINITY, 178, 0, 1);
		assertTrue(ship2.getVelocityVector().pythagoras() - EPSILON < ship2.getMaxSpeed());

		Ship ship3 = new Ship(0, 0, 300000, 300000, 14564, 0, 1);
		assertTrue(ship3.getVelocityVector().pythagoras() - EPSILON < ship3.getMaxSpeed());

	}

	// We dont have to test if the orientation parameter can throw errors since
	// it's written in a nominal fashion, and we will test later if it works
	// with valid parameters

	@Test(expected = IllegalArgumentException.class)
	public void testNewShip6InvalidRadius1_RadNaN() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Ship(0, 0, 0, 0, Double.NaN, 0, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewShip6InvalidRadius2_RadInfinity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Ship(0, 0, 0, 0, Double.NEGATIVE_INFINITY, 0, 1);
	}

	@Test(expected = InvalidRadiusException.class)
	public void testNewShip6InvalidRadius3_RadNegatif() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Ship(0, 0, 0, 0, -15, 0, 1);
	}

	@Test(expected = InvalidRadiusException.class)
	public void testNewShip6InvalidRadius4_RadToSmall() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Ship(0, 0, 0, 0, -1, 0, 1); // less than MIN_RADIUS
	}

	// -------------------TESTS FUNCTIONS VAN SHIP DIE ENKEL MET SHIP TE MAKEN
	// HEBBEN

	@Test
	public void testGetPosition() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship = new Ship(3, -5, 0, 0, 451, 0, 1);
		assertEquals(ship.getPosition().x, 3, EPSILON);
		assertEquals(ship.getPosition().y, -5, EPSILON);
	}

	@Test
	public void testGetVelocity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship = new Ship(3, -5, 0, 0, 487, 0, 1);
		assertEquals(ship.getPosition().x, 3, EPSILON);
		assertEquals(ship.getPosition().y, -5, EPSILON);
	}

	@Test
	public void testGetRadius() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship = new Ship(3, 2, -3, -3, 15.73, Math.PI / 2, 1);
		assertEquals(ship.getRadius(), 15.73, EPSILON);
	}

	@Test
	public void testGetOrientation() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship = new Ship(-3, -4, 0, 5, 102, Math.PI / 2, 1);
		assertEquals(ship.getOrientation(), Math.PI / 2, EPSILON);
	}

	@Test
	public void testGetBaseMass() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship = new Ship(1, 2, 3, 4, 50, 6, 30E30);
		assertEquals(ship.getBaseMass(), 30E30, EPSILON);
	}

	@Test
	public void testInvalidBaseMass() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship = new Ship(1, 2, 3, 4, 50, 6, 1);
		assertEquals(ship.getBaseMass(), 7.4351026, EPSILON);
	}

	@Test
	public void testMove1() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, InvalidTimeException {
		Ship ship = new Ship(7, -3, 2, -87, 100, Math.PI / 3, 1);
		World world = new World(1000, 1000);
    	world.addEntity(ship);
    	world.evolve(5);
		assertEquals(ship.getPosition().x, 17, EPSILON);
		assertEquals(ship.getPosition().y, -438, EPSILON);
    	world.evolve(10.5);
		assertEquals(ship.getPosition().x, 38, EPSILON);
		assertEquals(ship.getPosition().y, -1351.5, EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMove2_MoveInfinity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, InvalidTimeException {
		Ship ship = new Ship(7, -3, 2, -87, 100, 0, 1);
		World world = new World(1000, 1000);
    	world.addEntity(ship);
    	world.evolve(Double.POSITIVE_INFINITY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMove3_MoveNegatif() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, InvalidTimeException {
		Ship ship = new Ship(7, -3, 2, -87, 100, 0, 1);
		World world = new World(1000, 1000);
    	world.addEntity(ship);
    	world.evolve(-4);
	}

	@Test
	public void testTurn() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship = new Ship(3, 2, 1, 5, 10, 7 * Math.PI / 4, 1);
		ship.turn(Math.PI / 2);
		assertEquals(ship.getOrientation(), Math.PI / 4, EPSILON);
	}

	@Test
	public void testThrust() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship = new Ship(0, 2, 4, 2, 10, 5 * Math.PI / 3, 1);
		ship.thrust(10);
		assertEquals(ship.getVelocityVector().x, 9, EPSILON);
		assertEquals(ship.getVelocityVector().y, 2 - 5 * Math.sqrt(3), EPSILON);
	}

	@Test
	public void testAdvancedMovements() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, InvalidTimeException {
		Ship ship = new Ship(4, 2, 4, 3, 19, Math.PI / 2, 1);
		World world = new World(1000, 1000);
    	world.addEntity(ship);
    	ship.thrust(15);
    	world.evolve(4);
		ship.turn(Math.PI / 2);
		world.evolve(10);
		ship.turn(Math.PI);
		ship.thrust(4);
		ship.turn(2 * Math.PI); // 360 noscope
		world.evolve(3);
		assertEquals(ship.getPosition().x, 84, EPSILON);
		assertEquals(ship.getPosition().y, 308, EPSILON);
		assertEquals(ship.getVelocityVector().x, 8, EPSILON);
		assertEquals(ship.getVelocityVector().y, 18, EPSILON);
		assertEquals(ship.getOrientation(), 0, EPSILON);
	}

	// --------------TESTS FUNCTIONS MET MEERDERE SHIPS

	@Test
	public void testGetDistanceBetween1_NormalCase() throws NullPointerException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(1, 1, 1, 1, 10, Math.PI / 2, 1);
		Ship ship2 = new Ship(2, 2, 2, 2, 10, Math.PI / 3, 1);
		assertEquals(Ship.getDistanceBetween(ship1, ship2), Math.sqrt(2) - 20, EPSILON);

	}

	@Test(expected = NullPointerException.class)
	public void testGetDistanceBetween2_Ship2IsNull() throws NullPointerException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(5, 5, 5, 5, 50, Math.PI / 2, 1);
		Ship.getDistanceBetween(ship1, null);
	}

	@Test(expected = NullPointerException.class)
	public void testGetDistanceBetween3_Ship1IsNull() throws NullPointerException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(6, 2, 3, 6, 60, Math.PI / 2, 1);
		Ship.getDistanceBetween(null, ship1);
	}

	@Test
	public void testOverlap1_NormalCase() throws NullPointerException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(6, 2, 6, 4, 70, Math.PI / 2, 1);
		Ship ship2 = new Ship(3, 40, 3, 6, 70, Math.PI / 2, 1);
		assertTrue(Ship.overlap(ship1, ship2));
	}

	@Test(expected = NullPointerException.class)
	public void testOverlap2_Ship2IsNull() throws NullPointerException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(26, 16, 36, 64, 65, Math.PI / 2, 1);
		Ship.overlap(ship1, null);
	}

	@Test(expected = NullPointerException.class)
	public void testOverlap3_Ship1IsNull() throws NullPointerException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(4, 26, 60, 6, 26, Math.PI / 2, 1);
		Ship.overlap(null, ship1);
	}

	@Test
	public void testGetTimeToCollision1_Never() throws NullPointerException, EntitiesOverlapException, IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(6, 6, 6, 6, 16, Math.PI / 2, 1);
		Ship ship2 = new Ship(70, 6, 6, 6, 16, Math.PI / 2, 1);
		assertEquals(Ship.getTimeToCollision(ship1, ship2), Double.POSITIVE_INFINITY, EPSILON);
	}

	@Test(expected = NullPointerException.class)
	public void testGetTimeToCollision2_Ship1IsNull()
			throws NullPointerException, EntitiesOverlapException, IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(6, 6, 6, 6, 60, Math.PI / 2, 1);
		Ship.getTimeToCollision(null, ship1);
	}

	@Test(expected = NullPointerException.class)
	public void testGetTimeToCollision3_Ship2IsNull()
			throws NullPointerException, EntitiesOverlapException, IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(6, 6, 6, 6, 60, Math.PI / 2, 1);
		Ship.getTimeToCollision(ship1, null);
	}

	@Test(expected = EntitiesOverlapException.class)
	public void testGetTimeToCollision4_ShipsOverlap()
			throws NullPointerException, EntitiesOverlapException, IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(6, 6, 6, 6, 60, Math.PI / 2, 1);
		Ship ship2 = new Ship(6, 40, 6, 6, 60, Math.PI / 2, 1);
		Ship.getTimeToCollision(ship1, ship2);
	}

	@Test
	public void testGetTimeToCollision5_NormalCase()
			throws NullPointerException, EntitiesOverlapException, IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(100, 6, -1, 6, 11, Math.PI / 2, 1);
		Ship ship2 = new Ship(7, 6, 0, 6, 10, Math.PI / 2, 1);
		assertEquals(Ship.getTimeToCollision(ship1, ship2), 72, EPSILON);
	}

	@Test
	public void testGetCollisionPosition1_Never()
			throws NullPointerException, EntitiesOverlapException, IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(6, 6, 6, 6, 10, Math.PI / 2, 1);
		Ship ship2 = new Ship(70, 6, 6, 6, 14, Math.PI / 2, 1);
		assertTrue(Ship.getCollisionPosition(ship1, ship2) == null);
	}

	@Test(expected = NullPointerException.class)
	public void testGetCollisionPosition2_Ship2IsNull()
			throws NullPointerException, EntitiesOverlapException, IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship = new Ship(6, 6, 6, 6, 26, Math.PI / 2, 1);
		Ship.getCollisionPosition(ship, null);
	}

	@Test(expected = EntitiesOverlapException.class)
	public void testGetCollisionPosition3_ShipsOverlap()
			throws NullPointerException, EntitiesOverlapException, IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(6, 6, 6, 6, 16, Math.PI / 2, 1);
		Ship ship2 = new Ship(20, 6, 6, 6, 16, Math.PI / 2, 1);
		Ship.getCollisionPosition(ship1, ship2);
	}

	@Test
	public void testGetCollisionPosition4_NormalCase()
			throws NullPointerException, EntitiesOverlapException, IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(100, 6, -1, 6, 11, Math.PI / 2, 1);
		Ship ship2 = new Ship(7, 6, 0, 6, 10, Math.PI / 2, 1);

		Vector2 collisionPosition = Ship.getCollisionPosition(ship1, ship2);
		assertEquals(collisionPosition.x, 17, EPSILON);
		assertEquals(collisionPosition.y, 438, EPSILON);
	}

	@Test
	public void testCollideShips() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, InvalidTimeException {
		Ship ship1 = new Ship(100, 200, 1, 0, 20, 0, 8);
		Ship ship2 = new Ship(140, 200, -1, 0, 20, 0, 8);
		World world = new World(1000, 1000);
		world.addEntity(ship1);
		world.addEntity(ship2);
		world.evolve(1);
		
		assertEquals(ship1.getVelocityVector().x, -1, EPSILON);
		assertEquals(ship2.getVelocityVector().x, 1, EPSILON);
	}

	// ------------------------AANMAKEN VAN BULLETS

	@Test
	public void testNewBullet_GivenParameters() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {

		Bullet bullet = new Bullet(1, 2, 3, 4, 5, null);
		assertEquals(1, bullet.getPosition().x, EPSILON);
		assertEquals(2, bullet.getPosition().y, EPSILON);
		assertEquals(3, bullet.getVelocityVector().x, EPSILON);
		assertEquals(4, bullet.getVelocityVector().y, EPSILON);
		assertEquals(5, bullet.getRadius(), EPSILON);
	}

	@Test(expected = InvalidPositionException.class)
	public void testNewBullet2InvalidPosition1_XNaN() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Bullet(Double.NaN, 1, 0, 0, 10, null);
	}

	@Test(expected = InvalidPositionException.class)
	public void testNewBullet2InvalidPosition2_XInfinity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Bullet(Double.POSITIVE_INFINITY, 3, 0, 0, 0, null);
	}

	@Test(expected = InvalidPositionException.class)
	public void testNewbullet3InvalidPosition3_YNaN() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Ship(0, Double.NaN, 0, 1, 20, 0, 1);
	}

	@Test(expected = InvalidPositionException.class)
	public void testNewBullet3InvalidPosition4_YInfinity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Bullet(0, Double.NEGATIVE_INFINITY, 7, 0, 30, null);
	}

	@Test
	public void testNewBullet4InvalidVelocity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship1 = new Ship(100, 6, -1, 6, 11, Math.PI / 2, 1);
		Bullet bullet1 = new Bullet(0, 4, Double.NaN, 0, 34, ship1);
		assertTrue(bullet1.getVelocityVector().pythagoras() - EPSILON < bullet1.getMaxSpeed());

		Bullet bullet2 = new Bullet(0, 10, 0, Double.NEGATIVE_INFINITY, 178, ship1);
		assertTrue(bullet2.getVelocityVector().pythagoras() - EPSILON < bullet2.getMaxSpeed());

		Bullet bullet3 = new Bullet(30, 0, 300000, 300000, 14541, null);
		assertTrue(bullet3.getVelocityVector().pythagoras() - EPSILON < bullet3.getMaxSpeed());

	}

	// We dont have to test if the orientation parameter can throw errors since
	// it's written in a nominal fashion, and we will test later if it works
	// with valid parameters

	@Test(expected = IllegalArgumentException.class)
	public void testNewBullet5InvalidRadius1_RadNaN() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Bullet(0, 0, 0, 0, Double.NaN, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewBullet5InvalidRadius2_RadInfinity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Bullet(0, 0, 0, 0, Double.NEGATIVE_INFINITY, null);
	}

	@Test(expected = InvalidRadiusException.class)
	public void testNewBullet5InvalidRadius3_RadNegatif() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Bullet(0, 0, 0, 0, -15, null);
	}

	@Test(expected = InvalidRadiusException.class)
	public void testNewBullet5InvalidRadius4_RadToSmall() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		new Ship(0, 0, 0, 0, -1, 0, 1); // less than MIN_RADIUS
	}

	// ----------------TESTS FUNCTIONS BULLETS

	@Test
	public void testGetPositionBullet() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Bullet bullet = new Bullet(3, -5, 0, 0, 451, null);
		assertEquals(bullet.getPosition().x, 3, EPSILON);
		assertEquals(bullet.getPosition().y, -5, EPSILON);
	}

	@Test
	public void testGetVelocityBullet() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Bullet bullet = new Bullet(3, -5, 0, 0, 487, null);
		assertEquals(bullet.getPosition().x, 3, EPSILON);
		assertEquals(bullet.getPosition().y, -5, EPSILON);
	}

	@Test
	public void testGetRadiusBullet() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Bullet bullet = new Bullet(3, 2, -3, -3, 15.73, null);
		assertEquals(bullet.getRadius(), 15.73, EPSILON);
	}

	@Test
	public void testMove1Bullet() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, InvalidTimeException {
		Bullet bullet = new Bullet(7, -3, 2, -87, 100, null);
		World world = new World(1000, 1000);
		world.addEntity(bullet);
		world.evolve(5);
		assertEquals(bullet.getPosition().x, 17, EPSILON);
		assertEquals(bullet.getPosition().y, -438, EPSILON);
		world.evolve(10.5);
		assertEquals(bullet.getPosition().x, 38, EPSILON);
		assertEquals(bullet.getPosition().y, -1351.5, EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMove2Bullet_MoveInfinity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, InvalidTimeException {
		Bullet bullet = new Bullet(7, -3, 2, -87, 100, null);
		World world = new World(1000, 1000);
		world.addEntity(bullet);
		world.evolve(Double.POSITIVE_INFINITY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMove3Bullet_MoveNegatif() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, InvalidTimeException {
		Bullet bullet = new Bullet(7, -3, 2, -87, 100, null);
		World world = new World(1000, 1000);
		world.addEntity(bullet);
		world.evolve(-4);
	}

	@Test
	public void testIsLoadedInParent1() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Ship ship = new Ship(5, 5, 1, 1, 15, 1, 1);
		Bullet bullet = new Bullet(3, 2, 1, 5, 10, ship);
		ship.loadBullet(bullet);
		assertTrue(bullet.isLoadedInMotherShip());
	}

	@Test
	public void testIsLoadedInParent2() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, InvalidParentShipException {
		World world = new World(500, 600);
		Ship ship = new Ship(50, 50, 2, 2, 20, 2, 2);
		world.addEntity(ship);
		Bullet bullet = new Bullet(30, 20, 1, 5, 10, ship);
		ship.shootBullet(bullet);
		assertFalse(bullet.isLoadedInMotherShip());
	}

	@Test
	public void testAddToParent1() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException {
		World world = new World(5000, 6000);
		Ship ship = new Ship(400, 500, 9, 8, 60, 0, 0);
		world.addEntity(ship);
		Bullet bullet = new Bullet(30, 20, 1, 5, 10, ship);
		assertEquals(bullet.getMotherShip(), ship);
	}

	@Test
	public void testAddToParent2() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		Bullet bullet = new Bullet(3, 2, 1, 5, 10, null);
		assertEquals(bullet.getMotherShip(), null);
	}

	// ----------------------TESTS WORLD

	@Test
	public void testMakingWorld1() {
		World world = new World(500, 600);
		assertEquals(world.getWidth(), 500, EPSILON);
		assertEquals(world.getHeight(), 600, EPSILON);
	}

	@Test
	public void testMakingWorld2() {
		World world = new World(Double.POSITIVE_INFINITY, -5);
		assertEquals(world.getWidth(), Double.MAX_VALUE, EPSILON);
		assertEquals(world.getHeight(), 0, EPSILON);
	}

	@Test
	public void testTerminatingWorld() {
		World world = new World(Double.POSITIVE_INFINITY, -4);
		world.terminate();
		assertTrue(world.isTerminated());
	}

	@Test
	public void testAddEntity1() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, NotWithinBoundariesException {
		World world = new World(1000, 2000);
		Bullet bullet = new Bullet(10, 20, 3, 4, 5, null);
		Ship ship = new Ship(100, 200, 300, 400, 50, 6, 7);

		world.addEntity(ship);
		Set<Entity> test = new HashSet<Entity>();
		test.add(ship);

		assertEquals(world.getAllEntities(Ship.class), test);

		ship.loadBullet(bullet);

		test.remove(ship);
		test.add(bullet);
		assertEquals(world.getAllEntities(Bullet.class), test);

		test.add(ship);

		assertEquals(world.getAllEntities(), test);

	}

	@Test(expected = NotWithinBoundariesException.class)
	public void testAddEntity2()
			throws IllegalArgumentException, InvalidRadiusException, DoubleEntityException, InvalidPositionException, EntitiesOverlapException, NotWithinBoundariesException {
		World world = new World(1000, 2000);
		Bullet bullet = new Bullet(1, 2, 3, 4, 5, null);

		world.addEntity(bullet);
	}

	@Test(expected = DoubleEntityException.class)
	public void testAddEntity3()
			throws IllegalArgumentException, DoubleEntityException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, NotWithinBoundariesException {
		World world = new World(1000, 2000);
		Bullet bullet = new Bullet(10, 20, 3, 4, 5, null);

		world.addEntity(bullet);
		world.addEntity(bullet);
	}

	@Test
	public void testRemoveEntity() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException {
		World world = new World(1000, 2000);
		// Bullet bullet = new Bullet(1, 2, 3, 4, 5, null);
		Ship ship = new Ship(100, 200, 300, 400, 50, 6, 7);

		world.addEntity(ship);
		Set<Entity> test = new HashSet<Entity>();
		world.removeEntity(ship);

		assertEquals(world.getAllEntities(), test);
	}

	@Test
	public void testGetEntityFromPosition() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException {
		World world = new World(1000, 2000);
		// Bullet bullet = new Bullet(1, 2, 3, 4, 5, null);
		Ship ship = new Ship(100, 200, 300, 400, 50, 6, 7);

		world.addEntity(ship);

		assertEquals(ship.getPosition().x, 100, EPSILON);
		assertEquals(ship.getPosition().y, 200, EPSILON);
	}

	@Test
	public void testEvolveShipShipBoundaryCollision() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, InvalidTimeException {
		World world = new World(1000, 2000);
		// Bullet bullet = new Bullet(1, 2, 3, 4, 5, null);
		Ship ship1 = new Ship(100, 200, 300, 400, 50, 6, 7);
		Ship ship2 = new Ship(100, 400, 300, 100, 50, 6, 7);

		world.addEntity(ship1);
		world.addEntity(ship2);
		
		world.evolve(100);

		//bots verschillende keren
		
		assertEquals(ship1.getPosition().x, 600, EPSILON);
		assertEquals(ship1.getPosition().y, 600, EPSILON);
		assertEquals(ship2.getPosition().x, 600, EPSILON);
		assertEquals(ship2.getPosition().y, 700, EPSILON);
		
		assertEquals(ship1.getVelocityVector().x, -300, EPSILON);
		assertEquals(ship1.getVelocityVector().y, 400, EPSILON);
		assertEquals(ship2.getVelocityVector().x, -300, EPSILON);
		assertEquals(ship2.getVelocityVector().y, -100, EPSILON);
	}

	@Test
	public void testEvolveShipBulletCollision() throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException, EntitiesOverlapException, InvalidTimeException {
		World world = new World(1000, 2000);
		// Bullet bullet = new Bullet(1, 2, 3, 4, 5, null);
		Ship ship = new Ship(100, 200, 300, 400, 50, 6, 7);
		Bullet bullet = new Bullet(100, 400, 300, 100, 50, null);

		world.addEntity(ship);
		world.addEntity(bullet);
		
		world.evolve(100);

		assertTrue(ship.isTerminated());
		assertTrue(bullet.isTerminated());
		
		assertFalse(world.getAllEntities().contains(ship));
		assertFalse(world.getAllEntities().contains(bullet));
	}
}
