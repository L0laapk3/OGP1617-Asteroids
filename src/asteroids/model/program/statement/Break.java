 package asteroids.model.program.statement;

import asteroids.exceptions.BadBreakStatementException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.LoopContextAwareExpression;

public class Break extends LoopContextAwareExpression {

	public Break() throws ProgramException {
		super();
	}

	@Override
	public boolean step(Program program) throws BadBreakStatementException {
		this.loopContext.doBreak();
		return false;
	}

	@Override
	public Object evaluate(Program program) throws ProgramException {
		throw new ProgramException("Internal program exception: evaluate was called on a statement.");
	}
}
