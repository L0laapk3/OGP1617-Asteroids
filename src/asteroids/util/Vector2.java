package asteroids.util;

import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

/**
 * A class to define a twodimensional vector.
 * 
 * @version 1.0
 * @author Kris Keersmaekers
 * @author Rik Pauwels
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
	 * @param x
	 *        the x parameter of this vector2.
	 * @param y
	 *        the y parameter of this vector2.
	 * @effect Creates a new vector2 with given x and y.
	 */
	public Vector2(Vector2 vec) { this(vec.x, vec.y); }
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
	}

	/**
	 * Creates a new Vector2, that is the addition of firstVector to secondVector.
	 * 
	 * @param firstVector
	 *        The first vector.
	 * @param secondVector
	 *        The second vector.
	 * @return new vector2 that is the result of the addition.
	 */
	@Immutable
	public static Vector2 add(Vector2 firstVector, Vector2 secondVector) {
		return new Vector2(firstVector.x + secondVector.x, firstVector.y + secondVector.y);
	}

	/**
	 * Creates a new Vector2, that is the subtraction of secondVector from firstVector.
	 * 
	 * @param firstVector
	 *        The first vector.
	 * @param secondVector
	 *        The second vector.
	 * @return new vector2 that is the result of the subtraction.
	 */
	@Immutable
	public static Vector2 subtract(Vector2 firstVector, Vector2 secondVector) {
		return new Vector2(firstVector.x - secondVector.x, firstVector.y - secondVector.y);
	}

	/**
	 * Creates a new Vector2, that is the division of vector with value
	 * 
	 * @param vector
	 *        The vector to divide.
	 * @param value
	 *        The value to divide the vector with.
	 * @return new vector2 that is the result of the multiplication.
	 */
	@Immutable
	public static Vector2 divide(Vector2 vector, double value) {
		return new Vector2(vector.x / value, vector.y / value);
	}

	/**
	 * Creates a new Vector2, that is the multiplication of vector with value
	 * 
	 * @param vector
	 *        The vector to multiply.
	 * @param value
	 *        The value to multiply the vector with.
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
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

	/**
	 * Calculates the distance between 2 vectors
	 * 
	 * @param firstVector
	 *        The first vector
	 * @param secondVector
	 *        The second vector
	 * @return Calculates the distance between the first vector and the second vector.
	 */
	public static double distance(Vector2 firstVector, Vector2 secondVector) {
		return subtract(firstVector, secondVector).pythagoras();
	}

	/**
	 * Calculates the dot product of 2 vectors
	 * 
	 * @param firstVector
	 *        The first vector
	 * @param secondVector
	 *        The second vector
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
	public double[] toProfNotation() {
		return new double[] { this.x, this.y };
	}

	/**
	 * Prints contents of vector2.
	 */
	@Override
	public String toString() {
		return "Vector2 [x=" + x + ", y=" + y + "]";
	}

	/**
	 * returns a new vector2 containing the absolute values of the vector2.
	 */
	public Vector2 abs() {
		if (this.x >= 0 && this.y >= 0)
			return this;
		return new Vector2(Math.abs(this.x), Math.abs(this.y));
	}

	/**
	 * Converts the given vector to a vector with the same orientation and length 1
	 * 
	 * @return a vector with the same orientation as the given vector and length 1
	 */
	public Vector2 toUnitVector() {
		double factor = Math.sqrt(this.pythagoras());
		return new Vector2(this.x / factor, this.y / factor);
	}
	
	/**
	 * Clones Vector2.
	 * 
	 * @return New vector2 identical to this one.
	 */
	@Raw
	@Override
	public Vector2 clone() {
		return new Vector2(this);
	}
	
	/**
	 * generates Vector2 from polar coordinates.
	 * 
	 * @param rot
	 *        The angle.
	 * @param len
	 *        The the distance from (0, 0).
	 */
	public static Vector2 fromPolar(double rot, double len) {
		return new Vector2(Math.cos(rot) * len, Math.sin(rot) * len);
	}
	
	/**
	 * Returns the rotation value in polar coordinate system of this Vector2 object.
	 * @return the rotation value in polar coordinate system of this Vector2 object.
	 */
	public Double getRotation() {
		return this.y > 0 ? Math.acos(this.x / this.pythagoras()) : -Math.acos(this.x / this.pythagoras());
	}
}
