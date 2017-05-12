package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.VariableContextContainer;
import asteroids.model.program.expression.FunctionContextContainer;

public interface IStatement {
	
	//returns false only if this is the last step to be executed.
	public abstract boolean step(Program program) throws ProgramException;
	
	public abstract IStatement[] getChildStatements();
	
	
	
	public default void setContext(VariableContextContainer context) {
		for (IStatement statement : getChildStatements())
			statement.setContext(context);
	}
	public default void setLoopContext(LoopContextContainer context) {
		for (IStatement statement : getChildStatements())
			statement.setLoopContext(context);
	}
	public default void setFunctionContext(FunctionContextContainer context) {
		for (IStatement statement : getChildStatements()) 
			statement.setFunctionContext(context);
	}
	public default void reset(Program program) {
		for (IStatement statement : getChildStatements())
			statement.reset(program);
	}
	
	public default double getRequiredTime() { return 0;	}
	public default double selfGetRequiredTime() { return 0; }
}
