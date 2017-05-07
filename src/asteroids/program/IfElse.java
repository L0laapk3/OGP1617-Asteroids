package asteroids.program;

import asteroids.exceptions.ProgramErrorException;

public class IfElse extends Statement {
	
	private Condition condition;
	private BlockStatement onTrue;
	private BlockStatement onFalse;
	private boolean conditionDone = false;
	private boolean conditionResult;

	public <T extends Expression & ICondition> IfElse(T condition, BlockStatement onTrue, BlockStatement onFalse) {
		super();
		this.onTrue = onTrue;
		this.onFalse = onFalse;
		this.condition = (Condition)condition;
	}

	@Override
	protected boolean step(Program program) throws ProgramErrorException {
		if (!conditionDone) {
			conditionDone = true;
			conditionResult = condition.evaluate(program);
			return true;
		} else if (conditionResult)
			return onTrue.step(program);
		else
			return onFalse.step(program);
	}
	
	@Override
	protected void reset(Program program) {
		if (conditionDone) {
			if (conditionResult)
				onTrue.reset(program);
			else
				onFalse.reset(program);
		}
		conditionDone = false;
	}
	
	@Override
	public double getRequiredTime() {
		if (!conditionDone)
			return condition.getRequiredTime();
		else if (conditionResult)
			return onTrue.getRequiredTime();
		else
			return onFalse.getRequiredTime();
	}
	
}
