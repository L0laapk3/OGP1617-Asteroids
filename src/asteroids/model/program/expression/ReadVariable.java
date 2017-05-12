package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.Statement;

public class ReadVariable extends Statement implements IExpression<Object>, IVariableContextAwareStatement {
	
	private final String varname;
	
	public ReadVariable(String varname) throws ProgramException {
		super();
		this.varname = varname;
	}
	
	public Object evaluate(Program program) throws ProgramException {
		System.out.println(this.variableContext);
		return this.variableContext.getVariable(varname);
	}
	
	
	

	private VariableContextContainer variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer getVariableContext() { return this.variableContext; };
}
