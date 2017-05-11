package asteroids.model.program.statement;


import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.ContextContainer;

public abstract class LoopContextContainer extends ContextContainer {
	
	protected LoopContextContainer(Statement... statements) {
		super(statements);
		for (Statement statement : statements)
			statement.setLoopContext(this);
	}
	
	public void setLoopContext(LoopContextContainer context) { } //dont overwrite context of child classes

	
	
	
	private boolean doBreak = false;
	
	protected void doBreak() {
		this.doBreak = true;
	}
	
	public boolean step(Program program) throws ProgramException {
		return !doBreak;
	}
	
}
