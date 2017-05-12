package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.IStatement;
import asteroids.util.OGUtil;

public class FunctionContainer extends FunctionContextContainer {
	
	public final IStatement statement;
	public FunctionContainer(IStatement statement) throws ProgramException {
		super(statement);
		this.statement = statement;
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		OGUtil.println("run step on function " + this);
		return statement.step(program) && super.step(program);
	}
	
	@Override
	public double getRequiredTime() {
		return statement.getRequiredTime();
	}


}
