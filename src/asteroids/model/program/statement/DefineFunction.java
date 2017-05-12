package asteroids.model.program.statement;

import asteroids.exceptions.BadClassAssignmentException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.ContextAwareExpression;
import asteroids.model.program.expression.FunctionContainer;
import asteroids.util.OGUtil;

public class DefineFunction extends ContextAwareExpression {
	
	public final String functionName;
	public final FunctionContainer function;
	
	public DefineFunction(String functionName, Statement statement) throws ProgramException {
		super(new FunctionContainer(statement));
		this.function = (FunctionContainer)this.childStatements[0]; //get the above functioncontainer again.. workaround because super needs to be first
		this.functionName = functionName;
		OGUtil.println("definefunction functioncontainer is: " + this.function);
	}
	
	@Override
	public boolean step(Program program) throws BadClassAssignmentException {
		this.variableContext.setVariable(functionName, function);
		return false;
	}

	@Override
	public Object evaluate(Program program) throws ProgramException {
		throw new ProgramException("Internal program exception: evaluate was called on a statement.");
	}
}
