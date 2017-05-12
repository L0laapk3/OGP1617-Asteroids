package asteroids.model.program.statement;

import asteroids.exceptions.NullStatementException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.ContextContainer;
import asteroids.model.program.expression.FunctionContextContainer;
import asteroids.util.OGUtil;

public abstract class Statement {
	
	protected final Statement[] childStatements;
	
	//TODO: zien ofda alle subclasses wel super() callen bij create
	protected Statement(Statement... childStatements) throws ProgramException {
		OGUtil.print("creating " + this + " with children: ");
		for (Statement statement : childStatements) {
			if (statement == null)
				throw new NullStatementException();
			OGUtil.print(statement + " ");
		}
		OGUtil.println("");
		this.childStatements = childStatements;
	}
	
	//returns false only if this is the last step to be executed.
	public abstract boolean step(Program program) throws ProgramException;
	
	protected Statement[] getChildStatements() { return childStatements; }
	
	public void setContext(ContextContainer context) { 
		for (Statement statement : getChildStatements())
			statement.setContext(context);
	}
	public void setLoopContext(LoopContextContainer context) { 
		for (Statement statement : getChildStatements())
			statement.setLoopContext(context);
	}
	public void setFunctionContext(FunctionContextContainer context) { 
		for (Statement statement : getChildStatements()) 
			statement.setFunctionContext(context);
	}
	protected void reset(Program program) {
		for (Statement statement : getChildStatements())
			statement.reset(program);
	}
	
	public double getRequiredTime() {
		return 0;
	}
}
