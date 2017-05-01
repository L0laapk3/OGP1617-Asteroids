package asteroids.model;

import asteroids.exceptions.InvalidPositionException;
import asteroids.exceptions.InvalidRadiusException;

public abstract class MinorPlanet extends Entity {

	public MinorPlanet(double x, double y, double xVelocity, double yVelocity, double radius, double rho)
			throws IllegalArgumentException, InvalidRadiusException, InvalidPositionException {		
		super(x, y, xVelocity, yVelocity, radius, calculateBassMass(rho, radius));
		// TODO Auto-generated constructor stub
		
		//TODO dit is volgens mij fout gedaan in "bullets" want daar kan een bullet bestaan die een te kleine radius heeft omdat de grens pas later wordt toegevoegd
		this.setMinRadius(5);
		if (this.getRadius() < this.getMinRadius()) {
			this.terminate();
		}
	}
	
}
