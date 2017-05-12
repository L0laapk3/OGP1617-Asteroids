package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.statement.Statement;

public abstract class ContextAwareExpression extends Expression {

	protected ContextAwareExpression(Statement... statements) throws ProgramException {
		super(statements);
	}
	
	protected ContextContainer variableContext = null;
	public ContextContainer getContext() {
		return this.variableContext;
	}
	
	@Override
	public void setContext(ContextContainer context) {
		this.variableContext = context;
		super.setContext(context);
	}

}
