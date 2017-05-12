package asteroids.model.program.expression;

import java.util.List;

import asteroids.exceptions.*;
import asteroids.model.program.Program;
import asteroids.util.OGUtil;

public class CallFunction extends ContextAwareExpression {

	public final String functionName;
	public final List<Expression> actualArgs;
	
	public CallFunction(String functionName, List<Expression> actualArgs) throws ProgramException {
		super(actualArgs.toArray(new Expression[actualArgs.size()]));
		this.functionName = functionName;
		this.actualArgs = actualArgs;
	}
	
	private FunctionContainer function = null; //cache function while executing it
	private Object lastResult = null;
	private int iArgument;
	
	@Override
	public boolean step(Program program) throws ProgramException {
		if (function == null) {
			//save function to cache
			Object variable = this.variableContext.getVariable(functionName);
			if (!(variable instanceof FunctionContainer))
				throw new NotAFunctionException();
			function = (FunctionContainer)variable;
			iArgument = 0;
			function.clearArguments();
			OGUtil.println("start running function " + this.function + " (" + this.functionName + ") from " + this);
			return true;
		} else if (iArgument < actualArgs.size()) { //not done yet with computing the arguments!
			if (!(actualArgs.get(iArgument).step(program)))
				function.setArgument("$" + iArgument, actualArgs.get(iArgument++).evaluate(program));
			return true;
		}
		boolean result = function.step(program);
		if (!result) {
			lastResult = function.evaluate(program);
			OGUtil.println("stop running function " + this.function + " (" + this.functionName + ") from " + this);
			function = null;
		}
		return result;
	}
	

	@Override
	public Object evaluate(Program program) throws ProgramException {
		return lastResult;
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
