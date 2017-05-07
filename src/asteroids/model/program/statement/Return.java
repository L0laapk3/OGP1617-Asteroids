package asteroids.model.program.statement;

import asteroids.model.program.Program;
import asteroids.model.program.expression.Expression;

public class Return extends Statement {

	public final Expression expression;
	
	public Return(Expression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public boolean step(Program program) {
		program.doReturn(expression.evaluate(program));
		return false;
	}

}
