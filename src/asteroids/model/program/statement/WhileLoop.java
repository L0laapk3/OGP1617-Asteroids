package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.IExpression;

public class WhileLoop extends LoopContextContainer {

	private IExpression<? extends Boolean> condition;
	private IStatement statement;
	
	private boolean nextIsCondition = true;
	private boolean firstTime = true;
	
	public WhileLoop(IExpression<? extends Boolean> condition, IStatement body) throws ProgramException {
		super(body, condition);
		this.condition = condition;
		this.statement = body;
	} 

	
	public boolean step(Program program) throws ProgramException {
		boolean step = condition.step(program);
		System.out.println(nextIsCondition + " " + step);
		if (nextIsCondition) {
			if (step)
				return true;
			nextIsCondition = false;
			System.out.println(this.condition.evaluate(program));
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
	public void reset(Program program) {
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
}
