package asteroids.program;

public class Return extends Statement {

	public final Expression expression;
	
	protected Return(Expression expression) {
		super();
		this.expression = expression;
	}

	@Override
	protected boolean step(Program program) {
		program.doReturn(expression.evaluate(program));
		return false;
	}

}
