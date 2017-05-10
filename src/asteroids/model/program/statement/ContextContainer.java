package asteroids.model.program.statement;

import java.util.HashMap;
import java.util.Map;

import asteroids.model.program.Program;

public abstract class ContextContainer extends Statement {
	
	protected ContextContainer selfContext = null;
	
	protected ContextContainer(Statement... statements) {
		super(statements);
		for (Statement statement : statements)
			statement.setContext(this);
	}
	
	@Override
	protected void setContext(ContextContainer context) { //dont overwrite context of child classes, instead save to selfContext
		selfContext = context;
	}

	
	
	private Map<String, Object> variables = new HashMap<String, Object>();
	
	@Override
	protected void reset(Program program) {
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
	
	public void setVariable(String name, Object value) {
		variables.put(name, value);
	}
}
