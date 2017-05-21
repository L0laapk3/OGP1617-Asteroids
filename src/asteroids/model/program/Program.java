package asteroids.model.program;

import java.util.ArrayList;
import java.util.List;

import asteroids.exceptions.ForSomeReasonNotAllowedError;
import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.ProgramException;
import asteroids.exceptions.TooLongWithoutYieldingException;
import asteroids.model.Ship;
import asteroids.model.program.statement.FunctionContainer;
import asteroids.model.program.statement.IStatement;
import asteroids.util.OGUtil;



//TODO: WERK

public class Program {

	private static final int MAX_STEPS_IN_ONE_CALL = 10000; //1000-10000;
	
	private boolean completed = false;
	
	public boolean isCompleted() {
		return completed;
	}
	
	public Object getReturnValue() {
		return main.getReturnValue();
	}
	
	public final FunctionContainer main;

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
	
	public Program(IStatement statement) throws ProgramException {
		main = new FunctionContainer(statement);
		main.recursivePrint();
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
			OGUtil.println("\n--------------- STEP -----------------\n");
			try {
				completed = !main.step(this) || main.getIsReturned();
			} catch (ClassCastException e) {
				throw new ProgramException(e);
			}
			totalTimeTruncated += requiredTime;
			if (!completed)
				requiredTime = main.getRequiredTime();
			if (i++ > MAX_STEPS_IN_ONE_CALL)
				throw new TooLongWithoutYieldingException();
		}
		if (main.getIsReturned())
			throw new ForSomeReasonNotAllowedError("returning in the main function is for some reason not allowed.");
	}
	
	
	
	private final List<Object> prints = new ArrayList<Object>();

	public List<Object> getPrints() {
		return new ArrayList<Object>(prints);
	}
	
	public void print(Object object) {
		prints.add(object);
	}
}
