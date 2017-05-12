package asteroids.model.program.statement;

import java.util.Arrays;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public abstract class MultiContainerStatement<T extends IStatement> extends Statement implements IContainerStatement {

	protected final T[] statements;
	protected int stepdone = 0;
	
	@SafeVarargs
	public MultiContainerStatement(T... statements) throws ProgramException {
		super(statements);
		this.statements = statements;
	}


	protected void afterEachExpressionFinish(Program program) throws ProgramException { }

	@Override
	public final boolean step(Program program) throws ProgramException {
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
	public final double getRequiredTime() {
		if (stepdone < statements.length)
			return statements[stepdone].getRequiredTime();
		else
			return selfGetRequiredTime();
	}
	
	@Override
	public void reset(Program program) {
		super.reset(program);
		Arrays.fill(statements, false);
		stepdone = 0;
	}
	
	
	
	
	@Override
	public IStatement[] getChildStatements() {
		return statements;
	}
}
