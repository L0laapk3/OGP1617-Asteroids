package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.IExpression;

public class Assignment extends ContainerStatement<IExpression<? extends Object>> implements IVariableContextAwareStatement {

	public final String varname;
	
	public Assignment(String varname, IExpression<? extends Object> expression) throws ProgramException {
		super(expression);
		this.varname = varname;
	}

	@Override
	public boolean selfStep(Program program) throws ProgramException {
		this.variableContext.setVariable(varname, this.statements[0].evaluate(program));
		return false;
	}
	
	

	private VariableContextContainer<? extends IStatement> variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer<? extends IStatement> variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer<? extends IStatement> getVariableContext() { return this.variableContext; };

}
