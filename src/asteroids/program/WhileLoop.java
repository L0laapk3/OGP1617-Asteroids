package asteroids.program;

import asteroids.exceptions.ProgramErrorException;

public class WhileLoop extends ContextContainer {

	private Expression condition;
	
	protected <T extends Expression & ICondition> WhileLoop(T condition, BlockStatement statements) {
		super(statements);
		this.condition = condition;
		this.i = -1;
	}

	
	@Override
	protected boolean step(Program program) throws ProgramErrorException {
		if (this.i == -1) {
			this.i++;
			return (boolean)this.condition.evaluate(program);
		}
		return super.step(program);
	}
	
	
	@Override
	protected void toNextStatement() {
		super.toNextStatement();
		//loop around
		if (this.i >= statements.length)
			this.i = -1;
	}

	@Override
	public Statement getNextStatement() {
		if (this.i == -1)
			return this.condition;
		return super.getNextStatement();
	}
	
	

	@Override
	protected void reset(Program program) {
		i = -1;
		super.reset(program);
	}
}
