package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.Statement;

public class FunctionContainer extends FunctionContextContainer {
	
	public final Statement statement;
	public FunctionContainer(Statement statement) {
		super(statement);
		this.statement = statement;
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		return statement.step(program);
	}
	
	@Override
	public double getRequiredTime() {
		return statement.getRequiredTime();
	}


}
