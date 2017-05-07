package asteroids.model;

import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;

public abstract class MinorPlanet extends EntityWithConstantDensity {	
	
	private final static double MIN_RADIUS_MINORPLANET = 5;
	
	public MinorPlanet(double x, double y, double xVelocity, double yVelocity, double radius, double rho)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {		
		super(x, y, xVelocity, yVelocity, radius, rho, MIN_RADIUS_MINORPLANET);
	}
	
}
