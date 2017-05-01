package asteroids.model;

import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;

public class Asteroid extends MinorPlanet {

	
	/**
	 * Function to make a new Asteroid.
	 * @param  x
	 * 	       The x coordinate where the new Asteroid has to be created.
	 * @param  y
	 *         The y coordinate where the new Asteroid has to be created.
	 * @param  xVelocity
	 * 		   The initial speed in the x direction of the new Asteroid.
	 * @param  yVelocity
	 * 		   The initial speed in the y direction of the new Asteroid.
	 * @param  radius
	 * 		   The size of the newly created Asteroid.
	 * @effect This new Asteroid is initialized as a new MinorPlanet with given position, velocity and radius.
	 * 		 | super(x,y,xVelocity,yVelocity,radius,MASS_DENSITY_ASTEROID)
	 */
	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, MASS_DENSITY_ASTEROID);
		// TODO Auto-generated constructor stub
		
		
	}
	/**
	 * Variable holding the mass density of an asteroid.
	 */
	private final static double MASS_DENSITY_ASTEROID = 2.65E12;
}
