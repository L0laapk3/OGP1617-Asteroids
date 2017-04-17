package asteroids.util;

import be.kuleuven.cs.som.annotate.*;

@Value
public class OGUtil {

	
	
	private static final boolean VERBOSE = true;
	
	
	
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
	 * Prints line only in debug mode
	 */
	public static void println(String str) {
        if (VERBOSE)
			System.out.println(str);
	}
	
	/**
	 * Prints line only in debug mode
	 * @effect println(arg.toString());
	 */
	public static void println(Object arg) {
		println(arg.toString());
	}
}
