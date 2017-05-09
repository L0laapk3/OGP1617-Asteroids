package asteroids.model.program.statement;

import asteroids.exceptions.ProgramErrorException;
import asteroids.model.program.Program;

public abstract class Statement {
	
	//TODO: zien ofda alle subclasses wel super() callen bij create
	protected Statement() {
	}
	
	//returns false only if this is the last step to be executed.
	public abstract boolean step(Program program) throws ProgramErrorException;
	
	//warning: before modification of this function, has to be made sure that all subclasses have the super.reset()
	protected void reset(Program program) { }
	
	protected void makeContextAware(ContextContainer context) { }
	
	public double getRequiredTime() {
		return 0;
	}
}
