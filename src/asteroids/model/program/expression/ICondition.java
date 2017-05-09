package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public interface ICondition {

	public abstract Boolean evaluate(Program program) throws ProgramException;

}