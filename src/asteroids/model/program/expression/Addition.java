package asteroids.model.program.expression;

import asteroids.exceptions.NotComparableException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class Addition extends Numeric {
	
	private final Expression expression1;
	private final Expression expression2;
	
	public <T extends Expression & INumeric, U extends Expression & INumeric> Addition(T expression1, U expression2) throws ProgramException {
		super(expression1, expression2);
		this.expression1 = (Expression)expression1;
		this.expression2 = (Expression)expression2;		
	}
	
	
	@Override
	public Double evaluate(Program program) throws ProgramException {
		Object result1 = expression1.evaluate(program);
		Object result2 = expression2.evaluate(program);
		
		if ((result1 instanceof Double) && (result2 instanceof Double)) {
			return (double)result1 + (double)result2;
		} else {
			throw new NotComparableException("At least one of the variables is not a double");
		}
	}

	private boolean step1done = false;
	private boolean step2done = false;
	
	@Override
	public boolean step(Program program) throws ProgramException {
		if (!step1done && !expression1.step(program))
			step1done = true;
		else if (!step2done && !expression2.step(program)) {
			step2done = true;
			return false;
		}
		return true;
	}
	
	@Override
	public double getRequiredTime() {
		if (!step1done)
			return expression1.getRequiredTime();
		return expression2.getRequiredTime();
	}
	
	@Override
	public void reset(Program program) {
		super.reset(program);
		step1done = false;
		step2done = false;
	}
}
