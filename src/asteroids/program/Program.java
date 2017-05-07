package asteroids.program;

import java.util.HashSet;
import java.util.Set;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.ProgramErrorException;
import asteroids.model.Ship;



public class Program {
	
	public Set<Variable> variables = new HashSet<Variable>();

	public final Ship ship;
	
	private boolean completed = false;
	private boolean returned = false;
	private Object returnValue;
	public boolean isCompleted() {
		return this.completed;
	}
	
	private final BlockStatement main;
	
	public Program(Ship ship, Statement... statements) throws InvalidShipException {
		if (ship == null)
			throw new InvalidShipException();
		this.ship = ship;
		
		main = new BlockStatement(statements);
	}
	
	
	private double totalTime = 0;
	private double totalTimeTruncated = 0;
	public void run(double dt) throws ProgramErrorException {
		totalTime += dt;
		double requiredTime = main.getRequiredTime();
		while (requiredTime + totalTimeTruncated <= totalTime && !completed) {
			completed = main.step(this) || returned;
			totalTimeTruncated += requiredTime;
			requiredTime = main.getRequiredTime();
		}
	}
	
	public void doReturn(Object returnValue) {
		returned = true;
		this.returnValue = returnValue;
	}
}
