package asteroids.model.program.expression;

import asteroids.exceptions.NotAFunctionException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.FunctionContainer;
import asteroids.model.program.statement.IStatement;
import asteroids.model.program.statement.IVariableContextAwareStatement;
import asteroids.model.program.statement.VariableContextContainer;


//TODO: callfunction moet eigenlijk heel die functie shit clonen denk ik


public class CallFunction extends MultiContainerExpression<Object> implements IExpression<Object>, IVariableContextAwareStatement {

	public final String functionName;
	
	@SafeVarargs
	public CallFunction(String functionName, IExpression<? extends Object>... actualArgs) throws ProgramException {
		super(actualArgs);
		this.functionName = functionName;
	}
	
	private FunctionContainer function = null; //cache function while executing it
	private Object lastResult = null;
	private int iArgument;
	
	
	private void loadFunction() throws ProgramException {
		Object variable = this.getVariableContext().getVariable(functionName);
		if (!(variable instanceof FunctionContainer))
			throw new NotAFunctionException();
		function = new FunctionContainer((FunctionContainer)variable, this.getVariableContext());
		iArgument = 0;
	}
	
	@Override
	public boolean selfStep(Program program) throws ProgramException {
		if (function == null) {
			loadFunction();
			return true;
		} else if (iArgument < statements.length) { //not done yet with computing the arguments!
			function.setArgument("$" + (iArgument + 1), statements[iArgument++].evaluate(program));
			return true;
		}
		boolean result = function.step(program);
		if (!result) {
			lastResult = function.evaluate(program);
			function = null;
		}
		return result;
	}
	
	@Override
	public double selfGetRequiredTime() throws ProgramException {
		if (this.function == null)
			loadFunction();
		return this.function.getRequiredTime();
	}
	
	@Override
	public Object evaluate(Program program) throws ProgramException {
		return lastResult;
	}
	
	
	

	

	private VariableContextContainer<? extends IStatement> variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer<? extends IStatement> variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer<? extends IStatement> getVariableContext() { return this.variableContext; };
	
}
