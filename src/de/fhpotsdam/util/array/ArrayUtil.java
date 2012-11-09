package de.fhpotsdam.util.array;

/**
 * Some useful Array functions
 * 
 * @author Tim Pulver
 *
 */

public class ArrayUtil {

	public static ArrayUtil instance;

	private ArrayUtil() {
	}

	public static ArrayUtil getInstance() {
		if (instance == null) {
			instance = new ArrayUtil();
		}
		return instance;
	}

	/**
	 * Returns the smallest integer in the array.
	 * 
	 * @param iArr
	 * @return
	 */
	public static int getMax(int[] iArr) {
		int max = iArr[0];
		for (int i = 1; i < iArr.length; i++) {
			if (iArr[i] > max) {
				max = iArr[i];
			}
		}
		return max;
	}

	/**
	 * Returns the biggest integer in the array.
	 * 
	 * @param iArr
	 * @return
	 */
	public static int getMin(int[] iArr) {
		int min = iArr[0];
		for (int i = 1; i < iArr.length; i++) {
			if (iArr[i] < min) {
				min = iArr[i];
			}
		}
		return min;
	}
	
	/**
	 * Returns the smallest integer in the array.
	 * 
	 * @param fArr
	 * @return
	 */
	public static float getMax(float[] fArr) {
		float max = fArr[0];
		for (int i = 1; i < fArr.length; i++) {
			if (fArr[i] > max) {
				max = fArr[i];
			}
		}
		return max;
	}

	/**
	 * Returns the biggest integer in the array.
	 * 
	 * @param fArr
	 * @return
	 */
	public static float getMin(float[] fArr) {
		float min = fArr[0];
		for (int i = 1; i < fArr.length; i++) {
			if (fArr[i] < min) {
				min = fArr[i];
			}
		}
		return min;
	}

}
