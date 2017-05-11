package asteroids.model.program.expression;

import java.util.List;

import asteroids.exceptions.*;
import asteroids.model.program.Program;

public class CallFunction extends FunctionContextAwareExpression {

	public final String functionName;
	public final List<Expression> actualArgs;
	
	public CallFunction(String functionName, List<Expression> actualArgs) {
		super();
		this.functionName = functionName;
		this.actualArgs = actualArgs;
	}
	
	private FunctionContainer function = null; //cache function while executing it
	private int iArgument;
	
	@Override
	public boolean step(Program program) throws ProgramException {
		if (function == null) {
			//save function to cache
			Object variable = this.variableContext.getVariable(functionName);
			if (!(variable instanceof FunctionContainer))
				throw new NotAFunctionError();
			function = (FunctionContainer)variable;
			iArgument = 0;
			function.clearArguments();;
			return true;
		} else if (iArgument < actualArgs.size()) { //not done yet with computing the arguments!
			if (!(actualArgs.get(iArgument).step(program)))
				function.setArgument("$" + iArgument, actualArgs.get(iArgument++).evaluate(program));
			return true;
		}
		boolean result = function.step(program);
		if (!result)
			function = null;
		return result;
	}
	

	@Override
	public Object evaluate(Program program) throws ProgramException {
		return this.function.evaluate(program);
	}
	
	@Override
	public double getRequiredTime() {
		if (function == null)
			return 0;
		if (iArgument < actualArgs.size())
			return actualArgs.get(iArgument).getRequiredTime();
		return this.function.getRequiredTime();
	}
}
