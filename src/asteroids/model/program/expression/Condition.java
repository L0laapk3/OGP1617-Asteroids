package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public abstract class Condition extends Expression implements ICondition {
	
	@Override
	public abstract Boolean evaluate(Program program) throws ProgramException;

}
