package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.Expression;
import asteroids.model.program.expression.ICondition;

public class WhileLoop extends LoopContextContainer {

	private Expression condition;
	private Statement statement;
	
	private boolean nextIsCondition = true;
	private boolean firstTime = true;
	
	public <T extends Expression & ICondition> WhileLoop(T condition, Statement statement) {
		super((Expression)statement, condition);
		this.condition = condition;
		this.statement = statement;
	}

	
	public boolean step(Program program) throws ProgramException {
		if (nextIsCondition) {
			if (condition.step(program))
				return true;
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
		super.reset(program);
	}
	
	
	@Override
	public double getRequiredTime() {
		if (nextIsCondition)
			return condition.getRequiredTime();
		return statement.getRequiredTime();
	}
	

	@Override
	public Object evaluate(Program program) throws ProgramException {
		throw new ProgramException("Internal program exception: evaluate was called on a statement.");
	}
}
