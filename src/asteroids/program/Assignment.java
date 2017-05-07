package asteroids.program;

public class Assignment extends Statement {

	public final String varname;
	public final Expression expression;
	
	public Assignment(String varname, Expression expression) {
		super();
		this.varname = varname;
		this.expression = expression;
	}

	@Override
	protected boolean step(Program program) {
		program.variables.add(new Variable(varname, expression.evaluate(program)));
		return false;
	}

}
