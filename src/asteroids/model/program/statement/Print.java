package asteroids.model.program.statement;

import asteroids.model.program.Program;
import asteroids.model.program.expression.Expression;

public class Print extends Statement {

	public final Expression expression;
	
	public Print(Expression value) {
		this.expression = value;
	}
	
	@Override
	public boolean step(Program program) {
		System.out.println(expression.evaluate(program));
		return false;
	}

}
