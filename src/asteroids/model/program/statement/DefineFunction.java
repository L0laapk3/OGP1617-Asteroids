package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.ContextAwareExpression;
import asteroids.model.program.expression.FunctionContainer;

public class DefineFunction extends ContextAwareExpression {
	
	public final String functionName;
	public final FunctionContainer function;
	
	public DefineFunction(String functionName, Statement statement) {
		this.functionName = functionName;
		this.function = new FunctionContainer(statement);
	}
	
	@Override
	public boolean step(Program program) {
		this.variableContext.setVariable(functionName, function);
		return false;
	}

	@Override
	public Object evaluate(Program program) throws ProgramException {
		throw new ProgramException("Internal program exception: evaluate was called on a statement.");
	}
}
