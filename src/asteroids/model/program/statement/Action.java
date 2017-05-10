package asteroids.model.program.statement;

public abstract class Action extends Statement {
	
	protected Action(Statement... statements) {
		super(statements);
	}

	@Override
	public double getRequiredTime() {
		return 0.2;
	}
}
