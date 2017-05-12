package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.SingleContainerStatement;

public class Negative extends SingleContainerStatement<IExpression<? extends Double>> implements IExpression<Double> {
	
	public Negative(IExpression<? extends Double> expression) throws ProgramException {
		super(expression);
	}

	@Override
	public Double evaluate(Program program) throws ProgramException {
		return -statement.evaluate(program);
	}
}
