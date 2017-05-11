package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.Condition;
import asteroids.model.program.expression.ContextContainer;
import asteroids.model.program.expression.Expression;
import asteroids.model.program.expression.ICondition;

public class IfElse extends ContextContainer {
	
	private Condition condition;
	private Statement onTrue;
	private Statement onFalse;
	private boolean conditionDone = false;
	private boolean conditionResult;

	public <T extends Expression & ICondition> IfElse(Condition condition, Statement ifBody, Statement elseBody) {
		super(ifBody, elseBody, condition);
		this.onTrue = ifBody;
		this.onFalse = elseBody;
		this.condition = condition;
	}

	@Override
	public boolean step(Program program) throws ProgramException {
		if (!conditionDone) {
			if (condition.step(program))
				return true;
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
		super.reset(program);
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
