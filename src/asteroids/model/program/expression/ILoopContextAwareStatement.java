package asteroids.model.program.expression;

import asteroids.model.program.statement.LoopContextContainer;

public interface ILoopContextAwareStatement extends IVariableContextAwareStatement {

	public abstract LoopContextContainer getLoopContext();
	public abstract void saveLoopContext(LoopContextContainer loopContext);
	
	public default void setLoopContext(LoopContextContainer context) {
		this.saveLoopContext(context);
		IVariableContextAwareStatement.	super.setLoopContext(context);
	}

	
	
	
	
	
	/*
	
	default getters and setters
	
	
	private VariableContextContainer variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer getVariableContext() { return this.variableContext; };
	
	private LoopContextContainer loopContext = null;
	@Override public void saveLoopContext(LoopContextContainer loopContext) { this.loopContext = loopContext; }
	@Override public LoopContextContainer getLoopContext() { return this.loopContext; };
	
	 */
}
