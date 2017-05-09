package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.Entity;
import asteroids.model.program.Program;

public abstract class EntityExpression extends Expression implements IEntity {
	
	@Override
	public abstract Entity evaluate(Program program) throws ProgramException;

}




