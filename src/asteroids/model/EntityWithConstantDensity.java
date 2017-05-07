package asteroids.model;

import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class EntityWithConstantDensity extends Entity {

	/**
	 * Parameter for mass density.
	 */
	final public double RHO;
	
	/**
	 * Gets the mass density of this entity.
	 */
	@Raw
	@Basic
	public double getRho() {
		return this.RHO;
	}
	
	
	

	/**
	 * Create a new entity with the given position, velocity, radius and
	 * orientation, and constant mass density.
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
	 * @param  RHO
	 * 		   The mass density of the newly created entity.
	 * @param  MIN_RADIUS
	 * 		   sets MIN_RADIUS constant.
	 * @pre    The orientation parameter must be between 0 and 2*PI.
	 * @effect new Entity()
	 * @throws InvalidPositionException
	 * 		   The coordinates of the entity should not be infinite or NaN.
	 * @throws IllegalArgumentException
	 * 		   The radius of the entity should not be infinite.
	 * @throws IllegalArgumentException
	 * 		   The radius of the entity should not be NaN.
	 * @throws InvalidRadiusException
	 * 		   The radius must not be smaller than MIN_RADIUS.	
	 */
	public EntityWithConstantDensity(double x, double y, double xVelocity, double yVelocity, double radius, double RHO, double MIN_RADIUS)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, calculateBassMass(RHO, radius), MIN_RADIUS);
		this.RHO = RHO;
	}


	

	/**
	 * Function that will update the mass of a planetoid based on the given density.
	 * @param  rho
	 *  	   density to use to calculate the bass mass.
	 * @effect sets the bass mass for this entity based on given density.
	 */
	@Raw
	void updateBaseMass() {
		this.baseMass = calculateBassMass(RHO, this.getRadius());
	}
	
	

	/**
	 * Sets the radius of this entity to the given radius.
	 * 
	 * @param  radius
	 * 		   The new radius of the entity.
	 * @effect Entity.setRadius()
	 * @effect this.updateBassMass();
	 */
	@Raw
	@Basic
	void setRadius(double radius) {
		super.setRadius(radius);
		this.updateBaseMass();
	}
}
