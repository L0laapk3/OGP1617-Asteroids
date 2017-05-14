package asteroids.model;

import java.util.Set;
import java.util.function.Predicate;

import asteroids.exceptions.EntitiesOverlapException;
import asteroids.exceptions.IllegalEntityException;
import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;
import asteroids.exceptions.NoWorldException;
import asteroids.util.OGUtil;
import asteroids.util.Vector2;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;



//TODO: orientation bekijken in entity en alle subclasses van entity.


/**
 * A class to define basic entities.
 * 
 * @effect   Instance
 * @invar    Both the x and y coordinate of the position of the entities must not be NaN or infinity.
 *         | isValidPosition(getPosition())
 * @invar    The radius must be bigger than MIN_RADIUS.
 * 		   | isValidRadius(getRadius())
 * @invar    Velocity must be valid.
 * 		   | this.isValidVelocity(getVelocity)
 * 
 * @version  1.0
 * @author   Kris Keersmaekers
 * @author   Rik Pauwels
 */
public abstract class Entity extends Instance {
	
	
	/**
	 * Create a new entity with the given position, velocity, mass and radius.
	 * 
	 * @param  x
	 * 	       The x coordinate where the new entity has to be created.
	 * @param  y
	 *         The y coordinate where the new entity has to be created.
	 * @param  xVelocity
	 * 		   The initial speed in the x direction of the new entity.
	 * @param  yVelocity
	 * 		   The initial speed in the y direction of the new entity.
	 * @param  radius
	 * 		   The size of the newly created entity.
	 * @param  mass
	 * 		   The mass of the newly created entity.
	 * @param  MIN_RADIUS
	 * 		   sets MIN_RADIUS constant.
	 * @post   The x coordinate of this new entity is equal to the given x coordinate.
	 * 	     | getPosition(new)[0] == x
	 * @post   The y coordinate of this new entity is equal to the given y coordinate.
	 * 	     | getPosition(new)[1] == y
	 * @post   The x velocity of this new entity is equal the given x velocity.
	 * 		 | getVelocity(new)[0] = xVelocity
	 * @post   The y velocity of this new entity is equal to the given y velocity.
	 * 		 | getVelocity(new)[1] = yVelocity
	 * @post   if either the x or y velocity is NaN or infinite, both velocities will be set to zero instead.
	 *       | if (isInvalidNumber(xVelocity) or isInvalidNumber(yVelocity)
	 *       |     xVelocity = yVelocity = 0;
	 * @post   If the total velocity exceeds this.maxSpeed, the velocity will be modified so that the direction of the velocity remains
	 *         the same, but the total velocity will be this.maxSpeed.
	 *       | speed = pythagoras(xVelocity, yVelocity)
	 *       | if (speed > this.maxSpeed)
	 *       |     scale = this.maxSpeed / speed
	 *       |     xVelocity = xVelocity * scale
	 *       |     yVelocity = yVelocity * scale
	 * @post   The base mass of this entity is equal to the given mass
	 * 		 | new.this.getBaseMass == mass
	 * @post   The radius of this new entity is equal to the given radius.
	 * 		 | new.this.getRadius() == radius
	 * @throws InvalidPositionException
	 * 		   The coordinates of the entity should not be infinite or NaN.
	 * @throws IllegalArgumentException
	 * 		   The radius of the entity should not be infinite.
	 * @throws IllegalArgumentException
	 * 		   The radius of the entity should not be NaN.
	 * @throws InvalidRadiusException
	 * 		   The radius must not be smaller than MIN_RADIUS.	
	 */
	@Raw
	Entity(double x, double y, double xVelocity, double yVelocity, double radius, double mass, double MIN_RADIUS)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
				
		if (!isValidMinRadius(MIN_RADIUS))
			throw new InvalidRadiusException("The minimum radius is not valid.");
		this.MIN_RADIUS = MIN_RADIUS;
		this.maxSpeed = 300000;

