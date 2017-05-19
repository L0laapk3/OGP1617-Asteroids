package asteroids.model.program.expression;

import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.program.Program;
import asteroids.model.program.statement.ExpressionContainer;

public class GetAttribute extends ExpressionContainer<Entity> implements IExpression<Double> {

	public enum Attribute {
		X,
		Y,
		VX,
		VY,
		RADIUS,
		DIRECTION,
		VELOCITY,
		WEIGHT
	}
	
	public final Attribute attribute;
	
	public GetAttribute(Attribute attribute, IExpression<? extends Entity> expression) throws ProgramException {
		super(expression);
		this.attribute = attribute;
	}
	
	@Override
	public Double evaluate(Program program) throws ProgramException {
		
		if (getResult(0) == null)
			throw new NullComputationException();
		
		switch(attribute) {
		case DIRECTION:	return ((Ship)getResult(0)).getOrientation();
		case RADIUS:	return getResult(0).getRadius();
		case VX:		return getResult(0).getVelocityVector().x;
		case VY:		return getResult(0).getVelocityVector().y;
		case X:			return getResult(0).getPosition().x;
		case Y:			return getResult(0).getPosition().y;
		case VELOCITY:  return getResult(0).getVelocity();
		case WEIGHT:	return getResult(0).getMass();
		default:		return null;
		}
	}
}
