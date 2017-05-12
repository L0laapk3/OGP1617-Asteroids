package asteroids.model.program.statement;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.IExpression;

public class SetThruster extends SingleContainerStatement<IExpression<? extends Boolean>> implements IAction {

	public SetThruster(IExpression<? extends Boolean> state) throws ProgramException {
		super(state);
	}
	
	
	@Override
	public boolean selfStep(Program program) throws ProgramException {
		try {
			program.getShip().setThruster(this.statement.evaluate(program));
		} catch (InvalidShipException e) {
			throw new ProgramException(e);
		}
		return false;
	}
	
	@Override
	public double selfGetRequiredTime() {
		return 0;
	}

}
