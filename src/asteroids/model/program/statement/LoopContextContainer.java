package asteroids.model.program.statement;


import asteroids.exceptions.BadBreakStatementException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public abstract class LoopContextContainer<T extends IStatement> extends VariableContextContainer<T> {
	
	@SafeVarargs
	protected LoopContextContainer(T... statements) throws ProgramException {
		super(statements);
		for (T statement : statements)
			statement.setLoopContext(this);
	}

	public void setLoopContext(LoopContextContainer<? extends IStatement> context) { } //dont overwrite context of child classes

	
	
	
	private boolean doBreak = false;
	
	protected void doBreak() throws BadBreakStatementException {
		this.doBreak = true;
	}
	
	public boolean step(Program program) throws ProgramException {
		return !doBreak;
	}
	
}
