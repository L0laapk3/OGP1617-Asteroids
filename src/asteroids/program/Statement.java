package asteroids.program;

import asteroids.exceptions.ProgramErrorException;

public abstract class Statement {
	
	
	protected Statement() {
	}
	
	//returns false only if this is the last step to be executed.
	protected abstract boolean step(Program program) throws ProgramErrorException;
	
	protected void reset(Program program) { }
	
	public double getRequiredTime() {
		return 0;
	}
}
