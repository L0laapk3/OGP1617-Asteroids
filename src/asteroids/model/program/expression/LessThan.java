package asteroids.model.program.expression;

import asteroids.exceptions.NotComparableException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class LessThan extends Condition {
	
	private final Expression expression1;
	private final Expression expression2;
	
	public <T,U extends Expression & INumeric> LessThan(T expression1, U expression2) {
		super();
		this.expression1 = (Expression) expression1;
		this.expression2 = (Expression) expression2;		
	}
	
	
	@Override
	public Boolean evaluate(Program program) throws ProgramException {
		Object result1 = expression1.evaluate(program);
		Object result2 = expression2.evaluate(program);
		
		if ((result1 instanceof Double) && (result2 instanceof Double)) {
			return (double)result1 > (double)result2;
		} else {
			throw new NotComparableException("At least one of the variables is not a double");
		}
	
	}
}
