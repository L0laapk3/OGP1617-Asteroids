package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.IExpression;

public class Return extends ContainerStatement<IExpression<? extends Object>> implements IFunctionContextAwareStatement {
	
	public Return(IExpression<? extends Object> expression) throws ProgramException {
		super(expression);
	}

	public boolean selfStep(Program program) throws ProgramException {
		this.functionContext.doReturn(statements[0].evaluate(program));
		return false;
	}
	
	


	private VariableContextContainer<? extends IStatement> variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer<? extends IStatement> variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer<? extends IStatement> getVariableContext() { return this.variableContext; };
	
	private FunctionContextContainer<? extends IStatement> functionContext = null;
	@Override public void saveFunctionContext(FunctionContextContainer<? extends IStatement> functionContext) { this.functionContext = functionContext; }
	@Override public FunctionContextContainer<? extends IStatement> getFunctionContext() { return this.functionContext; };
}
