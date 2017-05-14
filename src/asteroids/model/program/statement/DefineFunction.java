package asteroids.model.program.statement;

import asteroids.exceptions.BadClassAssignmentException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class DefineFunction extends StatementWithChildren<FunctionContainer> implements IVariableContextAwareStatement {
	
	public final String functionName;
	
	public DefineFunction(String functionName, IStatement body) throws ProgramException {
		super(new FunctionContainer(body));
		this.functionName = functionName;
	}
	
	public FunctionContainer getFunction() {
		return statements[0];
	}
	
	@Override
	public boolean step(Program program) throws BadClassAssignmentException {
		this.variableContext.setVariable(functionName, getFunction());
		return false;
	}
	
	
	
	private VariableContextContainer<? extends IStatement> variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer<? extends IStatement> variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer<? extends IStatement> getVariableContext() { return this.variableContext; }
}
