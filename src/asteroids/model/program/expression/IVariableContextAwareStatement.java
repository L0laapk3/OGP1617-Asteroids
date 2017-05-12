package asteroids.model.program.expression;

import asteroids.model.program.statement.IStatement;

public interface IVariableContextAwareStatement extends IStatement {

	public abstract VariableContextContainer getVariableContext();
	public abstract void saveVariableContext(VariableContextContainer variableContext);
	
	public default void setContext(VariableContextContainer context) {
		this.saveVariableContext(context);
		IStatement.super.setContext(context);
	}

	
	
	
	
	
	/*
	
	default getters and setters
	
	
	private VariableContextContainer variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer getVariableContext() { return this.variableContext; };
	
	 */

}
