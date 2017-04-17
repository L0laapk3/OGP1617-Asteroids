package asteroids.model;

import asteroids.util.OGUtil;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;



/**
 * A class for objects that are terminatable.
 * 
 * @version 1.0
 * @author  Kris Keersmaekers
 * @author  Rik Pauwels
 */
public abstract class Instance {

	/**
	 * Check whether this object exists and is not terminated.
	 */
	public static boolean isNullOrTerminated(Instance obj) {
		return (obj == null) || obj.isTerminated();
	}
	
	


	/**
	 * Variable reflecting whether or not the instance is terminated.
	 */
	private boolean isTerminated = false;
	

	/**
	 * Terminate this entity.
	 *
	 * @post   The instance is terminated.
	 */
	public void terminate() {
		OGUtil.println("TERMINATE " + this); // TODO: weg
		if (!isTerminated())
			this.isTerminated = true;
	}

	
	/**
	 * Check whether this entity is terminated.
	 */
	@Basic
	@Raw
	public boolean isTerminated() {
		return isTerminated;
	}
}
