package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public interface IStatement extends Cloneable {
	
	public default IStatement[] getChildStatements() { return new IStatement[0]; }
	
	
	
	//returns false only if this is the last step to be executed.
	public abstract boolean step(Program program) throws ProgramException;	
	
	public default void setContext(VariableContextContainer<? extends IStatement> context) { 
		if (this instanceof IVariableContextAwareStatement)
			((IVariableContextAwareStatement)this).saveVariableContext(context);
	}
	public default void setLoopContext(LoopContextContainer<? extends IStatement> context) {
		if (this instanceof ILoopContextAwareStatement)
			((ILoopContextAwareStatement)this).saveLoopContext(context);
		}
	public default void setFunctionContext(FunctionContextContainer<? extends IStatement> context) {
		if (this instanceof ILoopContextAwareStatement)
			((IFunctionContextAwareStatement)this).saveFunctionContext(context);
		}
	public default void reset(Program program) { }
	
	public default double getRequiredTime() throws ProgramException { return 0;	}
	public default double selfGetRequiredTime() throws ProgramException { return 0; }
	
	public abstract IStatement clone();
}
