package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.IExpression;
import asteroids.util.OGUtil;

public class WhileLoop extends LoopContextContainer<IStatement> {
	
	private boolean nextIsCondition = true;
	private boolean firstTime = true;
	
	public WhileLoop(IExpression<? extends Boolean> condition, IStatement body) throws ProgramException {
		super(condition, body);
	}
	
	@SuppressWarnings("unchecked")
	public final IExpression<? extends Boolean> getCondition() { return (IExpression<? extends Boolean>)statements[0]; }
	public final IStatement getStatement() { return statements[1]; }

	
	public boolean step(Program program) throws ProgramException {
		OGUtil.println("while loop step, nextIsCondition: " + nextIsCondition);
		if (nextIsCondition) {
			if (getCondition().step(program))
				return true;
			nextIsCondition = false;
			if (firstTime)
				firstTime = false;
			else
				getStatement().reset(program);
			return (boolean)getCondition().evaluate(program);
		}
		
		boolean step = getStatement().step(program);
		OGUtil.println("body step result: " + step);
		if(!step) {
			nextIsCondition = true;
			getCondition().reset(program);
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
	public double getRequiredTime() throws ProgramException {
		if (nextIsCondition)
			return getCondition().getRequiredTime();
		return getStatement().getRequiredTime();
	}
}
