package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.Entity;
import asteroids.model.program.Program;
import asteroids.model.program.statement.Statement;

public abstract class EntityExpression extends Expression implements IEntity {
	
	protected EntityExpression(Statement... statements) throws ProgramException {
		super(statements);
	}
	
	@Override
	public abstract Entity evaluate(Program program) throws ProgramException;

}




