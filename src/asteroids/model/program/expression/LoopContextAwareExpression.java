package asteroids.model.program.expression;

import asteroids.model.program.statement.LoopContextContainer;
import asteroids.model.program.statement.Statement;

public abstract class LoopContextAwareExpression extends ContextAwareExpression {

	protected LoopContextAwareExpression(Statement... statements) {
		super(statements);
	}
	
	protected LoopContextContainer loopContext = null;
	public LoopContextContainer getLoopContext() {
		return this.loopContext;
	}
	
	public void setLoopContext(LoopContextContainer context) {
		this.loopContext = context;
	}
	
	protected LoopContextAwareExpression() {
		super();
	}

}
