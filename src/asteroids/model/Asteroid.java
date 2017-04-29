package asteroids.model;

import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;

public class Asteroid extends MinorPlanet {

	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {
		super(x, y, xVelocity, yVelocity, radius, orientation, mass);
		// TODO Auto-generated constructor stub
	}

}
