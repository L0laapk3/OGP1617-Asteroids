package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.Expression;
import asteroids.model.program.expression.ICondition;

public class WhileLoop extends ContextContainer {

	private Expression condition;
	private Statement statement;
	
	private boolean nextIsCondition = true;
	private boolean firstTime = true;
	
	public <T extends Expression & ICondition> WhileLoop(T condition, Statement statement) {
		super(statement);
		this.condition = condition;
		this.statement = statement;
	}

	
	@Override
	public boolean step(Program program) throws ProgramException {
		if (nextIsCondition) {
			nextIsCondition = false;
			return (boolean)this.condition.evaluate(program);
		}
		if(!statement.step(program)) {
			nextIsCondition = true;
			if (firstTime)
				firstTime = false;
			else
				statement.reset(program);
		}
		return super.step(program);
	}
	
	

	@Override
	protected void reset(Program program) {
		nextIsCondition = true;
		firstTime = true;
		statement.reset(program);
		super.reset(program);
	}
}
