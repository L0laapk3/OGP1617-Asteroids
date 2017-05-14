package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.statement.IStatement;
import asteroids.model.program.statement.IVariableContextAwareStatement;
import asteroids.model.program.statement.Statement;
import asteroids.model.program.statement.VariableContextContainer;

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
	
	@Override
	public boolean step(Program program) {
		return false;
	}
	
	

	private VariableContextContainer<? extends IStatement> variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer<? extends IStatement> variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer<? extends IStatement> getVariableContext() { return this.variableContext; };
}
