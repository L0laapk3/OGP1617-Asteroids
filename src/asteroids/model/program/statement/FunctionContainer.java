package asteroids.model.program.statement;

import asteroids.exceptions.ForSomeReasonNotAllowedError;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.util.OGUtil;

public class FunctionContainer extends FunctionContextContainer<IStatement> {
	
	private boolean hasActionOrPrint = false;
	public final double firstRequiredTime;
	public FunctionContainer(IStatement statement) throws ProgramException {
		super(statement);
		firstRequiredTime = getRequiredTime();
		for (IStatement child : getAllChildren())
			if ((child instanceof IAction) || (child instanceof Print))
				hasActionOrPrint = true;
		OGUtil.println(this + " new hasprintoraction: " + hasActionOrPrint);
		
	}
	
	public FunctionContainer(FunctionContainer original, VariableContextContainer<? extends IStatement> selfContext) throws ProgramException {
		super(original.statements[0].clone());
		//TODO: contexts worden niet geupdate blijkbaar :(
		OGUtil.println("cloned " + original +  " to " + this);
		this.selfContext = original.selfContext;
		firstRequiredTime = original.firstRequiredTime;
		this.hasActionOrPrint = original.hasActionOrPrint;
		initChildsContext();
		//this.setFunctionContext(this); //set function context, but not variable context
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		OGUtil.println(this + " hasprintoraction: " + hasActionOrPrint);
		if (hasActionOrPrint && (program.main != this))
			throw new ForSomeReasonNotAllowedError("Prints or actions are for some reason not allowed inside functions.");
		return statements[0].step(program) && super.step(program);
	}
	
	@Override
	public double getRequiredTime() throws ProgramException {
		return statements[0].getRequiredTime();
	}
}
