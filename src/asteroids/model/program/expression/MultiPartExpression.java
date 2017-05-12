package asteroids.model.program.expression;

import java.util.Arrays;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.IContainerStatement;
import asteroids.model.program.statement.MultiContainerStatement;

public abstract class MultiPartExpression<T extends Object> extends MultiContainerStatement<IExpression<? extends T>> implements IContainerStatement {

	protected final T results[];
	
	@SafeVarargs //not actually writing to expressiosn
	@SuppressWarnings("unchecked") //T extends object
	public MultiPartExpression(IExpression<? extends T>... expressions) throws ProgramException {
		super(expressions);
		results = (T[])new Object[expressions.length];
	}

	@Override
	protected void afterEachExpressionFinish(Program program) throws ProgramException {
		results[this.stepdone] = this.statements[this.stepdone].evaluate(program);
	}
	
	@Override
	public void reset(Program program) {
		super.reset(program);
		Arrays.fill(results, null);
	}
}
