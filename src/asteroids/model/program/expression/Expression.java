package asteroids.model.program.expression;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.Statement;

public abstract class Expression extends Statement {
	
	public Expression(Statement... statements) {
		super(statements);
	}

	@Override
	public boolean step(Program program) throws ProgramException {
		return false;
	}
	
	//evaluate may only be called after step() returned false.
	public abstract Object evaluate(Program program) throws ProgramException;
}
