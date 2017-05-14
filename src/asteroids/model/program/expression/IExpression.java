package asteroids.model.program.expression;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.IContainerStatement;
import asteroids.model.program.statement.IStatement;

public interface IExpression<T extends Object> extends IStatement, IContainerStatement {

	public default boolean step(Program program) throws ProgramException { return false; }
	public default boolean selfStep(Program program) throws ProgramException { return false; }
	
	//evaluate may only be called after step() returned false.
	public abstract T evaluate(Program program) throws ProgramException;
	
	
	
	@SuppressWarnings("unchecked")
	public default <U extends Object> IExpression<? extends U> castTo(Class<U> c) throws ProgramException {
		try {
			System.out.println("from " + this + " to " + c);
			return (IExpression<? extends U>)this;
		} catch (ClassCastException e) {
			throw new ProgramException(e);
		}
	}
}
