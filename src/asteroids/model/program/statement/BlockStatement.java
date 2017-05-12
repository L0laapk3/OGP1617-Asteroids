package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class BlockStatement extends Statement {
	
	protected int i = 0;
	public final boolean isEmpty;
	
	public BlockStatement() throws ProgramException {
		super();
		isEmpty = true;
	}
	public BlockStatement(IStatement... statements) throws ProgramException {
		super(statements);
		isEmpty = false;
	}

	@Override
	public boolean step(Program program) throws ProgramException {
		if (isEmpty)
			return false;
		if (!this.childStatements[i].step(program)) //if false: statement is done, go to next statement
			i++;
		return i < this.childStatements.length;
	}
	
	
	@Override
	public double getRequiredTime() {
		if (isEmpty)
			return 0;
		return this.childStatements[i].getRequiredTime();
	}
	
	
	@Override
	public void reset(Program program) {
		i = 0;
		super.reset(program);
	}
}
