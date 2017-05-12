package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.VariableContextContainer;
import asteroids.model.program.expression.IExpression;
import asteroids.util.OGUtil;

public class IfElse extends VariableContextContainer {
	
	private IExpression<? extends Boolean> condition;
	private IStatement onTrue;
	private IStatement onFalse;
	private boolean conditionDone = false;
	private boolean conditionResult;

	public IfElse(IExpression<? extends Boolean> condition2, IStatement ifBody, IStatement elseBody) throws ProgramException {
		super(ifBody, elseBody, condition2);
		OGUtil.println("if statement " + condition2 + " " + ifBody + " " + elseBody);
		this.onTrue = ifBody;
		this.onFalse = elseBody;
		this.condition = condition2;
	}

	@Override
	public boolean step(Program program) throws ProgramException {
		if (!conditionDone) {
			if (condition.step(program))
				return true;
			conditionDone = true;
			conditionResult = (boolean)condition.evaluate(program);
			return true;
		} else if (conditionResult)
			return onTrue.step(program);
		else
			return onFalse.step(program);
	}
	
	@Override
	public void reset(Program program) {
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
