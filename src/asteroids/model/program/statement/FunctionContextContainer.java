package asteroids.model.program.statement;


import java.util.HashMap;
import java.util.Map;

import asteroids.exceptions.BadBreakStatementException;
import asteroids.exceptions.NoArgumentException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.IExpression;
import asteroids.util.OGUtil;

public abstract class FunctionContextContainer<T extends IStatement> extends LoopContextContainer<T> implements IExpression<Object> {
	
	@SafeVarargs
	protected FunctionContextContainer(T... statements) throws ProgramException {
		super(statements);
	}
	
	@Override
	protected void initChildsContext() {
		super.initChildsContext();
		for (T statement : statements)
			statement.setFunctionContext(this);
	}
	
	@Override
	public void setFunctionContext(FunctionContextContainer<? extends IStatement> context) { } //dont overwrite context of child classes

	@Override
	public void doBreak() throws BadBreakStatementException {
		throw new BadBreakStatementException();
	}
	
	
	protected boolean doReturn = false;
	private Object returnValue = null;
	
	public void doReturn(Object value) {
		this.doReturn = true;
		this.returnValue = value;
		OGUtil.println("registered doreturn " + this.returnValue + " " + this);
	}
	
	public boolean getIsReturned() {
		return this.doReturn;
	}
	
	public Object getReturnValue() {
		return this.returnValue;
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		OGUtil.println(this + " step");
		return !doReturn;
	}
	
	@Override
	public Object evaluate(Program program) throws ProgramException {
		OGUtil.println("asked return value " + this.doReturn + " " + this.returnValue + " " + this);
		return this.returnValue;
	}
	
	@Override
	public void reset(Program program) {
		super.reset(program);
		this.returnValue = null;
		this.doReturn = false;
	}
	
	
	

	private Map<String, Object> arguments = new HashMap<String, Object>();
	
	public Object getArgument(String name) throws NoArgumentException {
		if (arguments.containsKey(name))
			return arguments.get(name);
		throw new NoArgumentException();
	}
	
	public void setArgument(String name, Object value) {
		OGUtil.println("setArgument: " + name + " " + value );
		arguments.put(name, value);
	}
	
	public void clearArguments() {
		arguments.clear();
	}
}
