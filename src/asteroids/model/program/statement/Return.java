package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.FunctionContextContainer;
import asteroids.model.program.expression.IExpression;
import asteroids.model.program.expression.IFunctionContextAwareStatement;
import asteroids.model.program.expression.VariableContextContainer;

public class Return extends SingleContainerStatement<IExpression<? extends Object>> implements IFunctionContextAwareStatement {
	
	public Return(IExpression<? extends Object> expression) throws ProgramException {
		super(expression);
	}

	public boolean selfStep(Program program) throws ProgramException {
		this.functionContext.doReturn(statement.evaluate(program));
		return false;
	}
	
	

	
	private VariableContextContainer variableContext = null;
	@Override public void saveVariableContext(VariableContextContainer variableContext) { this.variableContext = variableContext; }
	@Override public VariableContextContainer getVariableContext() { return this.variableContext; };
	
	private FunctionContextContainer functionContext = null;
	@Override public void saveFunctionContext(FunctionContextContainer functionContext) { this.functionContext = functionContext; }
	@Override public FunctionContextContainer getFunctionContext() { return this.functionContext; }
}
