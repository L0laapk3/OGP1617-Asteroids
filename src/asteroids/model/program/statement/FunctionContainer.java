package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class FunctionContainer extends FunctionContextContainer<IStatement> {
	
	public final double firstRequiredTime;
	public FunctionContainer(IStatement statement) throws ProgramException {
		super(statement);
		firstRequiredTime = getRequiredTime();
	}
	
	public FunctionContainer(FunctionContainer original, VariableContextContainer<? extends IStatement> selfContext) throws ProgramException {
		super(original.statements[0].clone());
		this.selfContext = selfContext;
		firstRequiredTime = original.firstRequiredTime;
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		return statements[0].step(program) && super.step(program);
	}
	
	@Override
	public double getRequiredTime() throws ProgramException {
		return statements[0].getRequiredTime();
	}
}
