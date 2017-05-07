package asteroids.program;

public abstract class ContextAwareStatement extends Statement {

	protected ContextContainer context = null;
	public ContextContainer getContext() {
		return this.context;
	}
	
	protected ContextAwareStatement() {
		super();
	}

}
