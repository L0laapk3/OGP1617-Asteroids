package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.Entity;
import asteroids.model.program.Program;
import asteroids.util.OGUtil;

public class GetAttribute extends Numeric {

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
	
	public final Expression expression;
	
	public <T extends Expression & IEntity> GetAttribute(Attribute attribute, T expression) throws ProgramException {
		super(expression);
		this.expression = expression;
		this.attribute = attribute;
	}
	
	@Override
	public Double evaluate(Program program) throws ProgramException {
		
		Entity entity = ((EntityExpression)expression).evaluate(program);
		OGUtil.println("getattribute " + entity + " " + attribute);
		
		switch(attribute) {
		case DIRECTION:	return entity.getVelocityVector().getRotation();
		case RADIUS:	return entity.getRadius();
		case VX:		return entity.getVelocityVector().x;
		case VY:		return entity.getVelocityVector().y;
		case X:			return entity.getPosition().x;
		case Y:			return entity.getPosition().y;
		case VELOCITY:  return entity.getVelocity();
		case WEIGHT:	return entity.getMass();
		default:		return null;
		}
	}
	
	@Override
	public boolean step(Program program) throws ProgramException {
		return expression.step(program);
	}
	
	@Override
	public double getRequiredTime() {
		return expression.getRequiredTime();
	}

}
