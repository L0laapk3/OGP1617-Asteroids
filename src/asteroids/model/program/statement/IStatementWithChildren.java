package asteroids.model.program.statement;

import asteroids.model.program.Program;

public interface IStatementWithChildren<T extends IStatement> extends IStatement {
	
	@Override
	public abstract T[] getChildStatements();
	
	
	
	
	public default void setContext(VariableContextContainer<? extends IStatement> context) {
		IStatement.super.setContext(context);
		for (T statement : getChildStatements())
			statement.setContext(context);
	}
	public default void setLoopContext(LoopContextContainer<? extends IStatement> context) {
		IStatement.super.setLoopContext(context);
		for (T statement : getChildStatements())
			statement.setLoopContext(context);
	}
	public default void setFunctionContext(FunctionContextContainer<? extends IStatement> context) {
		IStatement.super.setFunctionContext(context);
		for (T statement : getChildStatements()) 
			statement.setFunctionContext(context);
	}
	public default void reset(Program program) {
		IStatement.super.reset(program);
		for (T statement : getChildStatements())
			statement.reset(program);
	}
}
