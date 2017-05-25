package asteroids.model.program.statement;

import java.util.ArrayList;
import java.util.List;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.util.OGUtil;

public interface IStatement extends Cloneable {
	
	public default IStatement[] getChildren() { return new IStatement[0]; }
	public default List<IStatement> getAllChildren() { return new ArrayList<IStatement>(); }
	
	
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
		if (this instanceof IFunctionContextAwareStatement)
			((IFunctionContextAwareStatement)this).saveFunctionContext(context);
		}
	public default void reset(Program program) { }
	
	public default double getRequiredTime() throws ProgramException { return 0;	}
	public default double selfGetRequiredTime() throws ProgramException { return 0; }
	
	
	
	
	

	public default void recursivePrint() {
		if (OGUtil.SILENT)
			return;
		StackTraceElement head = new Exception().getStackTrace()[1];
		System.out.println(head);
		__recursivePrint(this, 0, new ArrayList<Integer>());
	}
	default void __recursivePrint(IStatement statement, int level, List<Integer> childrenLeft) {
		String string = level == 0 ? "  \\_" : "    ";
		for (int i = 0; i < level ; i++)
			if (i + 1 == level)
				string += childrenLeft.get(i) > 0 ? "  |_" : "  \\_";
			else
				string += childrenLeft.get(i) > 0 ? "  | " : "    ";
		if (childrenLeft.size() <= level)
			childrenLeft.add(null);
		childrenLeft.set(level, statement.getChildren().length);
		System.out.println(string + statement);
		for (IStatement child : statement.getChildren()) {
			childrenLeft.set(level, childrenLeft.get(level) - 1);
			__recursivePrint(child, level + 1, childrenLeft);
		}
	}


	public abstract IStatement clone();
}
