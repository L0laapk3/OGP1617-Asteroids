package asteroids.model.program.expression;

import asteroids.model.program.statement.LoopContextContainer;

public interface IFunctionContextAwareStatement extends ILoopContextAwareStatement {
	
	public abstract FunctionContextContainer getFunctionContext();
	public abstract void saveFunctionContext(FunctionContextContainer functionContext);
	
	public default void setFunctionContext(FunctionContextContainer context) {
		this.saveFunctionContext(context);
		ILoopContextAwareStatement.super.setFunctionContext(context);
	}
	
	
	//functioncontext should reset loopcontext: for example break calls shouldnt work inside functions.
	public default LoopContextContainer getLoopContext() { return null; }
	public default void saveLoopContext(LoopContextContainer loopContext) { }
	
	
	
	
	
	/*
	
	default getters and setters
	
	
	private VariableContextContainer variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer getVariableContext() { return this.variableContext; };
	
	private FunctionContextContainer functionContext = null;
	@Override public void saveFunctionContext(FunctionContextContainer functionContext) { this.functionContext = functionContext; }
	@Override public FunctionContextContainer getFunctionContext() { return this.functionContext; };
	
	 */
}
