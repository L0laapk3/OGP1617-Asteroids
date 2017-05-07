package asteroids.model.program.expression;

import asteroids.model.program.Program;

public class Entity extends Expression {

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
	
	public Entity(Filter filter) {
		this.filter = filter;
	}
	
	@Override
	public Object evaluate(Program program) {
		
		//TODO: pagina 18
		switch(filter) {
		case ANY:
			break;
		case ASTEROID:
			break;
		case BULLET:
			break;
		case NULL:
			break;
		case PLANET:
			break;
		case PLANETOID:
			break;
		case SELF:
			break;
		case SHIP:
			break;
		}
		
		return null;
	}

}
