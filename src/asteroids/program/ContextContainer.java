package asteroids.program;

import asteroids.exceptions.ProgramErrorException;

public abstract class ContextContainer extends BlockStatement {

	protected ContextContainer(BlockStatement statement) {
		super(statement);
		for (Statement singleStatement : statement.statements)
			if (singleStatement instanceof ContextAwareStatement)
				((ContextAwareStatement)singleStatement).context = this;
	}

	private boolean doBreak = false;
	
	public void doBreak() {
		this.doBreak = true;
	}
	
	@Override
	protected boolean step(Program program) throws ProgramErrorException {
		return super.step(program) && !doBreak;
	}
	
}
