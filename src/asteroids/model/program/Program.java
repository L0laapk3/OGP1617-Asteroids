package asteroids.model.program;

import java.util.ArrayList;
import java.util.List;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.ProgramException;
import asteroids.exceptions.TooLongWithoutYieldingException;
import asteroids.model.Ship;
import asteroids.model.program.expression.FunctionContainer;
import asteroids.model.program.statement.Statement;
import asteroids.util.OGUtil;



//TODO: WERK

public class Program {

	private static final int MAX_STEPS_IN_ONE_CYCLE = 50; //100000;
	
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
		if (ship == null)
			throw new InvalidShipException();
		return ship;
	}

	public void setShip(Ship ship) throws InvalidShipException {
		if (totalTime > 0)
			throw new InvalidShipException("Ship cannot be changed after program has already started executing.");
		this.ship = ship;
	}
	
	public Program(Statement statement) throws ProgramException {
		main = new FunctionContainer(statement);
		OGUtil.println("program functioncontainer is: " + main);
	}
	
	
	private double totalTime = 0;
	private double totalTimeTruncated = 0;
	public void run(double dt) throws ProgramException, InvalidShipException {
		int i = 0;
		if (ship == null)
			throw new InvalidShipException("Cannot run program, no ship assigned.");
		totalTime += dt;
		double requiredTime = main.getRequiredTime();
		while (requiredTime + totalTimeTruncated <= totalTime && !completed) {
			completed = !main.step(this) || main.getIsReturned();
			totalTimeTruncated += requiredTime;
			if (!completed)
				requiredTime = main.getRequiredTime();
			if (i++ > MAX_STEPS_IN_ONE_CYCLE)
				throw new TooLongWithoutYieldingException();
		}
	}
	
	
	
	private final List<Object> prints = new ArrayList<Object>();

	public List<Object> getPrints() {
		return new ArrayList<Object>(prints);
	}
	
	public void print(Object object) {
		System.out.println(object);
		prints.add(object);
	}
}
