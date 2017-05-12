 package asteroids.model.program.statement;

import asteroids.exceptions.BadBreakStatementException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.VariableContextContainer;
import asteroids.model.program.expression.ILoopContextAwareStatement;

public class Break extends Statement implements ILoopContextAwareStatement {

	public Break() throws ProgramException {
		super();
	}

	@Override
	public boolean step(Program program) throws BadBreakStatementException {
		this.loopContext.doBreak();
		return false;
	}
	

	private VariableContextContainer variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer getVariableContext() { return this.variableContext; };

	private LoopContextContainer loopContext = null;
	@Override public void saveLoopContext(LoopContextContainer loopContext) { this.loopContext = loopContext; }
	@Override public LoopContextContainer getLoopContext() { return this.loopContext; };

	
}
