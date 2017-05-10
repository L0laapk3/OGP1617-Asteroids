package asteroids.model.program.statement;

public abstract class LoopContextAwareStatement extends ContextAwareStatement {

	protected ContextContainer loopContext = null;
	public ContextContainer getLoopContext() {
		return this.loopContext;
	}
	
	protected void setLoopContext(ContextContainer context) {
		this.loopContext = context;
	}
	
	protected LoopContextAwareStatement() {
		super();
	}

}
