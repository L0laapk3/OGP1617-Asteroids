package asteroids.model.program.statement;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.IExpression;

public class SetThruster extends ExpressionContainer<Boolean> implements IAction {

	public SetThruster(IExpression<? extends Boolean> state) throws ProgramException {
		super(state);
	}
	
	
	@Override
	public boolean selfStep(Program program) throws ProgramException {
		if (getResult(0) == null)
			throw new NullComputationException();
		try {
			program.getShip().setThruster(getResult(0));
		} catch (InvalidShipException e) {
			throw new ProgramException(e);
		}
		return false;
	}

}
