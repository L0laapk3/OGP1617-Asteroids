package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public interface INumeric {

	public abstract Double evaluate(Program program) throws ProgramException;

}