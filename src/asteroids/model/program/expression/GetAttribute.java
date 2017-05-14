package asteroids.model.program.expression;

import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.Entity;
import asteroids.model.program.Program;
import asteroids.model.program.statement.ContainerStatement;

public class GetAttribute extends ContainerStatement<IExpression<? extends Entity>> implements IExpression<Double> {

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
		
		Entity entity = statements[0].evaluate(program);
		if (entity == null)
			throw new NullComputationException();
		
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
}
