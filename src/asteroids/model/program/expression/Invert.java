package asteroids.model.program.expression;

import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.ContainerStatement;

public class Invert extends ContainerStatement<IExpression<? extends Boolean>> implements IExpression<Boolean> {
	
	public Invert(IExpression<? extends Boolean> expression) throws ProgramException {
		super(expression);
	}

	@Override
	public Boolean evaluate(Program program) throws ProgramException {
		Boolean result = statements[0].evaluate(program);
		if (result == null)
			throw new NullComputationException();
		return !result;
	}
}
