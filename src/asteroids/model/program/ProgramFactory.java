package asteroids.model.program;

import java.util.List;

import asteroids.model.program.expression.*;
import asteroids.model.program.statement.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.OGUtil;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program> {

	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression value, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Assignment(variableName, value);
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new WhileLoop((Condition)condition, body);
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Break();
	}

	@Override
	public Statement createReturnStatement(Expression value, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Return(value);
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new IfElse((Condition)condition, ifBody, elseBody);
	}

	@Override
	public Statement createPrintStatement(Expression value, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Print(value);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new BlockStatement((Statement[]) statements.toArray());
	}

	@Override
	public Expression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Variable(variableName);
	}

	@Override
	public Expression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Negative<Number>((Number)expression);
	}

	@Override
	public Expression createNotExpression(Expression expression, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Invert(expression);
	}

	@Override
	public Expression createDoubleLiteralExpression(double value, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new ;
	}

	@Override
	public Expression createNullExpression(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSelfExpression(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createShipExpression(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAsteroidExpression(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createPlanetoidExpression(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createBulletExpression(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createPlanetExpression(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAnyExpression(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetXExpression(Expression e, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetYExpression(Expression e, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetVXExpression(Expression e, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetVYExpression(Expression e, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetRadiusExpression(Expression e, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSqrtExpression(Expression e, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createFireStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createTurnStatement(Expression angle, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSkipStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

}
