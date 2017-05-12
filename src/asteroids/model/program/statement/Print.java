package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.Expression;

public class Print extends Statement {

	public final Expression expression;
	
	public Print(Expression expression) throws ProgramException {
		super(expression);
		this.expression = expression;
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		if (expression.step(program))
			return true;
		program.print(expression.evaluate(program));
		return false;
	}
	
	
	@Override
	public double getRequiredTime() {
		return expression.getRequiredTime();
	}

}
