package asteroids.model.program.expression;

import asteroids.model.program.Program;

public interface ICondition {

	public abstract Boolean evaluate(Program program);

}