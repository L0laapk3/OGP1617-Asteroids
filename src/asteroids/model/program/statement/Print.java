package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.IExpression;

public class Print extends ExpressionContainer<Object> {
	
	public Print(IExpression<? extends Object> value) throws ProgramException {
		super(value);
	}
	
	@Override
	public boolean selfStep(Program program) throws ProgramException {
		program.print(getResult(0));
		return false;
	}

}
