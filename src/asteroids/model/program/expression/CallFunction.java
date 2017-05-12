package asteroids.model.program.expression;

import asteroids.exceptions.NotAFunctionException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.util.OGUtil;

public class CallFunction extends MultiPartExpression<Object> implements IExpression<Object>, IVariableContextAwareStatement {

	public final String functionName;
	
	@SafeVarargs
	public CallFunction(String functionName, IExpression<? extends Object>... actualArgs) throws ProgramException {
		super(actualArgs);
		this.functionName = functionName;
	}
	
	private FunctionContainer function = null; //cache function while executing it
	private Object lastResult = null;
	private int iArgument;
	
	@Override
	public boolean selfStep(Program program) throws ProgramException {
		if (function == null) {
			//save function to cache
			Object variable = this.getVariableContext().getVariable(functionName);
			if (!(variable instanceof FunctionContainer))
				throw new NotAFunctionException();
			function = (FunctionContainer)variable;
			iArgument = 0;
			function.clearArguments();
			OGUtil.println("start running function " + this.function + " (" + this.functionName + ") from " + this);
			return true;
		} else if (iArgument < statements.length) { //not done yet with computing the arguments!
			if (!(statements[iArgument].step(program)))
				function.setArgument("$" + (iArgument + 1), statements[iArgument++].evaluate(program));
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
	public double selfGetRequiredTime() {
		return this.function.getRequiredTime();
	}
	
	@Override
	public Object evaluate(Program program) throws ProgramException {
		return lastResult;
	}
	
	
	

	
	
	private VariableContextContainer variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer getVariableContext() { return this.variableContext; };
	
}
