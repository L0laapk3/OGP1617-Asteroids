package asteroids.model;

import asteroids.exceptions.*;
import asteroids.util.*;
import be.kuleuven.cs.som.annotate.*;

public abstract class Entity {
		

		/**
		 * Constant for the maximum velocity.
		 */
		private final double maxSpeed;
		
		
		/**
		 * Set the maxSpeed of this entity to the given maxSpeed
		 * 
		 * @param 	maxSpeed
		 * 			The new maxSpeed for this entity
		 * @post 	The maxSpeed of this entity is the same as the given maxSpeed
		 * 		  | new.getMaxSpeed() == maxSpeed
		 */
		//TODO VRAAG: in constructor: final
		/*@Raw
		private void setMaxSpeed(double maxSpeed) {
			if (Entity.isValidMaxSpeed(maxSpeed)) {
				this.maxSpeed = maxSpeed;
			}
		}*/
		
		/**
		 *  Returns the maxSpeed of the entity
		 */
		@Raw
		public double getMaxSpeed() {
			return this.maxSpeed;
		}
		
		/**
		 * Checks whether the given maxSpeed is a valid maxSpeed.
		 * 
		 * @param 	maxSpeed
		 * 			The maxSpeed to be checked.
		 * @return	True if and only if the maxSpeed is 0 or more and less than infinity.
		 * 		  | result == (maxSpeed>=0) && (maxSpeed<infinity) 
		 */
		@Raw
		public static boolean isValidMaxSpeed(double maxSpeed) {
			if ((maxSpeed >= 0) && (maxSpeed < Double.POSITIVE_INFINITY)) {
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
		 * Variable registering the speed of the entity.
		 */
		private Vector2 velocity;
		
		/**
		 * 
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
		private void setVelocity(double xVelocity, double yVelocity) {
			double speed = new Vector2(xVelocity, yVelocity).pythagoras();
			if (speed > this.maxSpeed) {
				double scale = this.maxSpeed / speed;
				this.velocity = new Vector2(xVelocity * scale, yVelocity * scale);
					//System.out.println("[WARN] speed exceeded this.maxSpeed while creating entity."); //for debugging possible problems in future code
			} else
				this.velocity = new Vector2(xVelocity, yVelocity);
		}
		
		
		
		/**
		 * Return the velocity of entity.
		 * @note   This is written in a total fashion.
		 */
		@Basic
		@Raw
		public Vector2 getVelocity() {
			return this.velocity;
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
		public static boolean isValidVelocity(Vector2 velocity) {
			if ((velocity.x >= 0) && (velocity.y >= 0) && (velocity.x < Double.POSITIVE_INFINITY) && (velocity.y < Double.POSITIVE_INFINITY)) {
				return true;
			} else {
				return false;
			}
		}	
		

		/**
		 * Variable registering the size of the entity.
		 */
		private final double radius;
		
		private final static double MIN_RADIUS = 10;
		
		/**
		 * Return the radius of entity.
		 * 
		 * @return The radius.
		 */
		@Basic
		@Immutable
		@Raw
		public double getRadius() {
			return this.radius;
		}
		
		/**
		 * Check whether the radius is valid for a entity.
		 * 
		 * @param  radius
		 * 		   The radius to check.
		 * @return True if and only if given radius is not smaller than MIN_RADIUS.
		 *       | result == (radius >= MIN_RADIUS)
		 */
		public boolean isValidRadius(double radius) {
			return radius >= MIN_RADIUS;
		}
		

		/**
		 * Variable registering the orientation of the entity.
		 */
		private double orientation;
		
		/**
		 * Return the orientation of <code>entity</code> (in radians).
		 * 
		 * @return The orientation. This double will always be between 0 and 2*PI.
		 */
		@Basic
		@Raw
		public double getOrientation() {
			return this.orientation;
		}
		
		/**
		 * Set the maxSpeed of this entity to the given maxSpeed
		 * 
		 * @param 	maxSpeed
		 * 			The new maxSpeed for this entity
		 * @post 	The maxSpeed of this entity is the same as the given maxSpeed
		 * 		  | new.getMaxSpeed() == maxSpeed
		 */
		@Raw
		public void setOrientation(double orientation) {
			assert orientation >= 0 && orientation <= 2*Math.PI;
			if (Entity.isValidOrientation(orientation)) {
				this.orientation = orientation;
			}
		}
		
		/**
		 * Check whether the orientation is valid for a entity.
		 * 
		 * @param  orientation
		 * 		   The orientation to check.
		 * @return True if and only if the given orientation is between 0 and 2*PI
		 *       | result == (orientation >= 0 && orientation <= 2*Math.PI)
		 */
		public static boolean isValidOrientation(double orientation) {
			return orientation >= 0 && orientation <= 2*Math.PI;
		}
	
		
		/**
		 * Create a new entity with the given position, velocity, radius and
		 * orientation (in radians).
		 * 
		 * @param  x
		 * 	       The x coordinate where the new entity has to be created.
		 * @param  y
		 *         The y coordinate where the new entity has to be created.
		 * @param  xVelocity
		 * 		   The initial speed in the x direction of the new entity.
		 * @param  yVelocity
		 * 		   The initial speed in the y direction of the new entity.
		 * @param  orientation
		 * 		   The direction that the new entity is initially pointed at.
		 * @param  radius
		 * 		   The size of the newly created entity.
		 * @pre    The orientation parameter must be between 0 and 2*PI.
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
		 * @post   The orientation of this new entity equal to the given orientation.
		 * @post   The radius of this new entity is equal to the given radius.
		 * @throws InvalidPositionException
		 * 		   The coordinates of the entity should not be infinite or NaN.
		 * @throws IllegalArgumentException
		 * 		   The radius of the entity should not be infinite.
		 * @throws IllegalArgumentException
		 * 		   The radius of the entity should not be NaN.
		 * @throws InvalidRadiusException
		 * 		   The radius must not be smaller than MIN_RADIUS.
		 */
		public Entity(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass) throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
			
			//setMaxSpeed(300000);
			this.maxSpeed = 300000;
			
			
			//DEFENSIVE
			//Variables of type double can never be null, so we do not have to check if they are null.
			Vector2 pos = new Vector2(x, y);
			if (!isValidPosition(pos))
				throw new InvalidPositionException();
			this.position = pos;
			
			
			
			
			//TOTAL
			if (OGUtil.isInvalidNumber(xVelocity) || OGUtil.isInvalidNumber(yVelocity))
				this.velocity = new Vector2(0, 0);
			else {
				setVelocity(xVelocity, yVelocity);
			}
			
			
			//NOMINAL
			assert orientation >= 0 && orientation <= 2*Math.PI;
			this.orientation = orientation;
			
			
			
			
			//DEFENSIVE
			OGUtil.throwErrorIfInvalidNumbers(radius);
			if (radius < MIN_RADIUS)
				throw new InvalidRadiusException();
			this.radius = radius;
			
			
			
			
			//TODO: DEAL WITH MASS
		}

		
		
		
		
		
		/**
		 * Variable reflecting whether or not the instance is terminated.
		 */
		private boolean isTerminated = false;
		
		
		/**
		 * Terminate this world.
		 *
		 * @post   The instance is terminated.
		 * @post   Each of the entities of the instance is terminated.
		 */
		public void terminate() {
			if (!isTerminated()) {
				this.isTerminated = true;	//TODO alle entities daarin terminaten
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

		
		
		
		
		
		
		




		/**
		 * variable to declare the acceleration of the given entity
		 */		
		private Vector2 acceleration = new Vector2(0,0);

		public Vector2 getAcceleration() {
			return acceleration;
		}

		public void setAcceleration(Vector2 acceleration) {
			this.acceleration = acceleration;
		}
		
		
		
		
		
		
		
			

		/**
		 * Update <code>entity</code>'s position, assuming it moves <code>dt</code>
		 * seconds at its current velocity.
		 * 
		 * @param  	dt
		 * 		   	The amount of time that the entity should move forward.
		 * @post   	The entity moves forward by the amount of time dt, with the stored velocity and the acceleration.
		 * 			The new position is calculated in a deterministic way so that the speed will never exceed the max speed of the given ship.
		 *        |	xPosition = xPosition + xVelocity * dt + 1/2 * xAcceleration^2
		 *        | yPosition = yPosition + yVelocity * dt + 1/2 * yAcceleration^2
		 * @throws 	IllegalArgumentException
		 * 		   	The time of the action should not be infinite.
		 * @throws 	IllegalArgumentException
		 * 		   	The time of the action should not be NaN.
		 * @throws 	NegativeTimeException
		 * 		   	The time of the action should be positive. 
		 * @note   	This is written in a defensive manner.
		 */
		public void move(double dt) throws IllegalArgumentException, NegativeTimeException {
			OGUtil.throwErrorIfInvalidNumbers(dt);
			if (!isValidDeltaTime(dt))
				throw new NegativeTimeException();
			
			double dtPossible;
			if ((this.getAcceleration().x==0) && (this.getAcceleration().y==0)) {
				dtPossible = dt;
			} else {
				double a = Math.pow(this.getAcceleration().x, 2) + Math.pow(this.getAcceleration().y, 2);
				double b = -2*this.velocity.x*this.getAcceleration().x-2*this.velocity.y*this.getAcceleration().y;
				double c = Math.pow(this.velocity.x,2)+Math.pow(this.velocity.y,2)-Math.pow(this.maxSpeed, 2);
				dtPossible = Math.min(dt, Math.max(((-b+Math.sqrt(b*b-4*a*c))/2), (-b-Math.sqrt(b*b-4*a*c))/2));
				assert dtPossible >= 0;
			}
			
			this.position = Vector2.add(Vector2.add(this.position, Vector2.multiply(this.velocity, dt)), Vector2.multiply(getAcceleration(), 1/2*Math.pow(dtPossible, 2)));	
			this.velocity = Vector2.add(this.velocity, Vector2.multiply(this.getAcceleration(), dtPossible));

		}
		
		
		/**
		 * Check whether the time to move the entity forward with is valid.
		 * 
		 * @param dt
		 * 		  The time to check.
		 * @return True if and only if dt equals to or is bigger than 0.
		 *       | result == (dt >= 0)
		 */
		public boolean isValidDeltaTime(double dt) {
			return dt >= 0;
		}
		
		
		
		/**
		 * Update the direction of <codeentityp</code> by adding <code>angle</code>
		 * (in radians) to its current direction. <code>angle</code> may be
		 * negative.
		 * 
		 * @param  angle
		 * 	       The angle that the entity has to turn.
		 * @pre    The angle must not be Infinity or NaN.
		 * @effect The angle will be added to the orientation. Then the orientation will be
		 *         modulated in such a way that the resulting orientation is between 0 and 2*PI;
		 * 	     | orientation = (orientation + angle) % 2*PI
		 *       | if (orientation < 0)
		 *       |     orientation += 2*Math.PI
		 * @note   This is written in a nominally manner.
		 * 
		 */
		
		public void turn(double angle) {
			this.orientation = (this.orientation + angle) % (2*Math.PI);
			if (this.orientation < 0) //due to the way java calculates %, we need to add 2*PI to keep this number positive.
				this.orientation += (2*Math.PI); //TODO: SHITVRAAG MOET SETTER GEBRUIEK OFNIE OFWA????
		}
		
		
		
		
		
		/**
		 * Update <code>entity</code>'s velocity based on its current velocity, its
		 * direction and the given <code>amount</code>.
		 * 
		 * @param  amount
		 * 		   The amount that the entity should accelerate.
		 * @post   If the given amount is NaN or infinite, it will skip the calculations as zero acceleration would imply no change in speed.
		 *       | if (isInvalidNumber(amount))
		 *		 |     return
		 * @post   If the amount is below zero, it will skip the calculations as zero acceleration would imply no change in speed.
		 *       | if (amount < 0)
		 *		 |     return
		 * @post   If the total velocity exceeds this.maxSpeed, the velocity will be modified so that the direction of the velocity remains
		 *         the same, but the total velocity will be this.maxSpeed.
		 *       | speed = pythagoras(xVelocity, yVelocity)
		 *       | if (speed > this.maxSpeed)
		 *       |     scale = this.maxSpeed / speed
		 *       |     xVelocity = xVelocity * scale
		 *       |     yVelocity = yVelocity * scale
		 * @note   This is written in a total manner.
		 */
		public void thrust(double amount) {
			if (OGUtil.isInvalidNumber(amount) || amount < 0)
				return;
			double xVelocity = this.velocity.x + amount * Math.cos(this.orientation);
			double yVelocity = this.velocity.y + amount * Math.sin(this.orientation);
			setVelocity(xVelocity, yVelocity);
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
			if (entity1 == null || entity2 == null)
				throw new NullPointerException("entities cannot be null.");
			if (entity1 == entity2) //optimisation
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
		 *       | getDistanceBetween(entity1, entity2) <= 0
		 * @throws NullPointerException
		 * 		   If entity1 or entity2 is null.
		 */
		public static boolean overlap(Entity entity1, Entity entity2) throws NullPointerException {
			if (entity1 == null || entity2 == null)
				throw new NullPointerException("entities cannot be null.");
			if (entity1 == entity2) //optimisation
				return true;
			return getDistanceBetween(entity1, entity2) <= 0;
		}

		
		
		

		/**
		 * Return the number of seconds until the first collision with the wall.
		 * @return The time until the first collision. Returns positive infinity if the ship never collides with the border of the world.
		 * 	     | 
		 */
		public double getTimeToWallCollision() {
			double xCollisionTime;
			if (velocity.x == 0)
				xCollisionTime = Double.POSITIVE_INFINITY;
			else if (velocity.x > 0)
				xCollisionTime = (world.getWidth() - position.x) / velocity.x;
			else
				xCollisionTime = position.x / velocity.x;

			double yCollisionTime;
			if (velocity.y == 0)
				yCollisionTime = Double.POSITIVE_INFINITY;
			else if (velocity.y > 0)
				yCollisionTime = (world.getWidth() - position.y) / velocity.y;
			else
				yCollisionTime = position.y / velocity.y;
			
			return Math.min(xCollisionTime, yCollisionTime);
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
		 *       | if (Dv * Dr < 0 || (d = (Dv*Dr) - (Dv*Dv)(Dr*Dr - sigma�)) < 0) result == positive infinity
		 *       | else result = -(Dv*Dr + sqrt(d)) / (Dv*Dv)
		 * @throws NullPointerException
		 * 		   entity1 or entity2 should not be null.
		 * @throws EntitiesOverlapException
		 * 		   The entities should not overlap already.
		 */
		public static double getTimeToCollision(Entity entity1, Entity entity2) throws NullPointerException, EntitiesOverlapException {			
			
			
			//TODO: AFRONDINGSSSHIT p9!!!!!
			
			if (entity1 == null || entity2 == null)
				throw new NullPointerException("entities cannot be null.");
			if (overlap(entity1, entity2))
				throw new EntitiesOverlapException("entities overlap");
			
			double sigma = entity1.radius + entity2.radius;
			Vector2 Dr = Vector2.subtract(entity1.position, entity2.position);
			Vector2 Dv = Vector2.subtract(entity1.velocity, entity2.velocity);
			double vr = Vector2.dot(Dr, Dv);
			if (vr >= 0)
				return Double.POSITIVE_INFINITY;
			double rr = Vector2.dot(Dr, Dr);
			double vv = Vector2.dot(Dv, Dv);
			double d = vr*vr - vv*(rr - sigma*sigma);
			if (d <= 0)
				return Double.POSITIVE_INFINITY;
			return -(vr + Math.sqrt(d)) / vv;	
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
		 * @effect First, the program calculates when the two entities are going to collide.
		 *         Then, the collision positions are calculated.
		 *         Lastly, an imaginary line is drawn between the centers of the two entities,
		 *         and it is calculated how far on that line the actual collision will happen based on the size of the entities.
		 *       | dt = getTimeToCollision(entity1, entity2)
		 *       | entity1NewPos = entity1.position + entity1.velocity * dt
		 *       | entity1NewPos = entity2.position + entity2.velocity * dt
		 *       | delta = entity2NewPos - entity1NewPos
		 *       | result == entity1NewPos + delta * (entity1.radius / (entity1.radius + entity2.radius))
		 * @return The position where the two entities will first touch. If the entities never collide, this function will return null.
		 * @throws NullPointerException
		 * 		   entity1 or entity2 should not be null.
		 * @throws EntitiesOverlapException
		 * 		   The entities should not overlap already.
		 */
		public static Vector2 getCollisionPosition(Entity entity1, Entity entity2) throws NullPointerException, EntitiesOverlapException {
			double dt = getTimeToCollision(entity1, entity2); //will throw error if entities overlap as expected
			if (dt == Double.POSITIVE_INFINITY)
				return null;
			Vector2 entity1NewPos = Vector2.add(entity1.position, Vector2.multiply(entity1.velocity, dt));
			Vector2 entity2NewPos = Vector2.add(entity2.position, Vector2.multiply(entity2.velocity, dt));
			Vector2 delta = Vector2.subtract(entity2NewPos, entity1NewPos);
			return Vector2.add(entity1NewPos, Vector2.multiply(delta, entity1.radius / (entity1.radius + entity2.radius)));
		}


	
	/**
	 * Returns the position of the entity.
	 */
	@Raw
	public Vector2 getPosition() {
		return this.position;
	}
	/**
	 * Set the position for this entity.
	 * @param position
	 * @post  The position of this entity is the same as the given position.
	 */
	@Raw
	private void setPosition(Vector2 position) {
		this.position = position;
	}
	/*
	 * Checks whether the position is valid for a entity.
	 * 
	 * @param position
	 * 		  A Vector2 object containing the position to check.
	 * @return True if and only if both the x and y coordinate of the given position are valid numbers: They cannot be NaN or infinity.
	 *       | result == (!isInvalidNumber(position.x) && !isInvalidNumber(position.y))
	 */
	public boolean isValidPosition(Vector2 position) {
		return !OGUtil.isInvalidNumber(position.x) && !OGUtil.isInvalidNumber(position.y);
	}
	
	
	
	
	

	//TODO: do stuff with world? maybe
	private World world;
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	

	public void collideWithWall() {
		// TODO Auto-generated method stub
		
	}

}