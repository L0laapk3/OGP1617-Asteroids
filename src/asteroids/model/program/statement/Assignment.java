package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.VariableContextContainer;
import asteroids.model.program.expression.IVariableContextAwareStatement;
import asteroids.model.program.expression.IExpression;

public class Assignment extends SingleContainerStatement<IExpression<? extends Object>> implements IVariableContextAwareStatement {

	public final String varname;
	
	public Assignment(String varname, IExpression<? extends Object> expression) throws ProgramException {
		super(expression);
		this.varname = varname;
	}

	@Override
	public boolean selfStep(Program program) throws ProgramException {
		this.variableContext.setVariable(varname, this.statement.evaluate(program));
		return false;
	}
	
	
	
	private VariableContextContainer variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer getVariableContext() { return this.variableContext; };

}
