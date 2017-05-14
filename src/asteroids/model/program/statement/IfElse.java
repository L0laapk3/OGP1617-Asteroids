package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.IExpression;

public class IfElse extends VariableContextContainer<IStatement> {
	
	private boolean conditionDone = false;
	private boolean conditionResult;

	public IfElse(IExpression<? extends Boolean> condition, IStatement ifBody, IStatement elseBody) throws ProgramException {
		super(condition, ifBody, elseBody);
	}
	
	@SuppressWarnings("unchecked")
	public IExpression<? extends Boolean> getCondition() { return (IExpression<? extends Boolean>)statements[0]; }
	public IStatement getOnTrue() { return statements[1]; }
	public IStatement getOnFalse() { return statements[2]; }

	@Override
	public boolean step(Program program) throws ProgramException {
		if (!conditionDone) {
			if (getCondition().step(program))
				return true;
			conditionDone = true;
			conditionResult = getCondition().evaluate(program);
			return true;
		} else if (conditionResult)
			return getOnTrue().step(program);
		else
			return getOnFalse().step(program);
	}
	
	@Override
	public void reset(Program program) {
		conditionDone = false;
		super.reset(program);
	}
	
	@Override
	public double getRequiredTime() throws ProgramException {
		if (!conditionDone)
			return getCondition().getRequiredTime();
		else if (conditionResult)
			return getOnTrue().getRequiredTime();
		else
			return getOnFalse().getRequiredTime();
	}
}
