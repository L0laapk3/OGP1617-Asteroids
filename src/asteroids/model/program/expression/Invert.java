package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.SingleContainerStatement;

public class Invert extends SingleContainerStatement<IExpression<? extends Boolean>> implements IExpression<Boolean> {
	
	public Invert(IExpression<? extends Boolean> expression) throws ProgramException {
		super(expression);
	}

	@Override
	public Boolean evaluate(Program program) throws ProgramException {
		return !statement.evaluate(program);
	}
}
