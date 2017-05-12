package asteroids.model.program.statement;

import asteroids.exceptions.NullStatementException;
import asteroids.exceptions.ProgramException;
import asteroids.util.OGUtil;

public abstract class Statement implements IStatement {
	
	//TODO: zien ofda alle subclasses wel super() callen bij create
	protected Statement(IStatement... statements) throws ProgramException {
		OGUtil.print("creating " + this + " with children: ");
		for (IStatement statement : statements) {
			if (statement == null)
				throw new NullStatementException();
			OGUtil.print(statement + " ");
		}
		OGUtil.println("");
		this.childStatements = statements;
	}
	
	protected final IStatement[] childStatements;
	public IStatement[] getChildStatements() { return childStatements; }
}
