package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.Entity;
import asteroids.model.program.Program;

public interface IEntity {

	public abstract Entity evaluate(Program program) throws ProgramException;

}