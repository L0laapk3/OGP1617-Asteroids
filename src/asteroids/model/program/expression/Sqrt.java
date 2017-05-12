package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.SingleContainerStatement;

public class Sqrt extends SingleContainerStatement<IExpression<? extends Double>> implements IExpression<Double> {
	
	public Sqrt(IExpression<? extends Double> expression) throws ProgramException {
		super(expression);
	}
	
	
	public Double evaluate(Program program) throws ProgramException {
		return statement.evaluate(program);
	}
}
