package asteroids.model.program.statement;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.NullComputationException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.*;

public class Turn extends ExpressionContainer<Double> implements IAction {
	
	public Turn(IExpression<? extends Double> angleExpression) throws ProgramException {
		super(angleExpression);
	}
	
	
	@Override
	public boolean selfStep(Program program) throws ProgramException {
		if (getResult(0) == null)
			throw new NullComputationException();
		try {
			program.getShip().turn(getResult(0));
		} catch (InvalidShipException e) {
			throw new ProgramException(e);
		}
		return false;
	}

}
