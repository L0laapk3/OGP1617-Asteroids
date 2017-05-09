package asteroids.model.program.expression;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.Statement;

public abstract class Expression extends Statement {
	
	protected Expression() {
		super();
	}
	
	@Override
	public boolean step(Program program) {
		return false;
	}
	
	public abstract Object evaluate(Program program) throws ProgramException;
}
