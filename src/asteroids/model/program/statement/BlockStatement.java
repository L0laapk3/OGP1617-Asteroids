package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;
import asteroids.model.program.Program;

public class BlockStatement extends ContainerStatement<IStatement> {
	
	
	public BlockStatement(IStatement... statements) throws ProgramException {
		super(statements);
	}

	@Override
	public boolean selfStep(Program program) { return false; }
}
