package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public abstract class SingleContainerStatement<T extends IStatement> extends Statement implements IContainerStatement {

	protected T statement;
	
	protected SingleContainerStatement(T statement) throws ProgramException {
		super(statement);
		this.statement = statement;
	}
	

	/*
	 * !!!!!!!IMPORTANT!!!!
	 * when extending this class, use the wrappers instead of original functions:
	 * boolean selfStep
	 * double  selfGetRequredTime
	 */
	
	
	
	protected boolean done = false;
	
	@Override
	public IStatement[] getChildStatements() { return new IStatement[] { statement }; }

	@Override
	public final boolean step(Program program) throws ProgramException {
		if (!done) {
			if (!statement.step(program))
				done = true;
			return true;
		}
		return selfStep(program);
	}
	
	@Override
	public double getRequiredTime() {
		if (done)
			return selfGetRequiredTime();
		return statement.getRequiredTime();
	}

	
	@Override
	public void reset(Program program) {
		super.reset(program);
		statement.reset(program);
		done = false;
	}
}
