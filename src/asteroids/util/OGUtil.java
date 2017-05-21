package asteroids.util;

import be.kuleuven.cs.som.annotate.*;

@Value
public class OGUtil {

	
	
	public static final boolean VERBOSE = false;
	
	
	
	/*
	 * Check whether the position is valid for a ship.
	 * 
	 * @param position
	 * 		  A Vector2 object containing the position to check.
	 * @return True if and only if both the x and y coordinate of the given position are valid numbers: They cannot be NaN or infinity.
	 *       | result == (!isInvalidNumber(position.x) && !isInvalidNumber(position.y))
	 */
	public static boolean isValidPosition(Vector2 position) {
		return !isInvalidNumber(position.x) && !isInvalidNumber(position.y);
	}
	
	
	
	



	/**
	 * Check whether the time to move the entity forward with is valid.
	 * 
	 * @param dt
	 * 		  The time to check.
	 * @return True if and only if dt equals to or is bigger than 0.
	 *       | result == (dt >= 0)
	 */
	@Raw
	public static boolean isValidDeltaTime(double dt) {
		return dt >= 0;
	}
	
	
	
	


	/**
	 * Checks if the number is valid or not.
	 * @param  number
	 * 		   The number that has to be validated.
	 * @return True if the number is infinite or Nan, otherwise false.
	 */
	@Immutable
	public static boolean isInvalidNumber(double number) {
		return Double.isInfinite(number) || Double.isNaN(number);
	}
	
	

	
	
	/**
	 * Checks if all given numbers are valid numbers.
	 * @param  numbers
	 * 		   The numbers that have to be validated.
	 * @throws IllegalArgumentException
	 *         If any of the numbers are infinite.
	 * @throws IllegalArgumentException
	 *         If any of the numbers are NaN.
	 */
	public static void throwErrorIfInvalidNumbers(double... numbers) throws IllegalArgumentException {
		for (double number : numbers) {
			if (Double.isInfinite(number))
				throw new IllegalArgumentException("Number cannot be infinite.");
			if (Double.isNaN(number))
				throw new IllegalArgumentException("Number cannot be NaN.");
		}
	}


	/**
	 * Prints only in debug mode
	 */
	public static void print(Object... args) { for (Object arg : args) print(arg + " "); }
	public static void print(Object arg) {
        if (VERBOSE)
			System.out.print(arg);
	}
	
	/**
	 * Prints line only in debug mode
	 */
	public static void println(Object... args) { for (Object arg : args) print(arg + " "); println(""); }
	public static void println(Object arg) {
        if (VERBOSE)
			System.out.println(arg);
	}
	

}
