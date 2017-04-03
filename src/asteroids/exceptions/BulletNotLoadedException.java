package asteroids.exceptions;

@SuppressWarnings("serial")
public class BulletNotLoadedException extends IllegalArgumentException {

	  public BulletNotLoadedException() {
	    super("Bullet is not loaded in the ship");
	  }
	  
	  public BulletNotLoadedException(String message) {
	    super(message);
	  }

	  public BulletNotLoadedException(Throwable nested) {
	    super(nested);
	  }
}
