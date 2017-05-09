package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public abstract class ContextContainer extends Statement {

	protected ContextContainer(Statement statement) {
		super();
		statement.makeContextAware(this);
	}

	private boolean doBreak = false;
	
	protected void doBreak() {
		this.doBreak = true;
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		return !doBreak;
	}
	
}