		// DEFENSIVE
		// Variables of type double can never be null, so we do not have to check if they are null.
		Vector2 pos = new Vector2(x, y);
		
		if ((this.getWorld() == null) && !(Double.isNaN(x)) && !(Double.isNaN(y))) {
			this.position = pos;
		} else {
			throw new InvalidPositionException();
		}

		// TOTAL
		if (OGUtil.isInvalidNumber(xVelocity) || OGUtil.isInvalidNumber(yVelocity))
			this.velocity = new Vector2(0, 0);
		else {
			this.setVelocity(xVelocity, yVelocity);
		}
		

		// DEFENSIVE
		OGUtil.throwErrorIfInvalidNumbers(radius);
		
		if (radius < MIN_RADIUS)
			if (this.getClass() == Planetoid.class)
				this.die();
			else
				throw new InvalidRadiusException();
		this.radius = radius;
		
		//mass is never checked if it is valid, because it is an abstract class. Child classes are expected to handle this themselves.
		this.baseMass = mass;

	}

	/**
	 * Gets the total mass of the entity.
	 */
	@Raw
	@Basic
	public double getMass() {
		return this.getBaseMass();
	}

	/**
	 * Variable storing the mass of the entity.
	 */
	protected double baseMass;

	/**
	 * Gets the unloaded mass of the entity.
	 */
	@Raw
	@Basic
	public double getBaseMass() {
		return this.baseMass;
	}
	
	
	
	/**
	 * Calculates mass from rho and radius.
	 * @param  rho
	 *         The rho value for the hypothetical entity.
	 * @param  radius
	 *         The radius value for the hypothetical entity.
	 * @return The mass of a hypothetical entity with given rho and radius.
	 */
	@Raw
	@Basic
	@Immutable
	public static double calculateBassMass(double rho, double radius) {
		return rho * Math.PI * radius * radius * radius * 4 / 3;
	}


	/**
	 * Constant for the maximum velocity.
	 */
	private double maxSpeed;
	
	/**
	 * Set the maxSpeed of this entity to the given maxSpeed
	 * 
	 * @param 	maxSpeed
	 * 			The new maxSpeed for this entity
	 * @post 	The maxSpeed of this entity is the same as the given maxSpeed
	 * 		  | new.getMaxSpeed() == maxSpeed
	 */
	@Raw
	@Basic
	private void setMaxSpeed(double maxSpeed) {
		if (this instanceof Bullet && Bullet.isValidMaxSpeed(maxSpeed)) {
			Bullet.setMaxSpeedBullet(maxSpeed);
		}
		else if (Entity.isValidMaxSpeed(maxSpeed)) {
			this.maxSpeed = maxSpeed;
		}
	}

	/**
	 *  Returns the maximum Speed of the entity
	 *  @return the maxSpeed of the entity
	 */
	@Raw
	@Basic
	public double getMaxSpeed() {
		if (this instanceof Bullet)
			return maxSpeed;
		return this.maxSpeed;
	}

	/**
	 * Checks whether the given maxSpeed is a valid maxSpeed.
	 * 
	 * @param 	maxSpeed
	 * 			The maxSpeed to be checked.
	 * @return	True if and only if the maxSpeed is 0 or more and less than infinity.
	 * 		  | result == (maxSpeed>=0) && (maxSpeed<300000) 
	 */
	@Raw
	@Basic
	@Immutable
	public static boolean isValidMaxSpeed(double maxSpeed) {
		if ((maxSpeed >= 0) && (maxSpeed < 300000)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Variable registering the speed of the entity.
	 */
	private Vector2 velocity;

	/**
	 * Sets the velocity
	 * @param  xVelocity
	 * 		   The velocity in the x direction.
	 * @param  yVelocity
	 * 		   The velocity in the y direction.
	 * @effect This function will write xVelocity and yVelocity in the class' internal variables for respectively xVelocity and yVelocity.
	 * @post   If the total velocity exceeds this.maxSpeed, the velocity will be modified so that the direction of the velocity remains
	 *         the same, but the total velocity will be this.maxSpeed.
	 *       | speed = pythagoras(xVelocity, yVelocity)
	 *       | if (speed > this.maxSpeed)
	 *       |     scale = this.maxSpeed / speed
	 *       |     xVelocity = xVelocity * scale
	 *       |     yVelocity = yVelocity * scale
	 * @note   This code is written in a total manner.
	 */
	@Raw
	void setVelocity(Vector2 velocity) {
		double speed = velocity.pythagoras();
		if (speed > this.maxSpeed) {
			double scale = this.maxSpeed / speed;
			this.velocity = Vector2.multiply(velocity, scale);
			// OGUtil.println("[WARN] speed exceeded this.maxSpeed while creating entity."); //for debugging possible problems in future code
		} else
			this.velocity = velocity;
	}

	/**
	 * Sets the velocity
	 * 
	 * @effect sets the velocityvector to the given values
	 * 		 | setVelocity(new Vector2(xVelocity, yVelocity))
	 */
	@Raw
	void setVelocity(double xVelocity, double yVelocity) {
		setVelocity(new Vector2(xVelocity, yVelocity));
	}

	/**
	 * Return the velocity vecotr of entity.
	 * 
	 * @note   This is written in a total fashion.
	 */
	@Raw
	@Basic
	public Vector2 getVelocityVector() {
		return new Vector2(this.velocity);
	}

	/**
	 * Return the velocity of entity.
	 * 
	 * @return The total velocity
	 * @note   This is written in a total fashion.
	 */
	@Raw
	public double getVelocity() {
		return this.velocity.pythagoras();
	}

	/**
	 * Checks whether the given velocity is a valid maxSpeed.
	 * 
	 * @param 	velocity
	 * 			The maxSpeed to be checked.
	 * @return	True if and only if the velocity is 0 or more and less than infinity.
	 * 		  | result ==  ((velocity.x >= 0) && (velocity.y >= 0) && (velocity.x < infinity) && (velocity.y < infinity))
	 */
	@Raw
	@Basic
	@Immutable
	public static boolean isValidVelocity(Vector2 velocity) {
		if ((velocity.x >= 0) && (velocity.y >= 0) && (velocity.x < Double.POSITIVE_INFINITY) && (velocity.y < Double.POSITIVE_INFINITY)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Variable registering the coordinates of the entity.
	 */
	private Vector2 position;
	
	/**
	 * Returns the position of the entity.
	 * 
	 * @return the position of the entity
	 */
	@Raw
	@Basic
	public Vector2 getPosition() {
		return this.position;
	}

	/**
	 * Set the position for this entity.
	 * 
	 * @param position
	 * @post  The position of this entity is the same as the given position.
	 */
	@Raw
	@Basic
	void setPosition(Vector2 position) {
		this.position = position;
	}

	/**
	 * Checks whether the position is valid for a entity.
	 * 
	 * @param position A Vector2 object containing the position to check.
	 * 
	 * @return True if and only if both the x and y coordinate of the given position are valid numbers: They cannot be NaN or infinity. 
	 * 		 | result == (!isInvalidNumber(position.x) && !isInvalidNumber(position.y))
	 */
	public boolean isValidPosition(Vector2 position) {
		return !OGUtil.isInvalidNumber(position.x) && !OGUtil.isInvalidNumber(position.y);
	}

	/**
	 * Variable that holds the minimum radius from a entity.
	 */
	private final double MIN_RADIUS;

	/**
	 * Returns the minimum radius of the entity.
	 * 
	 * @return MIN_RADIUS
	 * 		   Returns the minimum radius of the entity.
	 */
	@Raw
	@Basic
	public double getMinRadius() {
		return MIN_RADIUS;
	}

	/**
	 * Checks if a radius is a valid MinRadius.
	 * 
	 * @param radius
	 * @return True if and only if the radius is greater than 0.
	 * 		 | radius >= 0
	 */
	@Raw
	@Basic
	@Immutable
	public static boolean isValidMinRadius(double radius) {
		return radius >= 0 && radius < Double.POSITIVE_INFINITY && !(Double.isNaN(radius));
	}
	

	/**
	 * Variable registering the size of the entity.
	 */
	protected double radius;

	/**
	 * Return the radius of entity.
	 * 
	 * @return The radius.
	 */
	@Raw
	@Basic
	@Immutable
	public double getRadius() {
		return this.radius;
	}
	
	


	/**
	 * Sets the radius of this entity to the given radius.
	 * 
	 * @param radius
	 * 		  The new radius of the entity.
	 */
	@Raw
	@Basic
	void setRadius(double radius) {
		this.radius = radius;
	}
	

	/**
	 * Check whether the radius is valid for a entity.
	 * 
	 * @param  radius
	 * 		   The radius to check.
	 * @return True if and only if given radius is a valid number and not smaller than MIN_RADIUS.
	 *       | result == (radius >= MIN_RADIUS)
	 */
	@Raw
	@Basic
	public boolean isValidRadius(double radius) {
		return radius >= 0 && radius < Double.POSITIVE_INFINITY && radius >= this.getMinRadius();
	}
		
	
	
	/**
	 * Terminate this entity.
	 *
	 * @post   The instance is terminated.
	 * 		 | new.this.isTerminated() == true
	 * @post   If the entity is in a world, The entity is removed properly from the world first.
	 * 		 | new.this.getWorld() == None
	 */
	@Raw
	@Override
	public void terminate() throws IllegalEntityException {
		if (this.getWorld() != null)
			this.getWorld().removeEntity(this);
		super.terminate();
	}
	

	
	
	
	/**
	 * Update <code>entity</code>'s position, assuming it moves <code>dt</code>
	 * seconds at its current velocity.
	 * 
	 * @param  	dt
	 * 		   	The amount of time that the entity should move forward.
	 * @post   	The entity moves forward by the amount of time dt, with the stored velocity and the acceleration.
	 * 			The new position is calculated in a deterministic way so that the speed will never exceed the max speed of the given entity.
	 *        |	xPosition = xPosition + xVelocity * dt + 1/2 * xAcceleration^2
	 *        | yPosition = yPosition + yVelocity * dt + 1/2 * yAcceleration^2
	 */
	@Raw
	void move(double dt) {

		//The defensive part of this function is only checked in the public code that calls this function: world.evolve()
		
		// Berekeningen die geen rekening houden met acceleratie.
		Vector2 positionFirst = this.getPosition();
		this.setPosition(Vector2.add(positionFirst, Vector2.multiply(this.getVelocityVector(), dt)));		
	}	
	
	/**
	 * Return the distance between <code>entity1</code> and <code>entity2</code>.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either entity should move such that both entities are adjacent. Note that the
	 * result must be negative if the entities overlap. The distance between a entity
	 * and itself is 0.
	 * 
	 * @param  entity1
	 * 		   The first entity to measure the distance from.
	 * @param  entity2
	 *         The second entity to measure the distance to.
	 * @return The distance between the two entities. This can be negative if the entities collide with eachother.
	 *       | result == pythagoras(entity1.getPosition() - entity2.getPosition()) - entity1.getRadius() - entity2.getRadius()
	 * @throws NullPointerException
	 * 		   If entity1 or entity2 is null.
	 */
	@Immutable
	public static double getDistanceBetween(Entity entity1, Entity entity2) throws NullPointerException {
		if (isNullOrTerminated(entity1) || isNullOrTerminated(entity2))
			throw new NullPointerException("entities cannot be null.");
		if (entity1 == entity2) // optimisation
			return 0;
		return Vector2.distance(entity1.position, entity2.position) - entity1.radius - entity2.radius;
	}

	/**
	 * Check whether <code>entity1</code> and <code>entity2</code> overlap. A entity
	 * always overlaps with itself.
	 * 
	 * @param  entity1
	 * 		   The first entity to measure the distance from.
	 * @param  entity2
	 *         The second entity to measure the distance to.
	 * @return True if the distance between the two entities is equal to or less than zero.
	 *       | centerDifference.pythagoras() <= 0.99*(entity1.getRadius()+entity2.getRadius());	//to avoid rounding issues
	 * @throws NullPointerException
	 * 		   If entity1 or entity2 is null.
	 */
	public static boolean overlap(Entity entity1, Entity entity2) throws NullPointerException {
		if (isNullOrTerminated(entity1) || isNullOrTerminated(entity2))
			throw new NullPointerException("entities cannot be null.");
		if (entity1 == entity2) { // optimisation
			// OGUtil.println("De entities in functie overlap zijn hetzelfde => geeft true");
			return true;
		}
		Vector2 centerDifference = new Vector2(entity1.getPosition().x - entity2.getPosition().x, entity1.getPosition().y - entity2.getPosition().y);
		return centerDifference.pythagoras() <= 0.99 * (entity1.getRadius() + entity2.getRadius());
	}

	/**
	 * Return the number of seconds until the first collision with the wall.
	 * @return The time until the first collision. Returns positive infinity if the entity never collides with the border of the world.
	 */
	public double getTimeToWallCollision() throws NoWorldException {		
		double velocityX = this.getVelocityVector().x;
		double velocityY = this.getVelocityVector().y;
		
		if (isNullOrTerminated(this.world) && !((velocityX == 0) && (velocityY == 0)))
			throw new NoWorldException();
		
		if ((velocityX == 0) && (velocityY == 0)) {
			return Double.POSITIVE_INFINITY;
		}

		// Berekeningen die geen rekening hoduen met acceleratie.

		double xCollisionTime;
		if (velocity.x == 0)
			xCollisionTime = Double.POSITIVE_INFINITY;
		else if (velocity.x > 0)
			xCollisionTime = (world.getWidth() - position.x - this.radius) / velocity.x;
		else {
			xCollisionTime = (position.x - this.radius) / -velocity.x;
		}

		double yCollisionTime;
		if (velocity.y == 0)
			yCollisionTime = Double.POSITIVE_INFINITY;
		else if (velocity.y > 0)
			yCollisionTime = (world.getHeight() - position.y - this.radius) / velocity.y;
		else
			yCollisionTime = (position.y - this.radius) / -velocity.y;

		return Math.max(0, Math.min(xCollisionTime, yCollisionTime));
	}

	/**
	 * Return the number of seconds until the first collision between
	 * <code>entity1</code> and <code>entity2</code>, or Double.POSITIVE_INFINITY if
	 * they never collide. A entity never collides with itself.
	 * 
	 * @param  entity1
	 * 		   The first entity to measure the distance from.
	 * @param  entity2
	 *         The second entity to measure the distance to.
	 * @return The time until collision of the two entities if the entities are going to collide.
	 *       | if (Dv * Dr < 0 || (d = (Dv*Dr) - (Dv*Dv)(Dr*Dr - sigma²)) < 0) result == positive infinity
	 *       | else result = -(Dv*Dr + sqrt(d)) / (Dv*Dv)
	 * @throws NullPointerException
	 * 		   entity1 or entity2 should not be null.
	 * @throws EntitiesOverlapException
	 * 		   The entities should not overlap already.
	 */
	public static double getTimeToCollision(Entity entity1, Entity entity2) throws NullPointerException, EntitiesOverlapException {

		// Berekeningen die geen rekening houden met acceleratie.

		if (isNullOrTerminated(entity1) || isNullOrTerminated(entity2))
			throw new NullPointerException("entities cannot be null.");
		if (overlap(entity1, entity2)) {
			OGUtil.println("---- ILLEGAL OVERLAP!!! ----");
			OGUtil.println(entity1);
			OGUtil.println(entity2);
			OGUtil.println(entity1.getPosition());
			OGUtil.println(entity2.getPosition());
			OGUtil.println(entity1.isTerminated());
			OGUtil.println(entity2.isTerminated());
			if (entity1 instanceof Bullet)
				OGUtil.println("bullet1 isloadedinparent: " + ((Bullet) entity1).isLoadedInMotherShip());
			if (entity2 instanceof Bullet)
				OGUtil.println("bullet2 isloadedinparent: " + ((Bullet) entity2).isLoadedInMotherShip());
			throw new EntitiesOverlapException();
		}

		double sigma = entity1.radius + entity2.radius;
		Vector2 Dr = Vector2.subtract(entity1.position, entity2.position);
		Vector2 Dv = Vector2.subtract(entity1.velocity, entity2.velocity);
		double vr = Vector2.dot(Dr, Dv);
		if (vr >= 0)
			return Double.POSITIVE_INFINITY;
		double rr = Vector2.dot(Dr, Dr);
		double vv = Vector2.dot(Dv, Dv);
		double d = vr * vr - vv * (rr - sigma * sigma);
		if (d <= 0)
			return Double.POSITIVE_INFINITY;
		return Math.max(0, -(vr + Math.sqrt(d)) / vv);
	}

	/**
	 * Return the first position where <code>entity1</code> and <code>entity2</code>
	 * collide, or <code>null</code> if they never collide. A entity never
	 * collides with itself.
	 * 
	 * The result of this method is either null or an array of length 2, where
	 * the element at index 0 represents the x-coordinate and the element at
	 * index 1 represents the y-coordinate.
	 * 
	 * @param  entity1
	 * 		   The first entity of the collision.
	 * @param  entity2
	 *         The second entity of the collision.
	 * @effect getCollisionPosition(entity1, entity2, getTimeToCollision(entity1, entity2))
	 * @return The position where the two entities will first touch.
	 * @return If the entities never collide, this function will return null.
	 * @throws NullPointerException
	 * 		   entity1 or entity2 should not be null.
	 * @throws EntitiesOverlapException
	 * 		   The entities should not overlap already.
	 */
	public static Vector2 getCollisionPosition(Entity entity1, Entity entity2) throws NullPointerException, EntitiesOverlapException {
		return getCollisionPosition(entity1, entity2, getTimeToCollision(entity1, entity2));
	}

	/**
	 * Return the position where entity1 and entity2 will collide at time Dt.
	 * 
	 * @param  entity1
	 * 		   The first entity of the collision.
	 * @param  entity2
	 *         The second entity of the collision.
	 * @param  Dt
	 * 		   Time of the collision.
	 * @effect First, the program calculates when the two entities are going to collide.
	 *         Then, the collision positions are calculated.
	 *         Lastly, an imaginary line is drawn between the centers of the two entities,
	 *         and it is calculated how far on that line the actual collision will happen based on the size of the entities.
	 *       | dt = getTimeToCollision(entity1, entity2)
	 *       | entity1NewPos = entity1.position + entity1.velocity * dt
	 *       | entity1NewPos = entity2.position + entity2.velocity * dt
	 *       | delta = entity2NewPos - entity1NewPos
	 *       | result == entity1NewPos + delta * (entity1.radius / (entity1.radius + entity2.radius))
	 * @return The position where the two entities will first touch.
	 * @return If the entities never collide, this function will return null.
	 */
	@Raw
	static Vector2 getCollisionPosition(Entity entity1, Entity entity2, double Dt) {
		if (Dt == Double.POSITIVE_INFINITY)
			return null;

		Vector2 entity1NewPos = Vector2.add(entity1.position, Vector2.multiply(entity1.velocity, Dt));
		Vector2 entity2NewPos = Vector2.add(entity2.position, Vector2.multiply(entity2.velocity, Dt));
		Vector2 delta = Vector2.subtract(entity2NewPos, entity1NewPos);
		return Vector2.add(entity1NewPos, Vector2.multiply(delta, entity1.radius / (entity1.radius + entity2.radius)));
	}

	/**
	 * Gets the coordinates of the collision of the entity with a wall, if there is any.
	 * 
	 * @return The position of the collision with the wall if there is a collision with a wall.
	 * @return Null if there is no collision with a wall.
	 */
	public Vector2 getWallCollisionPosition() {
		return getWallCollisionPosition(this.getTimeToWallCollision());
	}

	/**
	 * Gets the coordinates of the collision of the entity with a wall at given time.
	 * @return The position of the collision with the wall if there is a collision with a wall.
	 * @return Null if there is no collision with a wall.
	 */
	@Raw
	Vector2 getWallCollisionPosition(double Dt) {
		if (Dt == Double.POSITIVE_INFINITY) {
			return null;
		}
		Vector2 newpos = Vector2.add(this.getPosition(), Vector2.multiply(this.getVelocityVector(), Dt));
		if ((newpos.x - 1.01 * this.radius) <= 0)
			return new Vector2(0, newpos.y);
		if ((newpos.x + 1.01 * this.radius) >= this.getCollisionWorld().getWidth())
			return new Vector2(this.getCollisionWorld().getWidth(), newpos.y);
		if ((newpos.y - 1.01 * this.radius) <= 0)
			return new Vector2(newpos.x, 0);
		if ((newpos.y + 1.01 * this.radius) >= this.getCollisionWorld().getHeight())
			return new Vector2(newpos.x, this.getCollisionWorld().getHeight());
		return null;
	}

	
	/**
	 * Variable registering if the entity should have collision in a world or not.
	 */
	boolean collides = true;
	
	/**
	 * Set if the entity can collide or not.
	 * @param collidable
	 * 	      True if the entity should be able to collide in a world, otherwise false.
	 * @post  Sets collides to true if and only if the entity is collidable.
	 * 		| new.this.collides = collidable
	 */
	@Raw
	@Basic
	void setCollision(boolean collidable) {
		this.collides = collidable;
	}

	
	/**
	 * Get if the entity can collide or not.
	 * @return True if the entity can collide in a world, otherwise false.
	 * 		 | Return this.collides
	 */
	@Raw
	@Basic
	public boolean hasCollision() {
		return this.collides;
	}
	
	
	/**
	 * Variable registering the coordinates of the entity.
	 */
	private World world;

	/**
	 *  Returns the world where the entity lives in.
	 *  
	 *  @return The world if the entity if the entity is collidable.
	 *  @return null otherwise
	 */
	@Raw
	public World getCollisionWorld() {
		//entity is only actually located in the world if it has collision.
		return this.collides ? getWorld() : null;
	}
	

	/**
	 *  Returns the world where the entity lives in, if the entity has collision.
	 *  
	 *  @return the world if the entity if the entity is collidable.
	 *  @return null otherwise.
	 */
	@Raw
	@Basic
	public World getWorld() {
		return world;
	}

	/**
	 * Set the world of this entity to the given world.
	 * @param  world
	 * 		   The new world for this entity.
	 */
	@Raw
	@Basic
	void setWorld(World world) {
		this.world = world;
	}

	/**
	 * Mirrors the position to put it within the bounds of the world.
	 * Only works for small out of bounds. (significantly less than 1 world width/height)
	 * 
	 * @effect set the position of the entity to the mirror position in the world
	 *		| this.setPosition(mirror position)
	 */
	@Raw
	void mirrorPositionWall() {

		Vector2 pos = this.getPosition();
		if (pos.x < this.getRadius())
			pos = new Vector2(2 * this.getRadius() - pos.x, pos.y);
		else if (pos.x > this.getCollisionWorld().getWidth() - this.getRadius())
			pos = new Vector2(2 * (this.getCollisionWorld().getWidth() - this.getRadius()) - pos.x, pos.y);
		if (pos.y < this.getRadius())
			pos = new Vector2(pos.x, 2 * this.getRadius() - pos.y);
		else if (pos.y > this.getCollisionWorld().getHeight() - this.getRadius())
			pos = new Vector2(pos.x, 2 * (this.getCollisionWorld().getHeight() - this.getRadius()) - pos.y);
		this.setPosition(pos);
	}

	/**
	 * Internal function that bounces the entity off a wall.
	 * @effect Changes the velocity when a entity bounces against the wall of the world.
	 * 		 | this.setVelocity(velocity after wall bounce)
	 */
	@Raw
	void collideWithWall() {
		if ((this.getPosition().x - 1.01 * this.radius) <= 0 || (this.getPosition().x + 1.01 * this.radius) >= this.getCollisionWorld().getWidth())
			this.setVelocity(new Vector2(-this.getVelocityVector().x, this.getVelocityVector().y));
		if ((this.getPosition().y - 1.01 * this.radius) <= 0 || (this.getPosition().y + 1.01 * this.radius) >= this.getCollisionWorld().getHeight())
			this.setVelocity(new Vector2(this.getVelocityVector().x, -this.getVelocityVector().y));
	}
	
	
	
	
	
	

	/**
	 * Recalculates the velocity of the two entities, when they bounce
	 * 
	 * @param first
	 *        The first entity that collides.
	 * @param second
	 *        The second entity that collides.
	 * @post  The Velocity of the two entities will be updated according to the laws of physics.
	 * 		| see implementation
	 */
	@Raw
	static void bounce(Entity first, Entity second) {

		double sigma = first.getRadius() + second.getRadius();
		Vector2 J = Vector2.multiply(Vector2.subtract(first.getPosition(), second.getPosition()),
				2 * first.getMass() * second.getMass()
						* Vector2.dot(Vector2.subtract(first.getVelocityVector(), second.getVelocityVector()), Vector2.subtract(first.getPosition(), second.getPosition()))
						/ (sigma * sigma * (first.getMass() + second.getMass())));

		first.setVelocity(Vector2.subtract(first.getVelocityVector(), Vector2.divide(J, first.getMass())));
		second.setVelocity(Vector2.add(second.getVelocityVector(), Vector2.divide(J, second.getMass())));
	}

	
	/**
	 * Kills the entity.
	 * 
	 * @effect Entity is terminated.
	 */
	void die() {
		this.terminate();
	}
	/**
	 * Function that will return the nearest entity of one of the given types.
	 * @param filter
	 * 		  Lamdba filter function. Optional.
	 * @param classes
	 * 		  All the valid types of the entity that will be returned. Optional: if no class types are passed, all class types will be valid.
	 * @return The nearest entity of one of the given types.
	 * 		  | see implementation
	 */
		
	@SafeVarargs public final Entity getNearestEntity(Class<? extends Entity>... classes) { return getNearestEntity(e -> true, classes); }
	@SafeVarargs public final Entity getNearestEntity(Predicate<Entity> filter, Class<? extends Entity>... classes) {
		double closestDistance = Double.POSITIVE_INFINITY;
		Entity closestEntity = null;
		Set<? extends Entity> entities = this.getWorld().getAllEntities(classes.length > 0 ? entity -> { for (Class<? extends Entity> c : classes) if (c.isInstance(entity)) return filter.test(entity); return false; } : entity -> filter.test(entity) );
		for (Entity entity : entities) {
			double newDistance = getDistanceBetween(this, entity);
			if (newDistance < closestDistance) {
				closestEntity = entity;
				closestDistance = newDistance;
			}
		}
		return closestEntity;
	}
	
}