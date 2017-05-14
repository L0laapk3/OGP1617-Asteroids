package asteroids.model.program.statement;

public interface ILoopContextAwareStatement extends IVariableContextAwareStatement {

	public abstract LoopContextContainer<? extends IStatement> getLoopContext();
	public abstract void saveLoopContext(LoopContextContainer<? extends IStatement> loopContext);

	
	
	
	
	
	/*
	
	default getters and setters
	
	
	private VariableContextContainer<? extends IStatement> variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer<? extends IStatement> variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer<? extends IStatement> getVariableContext() { return this.variableContext; };
	
	private LoopContextContainer<? extends IStatement> loopContext = null;
	@Override public void saveLoopContext(LoopContextContainer<? extends IStatement> loopContext) { this.loopContext = loopContext; }
	@Override public LoopContextContainer<? extends IStatement> getLoopContext() { return this.loopContext; };
	
	 */
}
