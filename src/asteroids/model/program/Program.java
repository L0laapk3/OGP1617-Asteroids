package asteroids.model.program;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.ProgramException;
import asteroids.model.Ship;
import asteroids.model.program.expression.FunctionContainer;
import asteroids.model.program.statement.Statement;



//TODO: WERK

public class Program {

	private boolean completed = false;
	
	public boolean isCompleted() {
		return completed;
	}
	
	public Object getReturnValue() {
		return main.getReturnValue();
	}
	
	private final FunctionContainer main;

	private Ship ship = null;

	public Ship getShip() throws InvalidShipException {
		if (getShip() == null)
			throw new InvalidShipException();
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	public Program(Statement statement) {
		main = new FunctionContainer(statement);
	}
	
	
	private double totalTime = 0;
	private double totalTimeTruncated = 0;
	public void run(double dt) throws ProgramException {
		totalTime += dt;
		double requiredTime = main.getRequiredTime();
		while (requiredTime + totalTimeTruncated <= totalTime && !completed) {
			completed = main.step(this) || main.getIsReturned();
			totalTimeTruncated += requiredTime;
			requiredTime = main.getRequiredTime();
		}
	}
}
