package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.FunctionContextContainer;
import asteroids.model.program.statement.IFunctionContextAwareStatement;
import asteroids.model.program.statement.IStatement;
import asteroids.model.program.statement.LoopContextContainer;
import asteroids.model.program.statement.Statement;
import asteroids.model.program.statement.VariableContextContainer;

public class ReadParameter extends Statement implements IExpression<Object>, IFunctionContextAwareStatement {
	
	private final String argName;
	
	public ReadParameter(String argName) throws ProgramException {
		super();
		this.argName = argName;
	}
	
	public Object evaluate(Program program) throws ProgramException {
		return this.functionContext.getArgument(argName);
	}
	
	

	private VariableContextContainer<? extends IStatement> variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer<? extends IStatement> variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer<? extends IStatement> getVariableContext() { return this.variableContext; };
	
	private LoopContextContainer<? extends IStatement> loopContext = null;
	@Override public void saveLoopContext(LoopContextContainer<? extends IStatement> loopContext) { this.loopContext = loopContext; }
	@Override public LoopContextContainer<? extends IStatement> getLoopContext() { return this.loopContext; };
	
	private FunctionContextContainer<? extends IStatement> functionContext = null;
	@Override public void saveFunctionContext(FunctionContextContainer<? extends IStatement> functionContext) { this.functionContext = functionContext; }
	@Override public FunctionContextContainer<? extends IStatement> getFunctionContext() { return this.functionContext; };
}
