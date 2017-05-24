package asteroids.model.program.statement;

import asteroids.exceptions.NullStatementException;
import asteroids.exceptions.ProgramException;

public abstract class StatementWithChildren<T extends IStatement> extends Statement implements IStatementWithChildren<T> {
	
	@SafeVarargs
	protected StatementWithChildren(T... statements) throws ProgramException {
		super();
		for (int i = 0; i < statements.length; i++)
			if (statements[i] == null)
				throw new NullStatementException();
		this.statements = statements;
	}
	
	public T[] statements;
	public T[] getChildren() { return statements; }
	
	

	@SuppressWarnings("unchecked")
	public StatementWithChildren<T> clone() {
		StatementWithChildren<T> n = (StatementWithChildren<T>)super.clone();
		//n.statements = (T[])new IStatement[this.statements.length];
		n.statements = statements.clone();
		for (int i = 0; i < this.statements.length; i++) {
			n.statements[i] = (T)this.statements[i].clone();
		}
		return n;
	}
}
