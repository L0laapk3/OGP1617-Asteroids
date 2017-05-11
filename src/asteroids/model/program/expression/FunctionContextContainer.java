package asteroids.model.program.expression;


import java.util.HashMap;
import java.util.Map;

import asteroids.exceptions.BadBreakStatementException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.LoopContextContainer;
import asteroids.model.program.statement.Statement;

public abstract class FunctionContextContainer extends LoopContextContainer {
	
	protected FunctionContextContainer(Statement... statements) {
		super(statements);
		for (Statement statement : statements)
			statement.setFunctionContext(this);
	}
	
	@Override
	public void setFunctionContext(FunctionContextContainer context) { } //dont overwrite context of child classes

	@Override
	public void doBreak() {
		throw new RuntimeException(new BadBreakStatementException());
	}
	
	
	protected boolean doReturn = false;
	protected Object returnValue;
	
	public void doReturn(Object value) {
		this.doReturn = true;
		this.returnValue = value;
	}
	
	public boolean getIsReturned() {
		return this.doReturn;
	}
	
	public Object getReturnValue() {
		return this.returnValue;
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		return !doReturn;
	}
	
	@Override
	public Object evaluate(Program program) throws ProgramException {
		return returnValue;
	}
	
	
	

	private Map<String, Object> arguments = new HashMap<String, Object>();
	
	public Object getArgument(String name) {
		if (arguments.containsKey(name))
			return arguments.get(name);
		return null;
	}
	
	public void setArgument(String name, Object value) {
		arguments.put(name, value);
	}
	
	public void clearArguments() {
		arguments.clear();
	}
}
