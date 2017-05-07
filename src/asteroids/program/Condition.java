package asteroids.program;

public abstract class Condition extends Expression implements ICondition {

	protected Condition() {
		super();
	}

	@Override
	public abstract Boolean evaluate(Program program);

}
