package asteroids.model.program.statement;

import asteroids.exceptions.InvalidShipException;
import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;
import asteroids.model.program.expression.*;

public class Turn extends ContainerStatement<IExpression<? extends Double>> implements IAction {
	
	public Turn(IExpression<? extends Double> angleExpression) throws ProgramException {
		super(angleExpression);
	}
	
	
	@Override
	public boolean selfStep(Program program) throws ProgramException {
		try {
			program.getShip().turn(statements[0].evaluate(program));
		} catch (InvalidShipException e) {
			throw new ProgramException(e);
		}
		return false;
	}

}
