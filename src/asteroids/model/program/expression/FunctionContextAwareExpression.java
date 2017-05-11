package asteroids.model.program.expression;

import asteroids.model.program.statement.Statement;

public abstract class FunctionContextAwareExpression extends LoopContextAwareExpression {

	protected FunctionContextAwareExpression(Statement... statements) {
		super(statements);
	}
	
	protected FunctionContextContainer functionContext = null;
	public FunctionContextContainer getFunctionContext() {
		return this.functionContext;
	}
	
	public void setFunctionContext(FunctionContextContainer context) {
		this.functionContext = context;
	}
}
