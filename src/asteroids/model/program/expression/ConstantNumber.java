package asteroids.model.program.expression;

public class ConstantNumber<T extends Number> extends Constant<T> implements INumber {

	public ConstantNumber(T value) {
		super(value);
	}
}
