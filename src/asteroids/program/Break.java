package asteroids.program;

public class Break extends ContextAwareStatement {

	protected Break() {
		super();
	}

	@Override
	protected boolean step(Program program) {
		this.context.doBreak();
		return false;
	}

}
