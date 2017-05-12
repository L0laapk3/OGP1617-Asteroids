package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Multiplication extends MultiPartExpression<Double> implements IExpression<Double> {
	
	
	public Multiplication(IExpression<? extends Double> expression1, IExpression<? extends Double> expression2) throws ProgramException {
		super(expression1, expression2);
	}
	
	@Override
	public Double evaluate(Program program) throws ProgramException {
		return this.results[0] * this.results[1];
	}
}
