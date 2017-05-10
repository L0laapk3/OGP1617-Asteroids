package asteroids.model.program.statement;

public abstract class ContextAwareStatement extends Statement {

	protected ContextContainer context = null;
	public ContextContainer getContext() {
		return this.context;
	}
	
	@Override
	protected void setContext(ContextContainer context) {
		this.context = context;
	}
	
	protected ContextAwareStatement() {
		super();
	}

}
