package asteroids.util;

import be.kuleuven.cs.som.annotate.*;



	/**
	 * A class to define a twodimensional vector.
	 * 
	 * @version  1.0
	 * @author   Kris Keersmaekers
	 * @author   Rik Pauwels
	 */


@Value
public class Vector2 {

	/**
	 * Variable registering the x of this vector.
	 */
	public final double x;
	/**
	 * Variable registering the y of this vector.
	 */
	public final double y;
	
	
	/**
	 * Creates a new Vector2 object.
	 * 
	 * @param  x
	 * 		   the x parameter of this vector2.
	 * @param  y
	 * 		   the y parameter of this vector2.
	 * @effect Creates a new vector2 with given x and y.
	 */
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}


	
	/**
	 * Returns true if and only if both vectors have the same position
	 */
	@Immutable
	public static boolean equals(Vector2 firstVector, Vector2 secondVector) {
		return firstVector.x == secondVector.x && firstVector.y == secondVector.y;
		//TODO afrondingsshit
	}
	
	
	/**
	 * Creates a new Vector2, that is the addition of firstVector to secondVector.
	 * 
	 * @param  firstVector
	 * 		   The first vector.
	 * @param  secondVector
	 * 		   The second vector.
	 * @return new vector2 that is the result of the addition.
	 */
	@Immutable
	public static Vector2 add(Vector2 firstVector, Vector2 secondVector) {
		return new Vector2(firstVector.x + secondVector.x, firstVector.y + secondVector.y);
	}

	/**
	 * Creates a new Vector2, that is the subtraction of secondVector from firstVector.
	 * 
	 * @param  firstVector
	 * 		   The first vector.
	 * @param  secondVector
	 * 		   The second vector.
	 * @return new vector2 that is the result of the subtraction.
	 */
	@Immutable
	public static Vector2 subtract(Vector2 firstVector, Vector2 secondVector) {
		return new Vector2(firstVector.x - secondVector.x, firstVector.y - secondVector.y);
	}
	

	/**
	 * Creates a new Vector2, that is the multiplication of vector with value
	 * 
	 * @param  vector
	 * 		   The vector to multiply.
	 * @param  value
	 * 		   The value to multiply the vector with.
	 * @return new vector2 that is the result of the multiplication.
	 */
	@Immutable
	public static Vector2 multiply(Vector2 vector, double value) {
		return new Vector2(vector.x * value, vector.y * value);
	}
	
	

	/**
	 * Calculates pythagoras
	 * 
	 * @return Calculates the length of a vector
	 */
	public double pythagoras() {
		return Math.sqrt(this.x*this.x  + this.y*this.y);
	}

	
	
	/**
	 * Calculates the distance between 2 vectors
	 * 
	 * @param  firstVector
	 * 		   The first vector
	 * @param  secondVector
	 * 		   The second vector
	 * @return Calculates the distance between the first vector and the second vector.
	 */
	public static double distance(Vector2 firstVector, Vector2 secondVector) {
		return subtract(firstVector, secondVector).pythagoras();
	}
	
	
	/**
	 * Calculates the dot product of 2 vectors
	 * 
	 * @param  firstVector
	 * 		   The first vector
	 * @param  secondVector
	 * 		   The second vector
	 * @return Calculates the dot product of firstVector and secondVector.
	 */
	public static double dot(Vector2 firstVector, Vector2 secondVector) {
		return firstVector.x * secondVector.x + firstVector.y * secondVector.y;
	}
	
	
	/**
	 * Converts vector2 to double[2]
	 * 
	 * @return array of 2 doubles with values {x, y}
	 */
	public double[] toArray() {
		return new double[] {this.x, this.y};
	}
}
