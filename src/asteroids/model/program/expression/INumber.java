package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public interface INumber {

	public abstract Double evaluate(Program program) throws ProgramException;

}