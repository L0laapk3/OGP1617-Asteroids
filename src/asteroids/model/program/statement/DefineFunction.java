package asteroids.model.program.statement;

import asteroids.exceptions.BadClassAssignmentException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.VariableContextContainer;
import asteroids.model.program.expression.FunctionContainer;
import asteroids.model.program.expression.IVariableContextAwareStatement;
import asteroids.util.OGUtil;

public class DefineFunction extends Statement implements IVariableContextAwareStatement {
	
	public final String functionName;
	public final FunctionContainer function;
	
	public DefineFunction(String functionName, IStatement body) throws ProgramException {
		super(new FunctionContainer(body));
		this.function = (FunctionContainer)this.childStatements[0]; //get the above functioncontainer again.. workaround because super needs to be first
		this.functionName = functionName;
		OGUtil.println("definefunction functioncontainer is: " + this.function);
	}
	
	@Override
	public boolean step(Program program) throws BadClassAssignmentException {
		this.variableContext.setVariable(functionName, function);
		return false;
	}
	
	
	
	private VariableContextContainer variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer getVariableContext() { return this.variableContext; };
}
