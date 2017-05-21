package asteroids.model;

import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;
import asteroids.util.OGUtil;
import asteroids.util.Vector2;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;



/**
 * A class to define advanced entities. Advanced entities are the same as basic entities, except they can have an orientation and an acceleration.
 * 
 * @effect   Entity
 * @invar    The initial orientation of the entity must be between 0 and 2*PI.
 *         | isValidOrientation(getOrientation())
 * 
 * @version  1.0
 * @author   Kris Keersmaekers
 * @author   Rik Pauwels
 */

public abstract class AdvancedEntity extends Entity {


	/**
	 * Variable registering the orientation of the entity.
	 */
	private double orientation;
	
	


	/**
	 * Return the orientation of <code>entity</code> (in radians).
	 * 
	 * @return The orientation. This double will always be between 0 and 2*PI.
	 */
	@Raw
	@Basic
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
		assert orientation >= 0 && orientation <= 2 * Math.PI;
		if (isValidOrientation(orientation)) {
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
	@Raw
	@Basic
	@Immutable
	public static boolean isValidOrientation(double orientation) {
		return orientation >= 0 && orientation <= 2 * Math.PI;
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
	@Raw
	public void turn(double angle) {
		assert !((0 > angle) || (angle >= 2 * Math.PI));

		this.orientation += angle;
		//this.orientation = (this.orientation + angle) % (2 * Math.PI); TODO weggedaan 
		//if (this.orientation < 0) // due to the way java calculates %, we need to add 2*PI to keep this number positive.
		//	this.orientation += (2 * Math.PI);
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
	@Raw
	public void thrust(double amount) {
		if (OGUtil.isInvalidNumber(amount) || amount < 0)
			return;
		setVelocity(Vector2.add(this.getVelocityVector(), Vector2.fromPolar(this.getOrientation(), amount)));
	}

	
	
	
	

	
	/**
	 * returns the trust force of this entity.
	 * @return The trust force of this entity.
	 * 		 | this.acceleration
	 */
	@Raw
	@Basic
	public double getAcceleration() {
		return thrustForce / this.getMass();
	}

	/**
	 * returns the trust force vector of this entity.
	 * @return The trust force vector of this entity.
	 * 		 | see implementation
	 */
	@Raw
	public Vector2 getAccelerationVector() {
		return Vector2.fromPolar(this.getOrientation(), this.getAcceleration());
	}
	
	/**
	 * variable to declare the trust force of the given entity
	 */
	private double thrustForce = 0;

	/**
	 * Sets the trust force of this entity to the given acceleration.
	 * @param thrustForce
	 * 		  The new trust force .
	 * @post  The given trust force becomes the new acceleration from this entity.
	 * 		| new.getThrust() = thrustForce
	 */
	@Raw
	@Basic
	void setThrust(double thrustForce) {
		this.thrustForce = thrustForce;
	}

	/**
	 * Gets the current trust force of the entity.
	 * @return The trust force.
	 */
	@Raw
	@Basic
	public double getThrust() {
		return this.thrustForce;
	}

	
	

	/*
	 * public void move(double dt) throws IllegalArgumentException, NegativeTimeException {
	 * 
	 * 
	 * // Berekingen die rekening houden met acceleratie.
	 * 
	 * OGUtil.throwErrorIfInvalidNumbers(dt); if (!isValidDeltaTime(dt)) throw new NegativeTimeException();
	 * 
	 * double dtPossible; if ((this.getAcceleration().x==0) && (this.getAcceleration().y==0)) { dtPossible = dt; } else { double a = Math.pow(this.getAcceleration().x, 2) + Math.pow(this.getAcceleration().y, 2); double b = -2*this.velocity.x*this.getAcceleration().x-2*this.velocity.y*this.getAcceleration().y; double c = Math.pow(this.velocity.x,2)+Math.pow(this.velocity.y,2)-Math.pow(this.maxSpeed, 2); dtPossible = Math.min(dt, Math.max(((-b+Math.sqrt(b*b-4*a*c))/2), (-b-Math.sqrt(b*b-4*a*c))/2)); assert dtPossible >= 0; }
	 * 
	 * this.position = Vector2.add(Vector2.add(this.position, Vector2.multiply(this.velocity, dt)), Vector2.multiply(getAcceleration(), 1/2*Math.pow(dtPossible, 2))); this.velocity = Vector2.add(this.velocity, Vector2.multiply(this.getAcceleration(), dtPossible));
	 * this.setVelocity(Vector2.add(this.getVelocity(), Vector2.multiply(this.getAccelerationVector(), dt)));
	 * }
	 */
	
	
	
	


	/**
	 * Applies acceleration to entity.
	 * @param  Dt
	 * 		   For how much time acceleration needs to be applied to the entity.
	 * @effect Sets the velocity to the new velocity which takes into account the acceleration of the entity.
	 * 		 | this.setVelocity(new velocity)
	 */
	@Raw
	public void accelerate(double Dt) {

		if (this.getAcceleration() == 0)
			return; // optimalisation
		this.setVelocity(Vector2.add(this.getVelocityVector(), Vector2.multiply(this.getAccelerationVector(), Dt)));
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
	 * @param  mass
	 * 		   The mass of the newly created entity
	 * @param  MIN_RADIUS
	 * 		   sets MIN_RADIUS constant.
	 * @pre    The orientation parameter must be between 0 and 2*PI.
	 * @effect new Entity()
	 * @post   The orientation of this new entity equal to the given orientation.
	 * 		 | new.this.getOrientation == orientation
	 * @throws InvalidPositionException
	 * 		   The coordinates of the entity should not be infinite or NaN.
	 * @throws IllegalArgumentException
	 * 		   The radius of the entity should not be infinite.
	 * @throws IllegalArgumentException
	 * 		   The radius of the entity should not be NaN.
	 * @throws InvalidRadiusException
	 * 		   The radius must not be smaller than MIN_RADIUS.	
	 */
	AdvancedEntity(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass, double MIN_RADIUS)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, mass, MIN_RADIUS);
		


		// NOMINAL (We still have to do a defensive check because of the given tests) 
		//TODO ik heb het zo moeten doen anders was het ofwel fout volgens de tests ofwel volgens de opgaven 
		if ((orientation < 0) || (orientation > 2 * Math.PI)) {
			throw new IllegalArgumentException ("the orientation can not be negative");
		}
		assert orientation >= 0 && orientation <= 2 * Math.PI;
		this.orientation = orientation;
		
		
		// TODO Auto-generated constructor stub
	}

}
