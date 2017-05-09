package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.Expression;

public class Print extends Statement {

	public final Expression expression;
	
	public Print(Expression value) {
		this.expression = value;
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		System.out.println(expression.evaluate(program));
		return false;
	}

}
