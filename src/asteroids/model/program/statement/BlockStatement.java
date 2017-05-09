package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class BlockStatement extends Statement {
	
	public final Statement[] statements;
	protected int i = 0;
	public final boolean isEmpty;
	
	public BlockStatement() {
		super();
		this.statements = new Statement[0];
		isEmpty = true;
	}
	public BlockStatement(Statement... statements) {
		super();
		this.statements = statements;
		isEmpty = false;
	}

	@Override
	public boolean step(Program program) throws ProgramException {
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
		if (!isEmpty)
			for (Statement statement : statements)
				statement.reset(program);
		super.reset(program);
	}
	
	
	@Override
	protected void makeContextAware(ContextContainer context) {
		if (!isEmpty)
			for (Statement statement : statements)
				statement.makeContextAware(context);
	}
}
