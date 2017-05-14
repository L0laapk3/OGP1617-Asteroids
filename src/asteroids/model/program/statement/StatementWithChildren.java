package asteroids.model.program.statement;

import asteroids.exceptions.NullStatementException;
import asteroids.exceptions.ProgramException;

public abstract class StatementWithChildren<T extends IStatement> extends Statement implements IStatementWithChildren<T> {
	
	@SafeVarargs
	protected StatementWithChildren(T... statements) throws ProgramException {
		super();
		for (T statement : statements)
			if (statement == null)
				throw new NullStatementException();
		this.statements = statements;
	}
	
	public final T[] statements;
	public T[] getChildStatements() { return statements; }
	
	

	@SuppressWarnings("unchecked") //TODO: ???
	public StatementWithChildren<T> clone() {
		StatementWithChildren<T> n = (StatementWithChildren<T>)super.clone();
		for (int i = 0; i < this.statements.length; i++) {
			T clone = (T)this.statements[i].clone();
			//OGUtil.println("changing child " + n.statements[i] + " to " + clone);
			n.statements[i] = clone;
		}
		return n;
	}
}
