package asteroids.model.program.statement;

import java.util.HashMap;
import java.util.Map;

import asteroids.exceptions.BadClassAssignmentException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public abstract class VariableContextContainer<T extends IStatement> extends StatementWithChildren<T> {
	
	protected VariableContextContainer<? extends IStatement> selfContext = null;
	
	@SafeVarargs
	protected VariableContextContainer(T... statements) throws ProgramException {
		super(statements);
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
	
	public Object getVariable(String name) {
		if (variables.containsKey(name))
			return variables.get(name);
		else if (selfContext != null)
			return selfContext.getVariable(name);
		else
			return null;
	}
	
	public void setVariable(String name, Object value) throws BadClassAssignmentException {
		if (variables.containsKey(name) && variables.get(name).getClass() != value.getClass())
			throw new BadClassAssignmentException();
		variables.put(name, value);
	}
}
