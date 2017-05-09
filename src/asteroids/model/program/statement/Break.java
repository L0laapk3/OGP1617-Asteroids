package asteroids.model.program.statement;

import asteroids.model.program.Program;

public class Break extends ContextAwareStatement {

	public Break() {
		super();
	}

	@Override
	public boolean step(Program program) {
		this.context.doBreak();
		return false;
	}

}
