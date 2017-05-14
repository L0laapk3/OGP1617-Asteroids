package asteroids.model.program;

import java.util.List;

import asteroids.exceptions.ProgramException;
import asteroids.model.Entity;
import asteroids.model.program.expression.Addition;
import asteroids.model.program.expression.CallFunction;
import asteroids.model.program.expression.Constant;
import asteroids.model.program.expression.Equality;
import asteroids.model.program.expression.FindEntity;
import asteroids.model.program.expression.GetAttribute;
import asteroids.model.program.expression.IExpression;
import asteroids.model.program.expression.Invert;
import asteroids.model.program.expression.LessThan;
import asteroids.model.program.expression.Multiplication;
import asteroids.model.program.expression.Negative;
import asteroids.model.program.expression.ReadParameter;
import asteroids.model.program.expression.ReadVariable;
import asteroids.model.program.expression.Sqrt;
import asteroids.model.program.statement.Assignment;
import asteroids.model.program.statement.BlockStatement;
import asteroids.model.program.statement.Break;
import asteroids.model.program.statement.DefineFunction;
import asteroids.model.program.statement.Fire;
import asteroids.model.program.statement.IStatement;
import asteroids.model.program.statement.IfElse;
import asteroids.model.program.statement.Print;
import asteroids.model.program.statement.Return;
import asteroids.model.program.statement.SetThruster;
import asteroids.model.program.statement.Skip;
import asteroids.model.program.statement.StatementWithChildren;
import asteroids.model.program.statement.Turn;
import asteroids.model.program.statement.WhileLoop;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.OGUtil;

public class ProgramFactory implements IProgramFactory<IExpression<? extends Object>, IStatement, DefineFunction, Program> {

	@Override
	public Program createProgram(List<DefineFunction> functions, IStatement main) {
		IStatement[] statements = new StatementWithChildren[functions.size() + 1];
		functions.toArray(statements);
		statements[statements.length - 1] = main;
		try {
			return new Program(new BlockStatement(statements));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public DefineFunction createFunctionDefinition(String functionName, IStatement body, SourceLocation sourceLocation) {
		try {
			return new DefineFunction(functionName, body);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<? extends Object> createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		try {
			return new ReadParameter(parameterName);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public IExpression<? extends Object> createFunctionCallExpression(String functionName, List<IExpression<? extends Object>> actualArgs, SourceLocation sourceLocation) {
		try {
			return new CallFunction(functionName, actualArgs.toArray(new IExpression[actualArgs.size()]));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createAssignmentStatement(String variableName, IExpression<? extends Object> value, SourceLocation sourceLocation) {
		try {
			return new Assignment(variableName, value);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createWhileStatement(IExpression<? extends Object> condition, IStatement body, SourceLocation sourceLocation) {
		try {
			OGUtil.println("creating while loop with " + condition + " and " + body);
			return new WhileLoop(condition.castTo(Boolean.class), body);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createBreakStatement(SourceLocation sourceLocation) {
		try {
			return new Break();
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createReturnStatement(IExpression<? extends Object> value, SourceLocation sourceLocation) {
		try {
			return new Return(value);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createIfStatement(IExpression<? extends Object> condition, IStatement ifBody, IStatement elseBody, SourceLocation sourceLocation) {
		try {
			return new IfElse(condition.castTo(Boolean.class), ifBody, elseBody);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createPrintStatement(IExpression<? extends Object> value, SourceLocation sourceLocation) {
		try {
			return new Print(value);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createSequenceStatement(List<IStatement> statements, SourceLocation sourceLocation) {
		try {
			return new BlockStatement(statements.toArray(new IStatement[statements.size()]));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<? extends Object> createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		try {
			return new ReadVariable(variableName);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Double> createChangeSignExpression(IExpression<? extends Object> expression, SourceLocation sourceLocation) {
		try {
			return new Negative(expression.castTo(Double.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Boolean> createNotExpression(IExpression<? extends Object> expression, SourceLocation sourceLocation) {
		try {
			return new Invert(expression.castTo(Boolean.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Double> createDoubleLiteralExpression(double value, SourceLocation sourceLocation) {
		try {
			return new Constant<Double>(value);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<? extends Entity> createNullExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.NULL);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<? extends Entity> createSelfExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.SELF);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<? extends Entity> createShipExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.SHIP);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<? extends Entity> createAsteroidExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.ASTEROID);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<? extends Entity> createPlanetoidExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.PLANETOID);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<? extends Entity> createBulletExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.BULLET);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<? extends Entity> createPlanetExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.PLANET);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<? extends Entity> createAnyExpression(SourceLocation sourceLocation) {
		try {
			return new FindEntity(FindEntity.Filter.ANY);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Double> createGetXExpression(IExpression<? extends Object> e, SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.X, e.castTo(Entity.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Double> createGetYExpression(IExpression<? extends Object> e, SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.Y, e.castTo(Entity.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Double> createGetVXExpression(IExpression<? extends Object> e, SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.VX, e.castTo(Entity.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Double> createGetVYExpression(IExpression<? extends Object> e, SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.VY, e.castTo(Entity.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Double> createGetRadiusExpression(IExpression<? extends Object> e, SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.RADIUS, e.castTo(Entity.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Boolean> createLessThanExpression(IExpression<? extends Object> e1, IExpression<? extends Object> e2, SourceLocation sourceLocation) {
		try {
			return new LessThan(e1.castTo(Double.class), e2.castTo(Double.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Boolean> createEqualityExpression(IExpression<? extends Object> e1, IExpression<? extends Object> e2, SourceLocation sourceLocation) {
		try {
			return new Equality(e1, e2);
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Double> createAdditionExpression(IExpression<? extends Object> e1, IExpression<? extends Object> e2, SourceLocation sourceLocation) {
		try {
			return new Addition(e1.castTo(Double.class), e2.castTo(Double.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Double> createMultiplicationExpression(IExpression<? extends Object> e1, IExpression<? extends Object> e2, SourceLocation sourceLocation) {
		try {
			return new Multiplication(e1.castTo(Double.class), e2.castTo(Double.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Double> createSqrtExpression(IExpression<? extends Object> e, SourceLocation sourceLocation) {
		try {
			return new Sqrt(e.castTo(Double.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IExpression<Double> createGetDirectionExpression(SourceLocation sourceLocation) {
		try {
			return new GetAttribute(GetAttribute.Attribute.DIRECTION, new FindEntity(FindEntity.Filter.SELF));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createThrustOnStatement(SourceLocation sourceLocation) {
		try {
			return new SetThruster(new Constant<Boolean>(true));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createThrustOffStatement(SourceLocation sourceLocation) {
		try {
			return new SetThruster(new Constant<Boolean>(false));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createFireStatement(SourceLocation sourceLocation) {
		try {
			return new Fire();
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createTurnStatement(IExpression<? extends Object> angle, SourceLocation sourceLocation) {
		try {
			return new Turn(angle.castTo(Double.class));
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public IStatement createSkipStatement(SourceLocation sourceLocation) {
		try {
			return new Skip();
		} catch (ProgramException ex) {
			throw new RuntimeException(ex);
		}
	}

}
