package asteroids.model.program;

import java.util.List;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.expression.*;
import asteroids.model.program.statement.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Statement, Program> {

	@Override
	public Program createProgram(List<Statement> functions, Statement main) {
		Statement[] statements = new Statement[functions.size() + 1];
		functions.toArray(statements);
		statements[statements.length - 1] = main;
		try {
			return new Program(new BlockStatement(statements));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
		try {
			return new DefineFunction(functionName, body);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		try {
			return new ReadParameter(parameterName);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation) {
		try {
			return new CallFunction(functionName, actualArgs);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression value, SourceLocation sourceLocation) {
		try {
			return new Assignment(variableName, value);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body, SourceLocation sourceLocation) {
		try {
			return new WhileLoop((Expression & ICondition)condition, body);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		try {
			return new Break();
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createReturnStatement(Expression value, SourceLocation sourceLocation) {
		try {
			return new Return(value);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		try {
			return new IfElse((Expression & ICondition)condition, ifBody, elseBody);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createPrintStatement(Expression value, SourceLocation sourceLocation) {
		try {
			return new Print(value);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		try {
			return new BlockStatement(statements.toArray(new Statement[statements.size()]));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		try {
			return new ReadVariable(variableName);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
		try {
			return new Negative((Expression & INumeric)expression);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createNotExpression(Expression expression, SourceLocation sourceLocation) {
		try {
			return new Invert((Expression & ICondition)expression);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createDoubleLiteralExpression(double value, SourceLocation sourceLocation) {
		try {
			return new ConstantNumber(value);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createNullExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.NULL);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createSelfExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.SELF);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createShipExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.SHIP);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createAsteroidExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.ASTEROID);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createPlanetoidExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.PLANETOID);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createBulletExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.BULLET);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createPlanetExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.PLANET);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createAnyExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.ANY);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createGetXExpression(Expression e, SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.X, (Expression & IEntity)e);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createGetYExpression(Expression e, SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.Y, (Expression & IEntity)e);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createGetVXExpression(Expression e, SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.VX, (Expression & IEntity)e);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createGetVYExpression(Expression e, SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.VY, (Expression & IEntity)e);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createGetRadiusExpression(Expression e, SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.RADIUS, (Expression & IEntity)e);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		try {
			return new LessThan((Expression & INumeric)e1, (Expression & INumeric)e2);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		try {
			return new Equality(e1, e2);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		try {
			return new Addition((Expression & INumeric)e1, (Expression & INumeric)e2);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation sourceLocation) {
		try {
			return new Multiplication((Expression & INumeric)e1, (Expression & INumeric)e2);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createSqrtExpression(Expression e, SourceLocation sourceLocation) {
		try {
			return new Sqrt(e);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.DIRECTION, new FindEntity(FindEntity.Filter.SELF));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation sourceLocation) {
		try {
			return new SetThruster(new ConstantBool(true));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation sourceLocation) {
		try {
			return new SetThruster(new ConstantBool(false));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createFireStatement(SourceLocation sourceLocation) {
		try {
			return new Fire();
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createTurnStatement(Expression angle, SourceLocation sourceLocation) {
		try {
			return new Turn((Expression & INumeric)angle);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Statement createSkipStatement(SourceLocation sourceLocation) {
		try {
			return new Skip();
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

}
