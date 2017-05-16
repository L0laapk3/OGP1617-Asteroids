package asteroids.model.program.statement;

import java.util.Arrays;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.IExpression;

public abstract class ExpressionContainer<T extends Object> extends StatementContainer<IExpression<? extends T>> {

	private T[] results;
	
	@SafeVarargs //not actually writing to expressions
	@SuppressWarnings("unchecked")
	public ExpressionContainer(IExpression<? extends T>... expressions) throws ProgramException {
		super(expressions);
		results = (T[])new Object[expressions.length];
	}
	
	protected T getResult(int index) { return this.results[index]; }

	@Override
	protected void afterEachExpressionFinish(Program program) throws ProgramException {
		results[this.stepdone] = this.statements[this.stepdone].evaluate(program);
	}
	
	@Override
	public void reset(Program program) {
		super.reset(program);
		Arrays.fill(results, null);
	}
	
	

	@Override
	public ExpressionContainer<T> clone() {
		ExpressionContainer<T> n = (ExpressionContainer<T>)super.clone();
		n.results = results.clone();
		return n;
	}
}
