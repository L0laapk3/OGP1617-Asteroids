package asteroids.program;

public class Print extends Statement {

	public final String string;
	
	public Print(String string) {
		this.string = string;
	}
	
	@Override
	protected boolean step(Program program) {
		System.out.println(string);
		return false;
	}

}
