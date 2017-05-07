package asteroids.program;

import asteroids.exceptions.ProgramErrorException;

public class BlockStatement extends Statement {
	
	public final Statement[] statements;
	protected int i = 0;
	public final boolean isEmpty;
	
	protected BlockStatement() {
		super();
		this.statements = new Statement[0];
		isEmpty = true;
	}
	protected BlockStatement(Statement... statements) {
		super();
		this.statements = statements;
		isEmpty = false;
	}

	@Override
	protected boolean step(Program program) throws ProgramErrorException {
		if (isEmpty)
			return false;
		if (!getNextStatement().step(program)) //if false: statement is done, go to next statement
			toNextStatement();
		return i < statements.length;
	}
	
	protected void toNextStatement() {
		i++;
	}

	
	public Statement getNextStatement() {
		if (isEmpty)
			return null;
		return statements[i];
	}
	
	@Override
	public double getRequiredTime() {
		if (isEmpty)
			return 0;
		return getNextStatement().getRequiredTime();
	}
	
	
	@Override
	protected void reset(Program program) {
		i = 0;
		resetAll(program);
	}
	
	protected void resetAll(Program program) {
		if (!isEmpty)
			for (Statement statement : statements)
				statement.reset(program);
	}
}
