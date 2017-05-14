package asteroids.model.program.expression;

import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.ContainerStatement;

public class Sqrt extends ContainerStatement<IExpression<? extends Double>> implements IExpression<Double> {
	
	public Sqrt(IExpression<? extends Double> expression) throws ProgramException {
		super(expression);
	}
	
	
	public Double evaluate(Program program) throws ProgramException {
		Double result = statements[0].evaluate(program);
		if (result == null)
			throw new NullComputationException();
		return Math.sqrt(result);
	}
}
