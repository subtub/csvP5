//
// ArrayUtils.java
// CsvP5 (v.##library.prettyVersion##) is released under the MIT License.
//
// Copyright (c) 2012, Tim Pulver & Paul Vollmer http://www.fh-potsdam.de
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
//

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
