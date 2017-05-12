package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.Statement;

public class ReadParameter extends Statement implements IExpression<Object>, IFunctionContextAwareStatement {
	
	private final String argName;
	
	public ReadParameter(String argName) throws ProgramException {
		super();
		this.argName = argName;
	}
	
	public Object evaluate(Program program) throws ProgramException {
		return this.functionContext.getArgument(argName);
	}
	
	

	private VariableContextContainer variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer getVariableContext() { return this.variableContext; };
	
	private FunctionContextContainer functionContext = null;
	@Override public void saveFunctionContext(FunctionContextContainer functionContext) { this.functionContext = functionContext; }
	@Override public FunctionContextContainer getFunctionContext() { return this.functionContext; };
}
