package asteroids.model.program.statement;

public interface IVariableContextAwareStatement extends IStatement {

	public abstract VariableContextContainer<? extends IStatement> getVariableContext();
	public abstract void saveVariableContext(VariableContextContainer<? extends IStatement> variableContext);

	
	
	
	
	
	/*
	
	default getters and setters
	
	
	private VariableContextContainer<? extends IStatement> variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer<? extends IStatement> variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer<? extends IStatement> getVariableContext() { return this.variableContext; };
	
	 */

}
