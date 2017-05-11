package asteroids.model.program.expression;

import asteroids.model.program.statement.Statement;

public abstract class ContextAwareExpression extends Expression {

	protected ContextAwareExpression(Statement... statements) {
		super(statements);
	}
	
	protected ContextContainer variableContext = null;
	public ContextContainer getContext() {
		return this.variableContext;
	}
	
	@Override
	public void setContext(ContextContainer context) {
		this.variableContext = context;
	}

}
