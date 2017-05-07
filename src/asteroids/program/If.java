package asteroids.program;

public class If extends IfElse {
	public <T extends Expression & ICondition> If(T condition, BlockStatement onTrue) {
		super(condition, onTrue, new BlockStatement());
	}
}