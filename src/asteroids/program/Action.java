package asteroids.program;

public abstract class Action extends Statement {
	
	protected Action() {
		super();
	}

	@Override
	public double getRequiredTime() {
		return 0.2;
	}
}
