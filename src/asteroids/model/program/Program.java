package asteroids.model.program;

import java.util.ArrayList;
import java.util.List;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.ProgramException;
import asteroids.exceptions.TooLongWithoutYieldingException;
import asteroids.model.Ship;
import asteroids.model.program.statement.FunctionContainer;
import asteroids.model.program.statement.IStatement;



//TODO: WERK

public class Program {

	private static final int MAX_STEPS_IN_ONE_CALL = 1000; //1000-10000;
	
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
	
	public Program(IStatement statement) throws ProgramException {
		main = new FunctionContainer(statement);
		System.out.println("\n\n\n------------------------------------\n");
		System.out.println(this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode()));
		recursivePrint(main, 0, new ArrayList<Integer>());
		System.out.println("\n------------------------------------\n\n\n");
	}
	private void recursivePrint(IStatement statement, int level, List<Integer> childrenLeft) {
		String string = level == 0 ? "  \\_" : "    ";
		for (int i = 0; i < level ; i++)
			if (i + 1 == level)
				string += childrenLeft.get(i) > 0 ? "  |_" : "  \\_";
			else
				string += childrenLeft.get(i) > 0 ? "  | " : "    ";
		if (childrenLeft.size() <= level)
			childrenLeft.add(null);
		childrenLeft.set(level, statement.getChildStatements().length);
		System.out.println(string + statement.getClass().getSimpleName() + "@" + Integer.toHexString(statement.hashCode()));
		for (IStatement child : statement.getChildStatements()) {
			childrenLeft.set(level, childrenLeft.get(level) - 1);
			recursivePrint(child, level + 1, childrenLeft);
		}
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
