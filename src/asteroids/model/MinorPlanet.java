package asteroids.model;

import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;

public abstract class MinorPlanet extends Entity {

	public MinorPlanet(double x, double y, double xVelocity, double yVelocity, double radius, double rho)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {		
		super(x, y, xVelocity, yVelocity, radius, calculateBassMass(rho, radius));
		// TODO Auto-generated constructor stub
		this.setMinRadius(5);
	}

	
	
}
