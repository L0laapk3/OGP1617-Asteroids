package asteroids.model.program.statement;

import asteroids.exceptions.ProgramException;

public abstract class Statement implements IStatement {
	
	protected Statement() throws ProgramException { }
	
	public Statement clone() {
		
		try {
			Statement clone = (Statement)super.clone();
			//System.out.println("I just got cl0ned: " + this + " " + clone);
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(e);
		}
	}
	
	
	

	
	
	
	
	//debug:
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode());
	}
}
