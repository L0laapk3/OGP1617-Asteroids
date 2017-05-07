package asteroids.program;

public abstract class Expression extends Statement {
	
	protected Expression() {
		super();
	}
	
	@Override
	protected boolean step(Program program) {
		return false;
	}
	
	public abstract Object evaluate(Program program);
}
