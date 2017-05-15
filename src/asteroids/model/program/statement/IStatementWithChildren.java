package asteroids.model.program.statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import asteroids.model.program.Program;

public interface IStatementWithChildren<T extends IStatement> extends IStatement {
	
	@Override
	public abstract T[] getChildren();
	public default List<IStatement> getAllChildren() { 
		List<IStatement> l = new ArrayList<IStatement>(Arrays.asList(getChildren()));
		for (IStatement child : getChildren())
			l.addAll(child.getAllChildren());
		return l;
	}
	
	
	
	
	public default void setContext(VariableContextContainer<? extends IStatement> context) {
		IStatement.super.setContext(context);
		for (T statement : getChildren())
			statement.setContext(context);
	}
	public default void setLoopContext(LoopContextContainer<? extends IStatement> context) {
		IStatement.super.setLoopContext(context);
		for (T statement : getChildren())
			statement.setLoopContext(context);
	}
	public default void setFunctionContext(FunctionContextContainer<? extends IStatement> context) {
		IStatement.super.setFunctionContext(context);
		for (T statement : getChildren()) 
			statement.setFunctionContext(context);
	}
	public default void reset(Program program) {
		IStatement.super.reset(program);
		for (T statement : getChildren())
			statement.reset(program);
	}
}
