 package asteroids.model.program.statement;

import asteroids.exceptions.BadBreakStatementException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.util.OGUtil;

public class Break extends Statement implements ILoopContextAwareStatement {

	public Break() throws ProgramException {
		super();
	}

	@Override
	public boolean step(Program program) throws BadBreakStatementException {
		OGUtil.println("BREAKING: " + this.loopContext);
		this.loopContext.doBreak();
		return false;
	}
	

	private VariableContextContainer<? extends IStatement> variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer<? extends IStatement> variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer<? extends IStatement> getVariableContext() { return this.variableContext; };
	
	private LoopContextContainer<? extends IStatement> loopContext = null;
	@Override public void saveLoopContext(LoopContextContainer<? extends IStatement> loopContext) { this.loopContext = loopContext; }
	@Override public LoopContextContainer<? extends IStatement> getLoopContext() { return this.loopContext; };

	
}
