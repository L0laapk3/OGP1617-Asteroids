package asteroids.model.program.expression;

import java.util.Arrays;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.ContainerStatement;

public abstract class MultiContainerExpression<T extends Object> extends ContainerStatement<IExpression<? extends T>> {

	private final T[] results;
	
	@SafeVarargs //not actually writing to expressiosn
	@SuppressWarnings("unchecked") //T extends object
	public MultiContainerExpression(IExpression<? extends T>... expressions) throws ProgramException {
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
}
