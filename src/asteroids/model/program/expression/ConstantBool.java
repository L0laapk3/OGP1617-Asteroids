package asteroids.model.program.expression;

public class ConstantBool extends Constant<Boolean> implements ICondition {

	public ConstantBool(Boolean value) {
		super(value);
	}
}
