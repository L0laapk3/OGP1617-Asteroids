package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.util.OGUtil;

public class LessThan extends MultiPartExpression<Double> implements IExpression<Boolean> {
	
	
	public LessThan(IExpression<? extends Double> expression1, IExpression<? extends Double> expression2) throws ProgramException {
		super(expression1, expression2);
	}
	
	@Override
	public Boolean evaluate(Program program) throws ProgramException {
		OGUtil.println(this.results[0] + " " + this.results[1]);
		return this.results[0] < this.results[1];
	}
}
