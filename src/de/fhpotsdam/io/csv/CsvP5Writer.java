//
// CsvP5Writer.java
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

package de.fhpotsdam.io.csv;

import processing.core.PApplet;

public class CsvP5Writer {
	public final static boolean ROUND_FLOAT_VALUES_DEFAULT = true;
	public final static int FLOAT_PRECISION_DEFAULT = 3; 
	
	private String filename;
	private int floatPrecision = FLOAT_PRECISION_DEFAULT;
	private boolean roundFloatValues = ROUND_FLOAT_VALUES_DEFAULT;
	private CsvP5 csv;
	
	public CsvP5Writer(CsvP5 csvBase){
		this.csv = csv;
		init();
	}
	
	private void init(){
		
	}
	
	/*
	 * ======================================================================|
	 * PUBLIC SETTING FUNCTIONS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
	 * ======================================================================|
	 */
	
	/**
	 * Sets the headline of a certain column.
	 * @param row row number
	 * @param name new name for the column
	 */
	/*
	public void setRowName(int column, String name) {
		data[0][column] = name;
	}
	*/
	
	/**
	 * Sets a specific String to a new value.
	 * @param rowIndex row number
	 * @param columnIndex column number
	 * @param value new value
	 */
	/*
	public void setString(int rowIndex, int columnIndex, String value) {
		data[rowIndex][columnIndex] = value;
	}
	*/
	
	/**

	 * Sets a specific String to a new value.
	 * @param columnName name of the row
	 * @param row column number
	 * @param value new value
	 */
	/*
	public void setString(String columnName, int row, String value) {
		int columnIndex = getColumnIndex(columnName);
		data[row][columnIndex] = value;
	}
	*/
	
	/**
	 * Sets a specific integer to a new value.
	 * @param rowIndex row number
	 * @param columnIndex column number
	 * @param value new integer value
	 */
	/*
	public void setInt(int rowIndex, int columnIndex, int value) {
		data[rowIndex][columnIndex] = PApplet.str(value);
	}
	*/
	
	/**
	 * setInt set a specific integer to a new value.
	 * @param columnName Name of the row
	 * @param rowIndex Column number
	 * @param value New row String
	 * Sets a specific integer to a new value.
	 * @param columnName name of the row
	 * @param rowIndex column number
	 * @param value new integer value
	 */
	/*
	public void setInt(String columnName, int rowIndex, int value) {
		int columnIndex = getColumnIndex(columnName);
		data[rowIndex][columnIndex] = PApplet.str(value);
	}
	*/
	
	/**
	 * Sets a specific float to a new value.
	 * @param rowIndex row number
	 * @param columnIndex column number
	 * @param value new float value
	 */
	/*
	public void setFloat(int rowIndex, int columnIndex, float value) {
		data[rowIndex][columnIndex] = PApplet.str(value);
	}
	
	/**
	 * Sets a specific float to a new value.
	 * @param columnName name of the column
	 * @param rowIndex row number
	 * @param value new float vaule
	 */
	/*
	public void setFloat(String columnName, int rowIndex, float value) {
		int columnIndex = getColumnIndex(columnName);
		data[rowIndex][columnIndex] = PApplet.str(value);
	}
	*/
}
