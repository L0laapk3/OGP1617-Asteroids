package asteroids.model.program.statement;

public interface IFunctionContextAwareStatement extends ILoopContextAwareStatement {
	
	public abstract FunctionContextContainer<? extends IStatement> getFunctionContext();
	public abstract void saveFunctionContext(FunctionContextContainer<? extends IStatement> functionContext);
	
	
	/*
	//functioncontext should reset loopcontext: for example break calls shouldnt work inside functions.
	public default LoopContextContainer<? extends IStatement> getLoopContext() { return null; }
	public default void saveLoopContext(LoopContextContainer<? extends IStatement> loopContext) { }
	*/
	
	
	
	
	/*
	
	default getters and setters
	
	
	private VariableContextContainer<? extends IStatement> variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer<? extends IStatement> variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer<? extends IStatement> getVariableContext() { return this.variableContext; };
	
	private LoopContextContainer<? extends IStatement> loopContext = null;
	@Override public void saveLoopContext(LoopContextContainer<? extends IStatement> loopContext) { this.loopContext = loopContext; }
	@Override public LoopContextContainer<? extends IStatement> getLoopContext() { return this.loopContext; };
	
	private FunctionContextContainer<? extends IStatement> functionContext = null;
	@Override public void saveFunctionContext(FunctionContextContainer<? extends IStatement> functionContext) { this.functionContext = functionContext; }
	@Override public FunctionContextContainer<? extends IStatement> getFunctionContext() { return this.functionContext; };
	
	 */
}
