package asteroids.model.program.statement;

import java.util.HashMap;
import java.util.Map;

import asteroids.exceptions.BadClassAssignmentException;
import asteroids.exceptions.ProgramException;
import asteroids.exceptions.VariableDoesntExistException;
import asteroids.model.program.Program;
import asteroids.util.OGUtil;

public abstract class VariableContextContainer<T extends IStatement> extends StatementWithChildren<T> {
	
	protected VariableContextContainer<? extends IStatement> selfContext = null;
	
	@SafeVarargs
	protected VariableContextContainer(T... statements) throws ProgramException {
		super(statements);
		initChildsContext();
	}
	
	protected void initChildsContext() {
		for (T statement : statements)
			statement.setContext(this);
	}

	@Override
	public void setContext(VariableContextContainer<? extends IStatement> context) { //dont overwrite context of child classes, instead save to selfContext
		selfContext = context;
	}

	
	
	private Map<String, Object> variables = new HashMap<String, Object>();
	
	public void reset(Program program) {
		variables.clear();
		super.reset(program);
	}
	
	public Object getVariable(String name) throws VariableDoesntExistException {
		OGUtil.println("try get " + name + " from " + this);
		if (variables.containsKey(name)) {
			OGUtil.println("get " + name + " from " + this);
			return variables.get(name);
		} else if (selfContext != null)
			return selfContext.getVariable(name);
		else
			throw new VariableDoesntExistException();
	}
	
	
	
	protected boolean setIfContains(String name, Object value) throws BadClassAssignmentException {
		//OGUtil.println("try put " + name + " in " + this);
		if (variables.containsKey(name) && !((variables.get(name) instanceof FunctionContainer) && !(value instanceof FunctionContainer))) {
			if (variables.get(name).getClass() != value.getClass())
				throw new BadClassAssignmentException();
			else {
				//OGUtil.println("put " + name + " to " + this);
				variables.put(name, value);
				return true;
			}
		} else if (selfContext != null)
			return selfContext.setIfContains(name, value);
		else
			return false;
	}
	
	public void setVariable(String name, Object value) throws BadClassAssignmentException {
		if (!setIfContains(name, value)) {
			if (variables.containsKey(name)) // && (variables.get(name) instanceof FunctionContainer)) //not needed, guarranteed
				throw new BadClassAssignmentException();
			variables.put(name, value);
			//OGUtil.println("put " + name + " to " + this);
		}
	}
	
	
	
	@Override
	public VariableContextContainer<T> clone() {
		VariableContextContainer<T> n = (VariableContextContainer<T>)super.clone();
		n.initChildsContext();
		return n;
	}
}
