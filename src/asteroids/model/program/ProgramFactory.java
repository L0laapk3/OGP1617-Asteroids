package asteroids.model.program;

import java.util.List;

import asteroids.model.program.expression.*;
import asteroids.model.program.statement.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.OGUtil;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Statement, Program> {

	@Override
	public Program createProgram(List<Statement> functions, Statement main) {
		Statement[] statements = new Statement[functions.size() + 1];
		functions.toArray(statements);
		statements[statements.length - 1] = main;
		return new Program(new BlockStatement(statements));
	}

	@Override
	public Statement createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new DefineFunction(functionName, body);
	}

	@Override
	public Expression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new ReadParameter(parameterName);
	}

	@Override
	public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new CallFunction(functionName, actualArgs);
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
		return new BlockStatement(statements.toArray(new Statement[statements.size()]));
	}

	@Override
	public Expression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new ReadVariable(variableName);
	}

	@Override
	public Expression createChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Negative((Numeric)expression);
	}

	@Override
	public Expression createNotExpression(Expression expression, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Invert((Condition)expression);
	}

	@Override
	public Expression createDoubleLiteralExpression(double value, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new ConstantNumber(value);
	}

	@Override
	public Expression createNullExpression(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new FindEntity(FindEntity.Filter.NULL);
	}

	@Override
	public Expression createSelfExpression(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new FindEntity(FindEntity.Filter.SELF);
	}

	@Override
	public Expression createShipExpression(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new FindEntity(FindEntity.Filter.SHIP);
	}

	@Override
	public Expression createAsteroidExpression(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new FindEntity(FindEntity.Filter.ASTEROID);
	}

	@Override
	public Expression createPlanetoidExpression(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new FindEntity(FindEntity.Filter.PLANETOID);
	}

	@Override
	public Expression createBulletExpression(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new FindEntity(FindEntity.Filter.BULLET);
	}

	@Override
	public Expression createPlanetExpression(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new FindEntity(FindEntity.Filter.PLANET);
	}

	@Override
	public Expression createAnyExpression(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new FindEntity(FindEntity.Filter.ANY);
	}

	@Override
	public Expression createGetXExpression(Expression e, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new GetAttribute(GetAttribute.Attribute.X, (EntityExpression)e);
	}

	@Override
	public Expression createGetYExpression(Expression e, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new GetAttribute(GetAttribute.Attribute.Y, (EntityExpression)e);
	}

	@Override
	public Expression createGetVXExpression(Expression e, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new GetAttribute(GetAttribute.Attribute.VX, (EntityExpression)e);
	}

	@Override
	public Expression createGetVYExpression(Expression e, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new GetAttribute(GetAttribute.Attribute.VY, (EntityExpression)e);
	}

	@Override
	public Expression createGetRadiusExpression(Expression e, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new GetAttribute(GetAttribute.Attribute.RADIUS, (EntityExpression)e);
	}

	@Override
	public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new LessThan(e1, e2);
	}

	@Override
	public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Equality(e1, e2);
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Addition(e1, e2);
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Multiplication(e1, e2);
	}

	@Override
	public Expression createSqrtExpression(Expression e, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Sqrt(e);
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new GetAttribute(GetAttribute.Attribute.DIRECTION, new FindEntity(FindEntity.Filter.SELF));
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new SetThruster(true);
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new SetThruster(false);
	}

	@Override
	public Statement createFireStatement(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Fire();
	}

	@Override
	public Statement createTurnStatement(Expression angle, SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Turn(angle);
	}

	@Override
	public Statement createSkipStatement(SourceLocation sourceLocation) {
		OGUtil.println(sourceLocation);
		return new Skip();
	}

}
