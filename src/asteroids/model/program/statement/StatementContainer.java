package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public abstract class StatementContainer<T extends IStatement> extends StatementWithChildren<T> implements IContainerStatement {
	
	public final boolean isEmpty;
	protected int stepdone = 0;
	
	@SafeVarargs
	public StatementContainer(T... statements) throws ProgramException {
		super(statements);
		isEmpty = false;
	}
	
	public StatementContainer() throws ProgramException {
		super();
		isEmpty = true;
	}


	protected void afterEachExpressionFinish(Program program) throws ProgramException { }

	@Override
	public final boolean step(Program program) throws ProgramException {
		if (isEmpty)
			return false;
		if (stepdone < statements.length) {
			if (!statements[stepdone].step(program)) {
				afterEachExpressionFinish(program);
				stepdone++;
			}
			return true;
		} else
			return selfStep(program);
	}

	@Override
	public final double getRequiredTime() throws ProgramException {
		if (isEmpty)
			return 0;
		if (stepdone < statements.length)
			return statements[stepdone].getRequiredTime();
		else
			return selfGetRequiredTime();
	}
	
	@Override
	public void reset(Program program) {
		super.reset(program);
		stepdone = 0;
	}
}
