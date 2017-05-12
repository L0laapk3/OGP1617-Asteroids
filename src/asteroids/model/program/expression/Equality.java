package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Equality extends MultiPartExpression<Object> implements IExpression<Boolean> {
	
	
	public <T extends Object> Equality(IExpression<? extends T> expression1, IExpression<? extends T> expression2) throws ProgramException {
		super(expression1, expression2);
	}
	
	@Override
	public Boolean evaluate(Program program) throws ProgramException {
		return this.results[0].equals(this.results[1]);
	}
}
