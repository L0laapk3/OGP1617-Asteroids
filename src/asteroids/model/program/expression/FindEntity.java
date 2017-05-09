package asteroids.model.program.expression;

import asteroids.exceptions.ProgramException;
import asteroids.model.Entity;
import asteroids.model.program.Program;

public class FindEntity extends EntityExpression {

	public enum Filter {
		NULL,
		SELF,
		SHIP,
		ASTEROID,
		PLANETOID,
		BULLET,
		PLANET,
		ANY
	}
	
	public final Filter filter;

	public FindEntity(Filter filter) {
		this.filter = filter;
	}
	
	
	@Override
	public Entity evaluate(Program program) throws ProgramException {

		//TODO: DEES
		switch(filter) {
		case ANY:		return null;
		case ASTEROID:	return null;
		case BULLET:	return null;
		case PLANET:	return null;
		case PLANETOID:	return null;
		case SELF:		return null;
		case SHIP:		return null;
		default:		return null; //case NULL:
		}
	}

}
