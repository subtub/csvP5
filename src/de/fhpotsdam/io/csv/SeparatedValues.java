package de.fhpotsdam.io.csv;

import processing.core.*;

/**
 * SeparatedValues
 * 
 * based on Ben Fry's Visualizing Data Book
 */
public class SeparatedValues {
	
	PApplet p5;
	
	// Class Variables
	public int rowCount;
	public String[][] data;
	// console boolean to print out
	public boolean console = false;
	
	
	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the library.
	 */ 
	public SeparatedValues(PApplet p) {
		p5 = p;
	}
	
	public SeparatedValues(PApplet p, String fileName) {
		p5 = p;
		loadFile(fileName);
	}
	
	
	
	
	/**
	 * loadFile
	 * load a csv File, skip empty and comments lines.
	 *
	 * @param filename Set the filename
	 * @param separator Set the separator to split csv file
	 * @param comments Set the comments sign
	 */
	public void loadFile(String filename, String separator, String comments) {
		String[] rows = p5.loadStrings(filename);
		if(console == true) {
			System.out.println("### Load File: " + filename);
			System.out.println(rows);
		}
	    
		data = new String[rows.length][];
	    
		for(int i=0; i<rows.length; i++) {
			// skip empty rows
			if(PApplet.trim(rows[i]).length() == 0) {
	        continue;
	      }
	      // skip comment lines
	      if(rows[i].startsWith(comments)) {
	        continue;
	      }
	      
	      String[] pieces = PApplet.split(rows[i], separator);
	      
	      data[rowCount] = pieces;
	      rowCount++;
	    }
	    // resize the 'data' array as necessary
	    data = (String[][]) PApplet.subset(data, 0, rowCount);
	  }
	  
	  
	/**
	 * loadFile
	 * load a csv File, skip empty and comments lines.
	 *
	 * @param filename Set the filename
	 * @param separator Set the separator to split csv file
	 */
	public void loadFile(String filename, String separator) {
		loadFile(filename, separator, "#");
	}
	
	
	/**
	 * loadFile
	 * load a csv File, skip empty and comments lines.
	 *
	 * @param filename
	 *        set the filename
	 */
	public void loadFile(String filename) {
		loadFile(filename, ",", "#");
	}
	
	
	/**
	 * getRowCount
	 * Return the number of rows.
	 *
	 * @return rowCount
	 */
	public int getRowCount() {
		return rowCount;
	}
	
	
	/**
	 * getRowIndex
	 * Find a row by its name, returns -1 if no row found.
	 *
	 * @param name Name of the row
	 * @return integer
	 */
	public int getRowIndex(String name) {
		for (int i = 0; i < rowCount; i++) {
			if (data[i][0].equals(name)) {
				return i;
			}
		}
		System.err.println("No row named '" + name + "' was found");
		return -1;
	}
	
	
	/**
	 * getRowName
	 * get the String of a specific row and column.
	 *
	 * @param row Row number
	 * @return String
	 */
	public String getRowName(int row) {
		return getString(row, 0);
	}
	
	
	/**
	 * getString
	 * get the String of a specific row and column.
	 *
	 * @param rowIndex Row number
	 * @param column Column number
	 * @return String
	 */
	public String getString(int rowIndex, int column) {
		return data[rowIndex][column];
	}
	
	
	/**
	 * getString
	 * get the String of a specific row and column.
	 *
	 * @param rowName
	 *        name of the row
	 * @param column
	 *        column number
	 * @return String
	 */
	public String getString(String rowName, int column) {
		return getString(getRowIndex(rowName), column);
	}
	
	
	/**
	 * getInt
	 * get the integer of a specific row and column.
	 *
	 * @param rowName
	 *        name of the row
	 * @param column
	 *        column number
	 * @return integer
	 */
	public int getInt(String rowName, int column) {
		return Integer.parseInt(getString(rowName, column));
	}
	
	
	/**
	 * getInt
	 * get the integer of a specific row and column.
	 *
	 * @param rowIndex
	 *        row number
	 * @param column
	 *        column number
	 * @return integer
	 */
	public int getInt(int rowIndex, int column) {
		return Integer.parseInt(getString(rowIndex, column));
	}
	
	
	/**
	 * getFloat
	 * get the float of a specific row and column.
	 *
	 * @param rowName
	 *        name of the row
	 * @param column
	 *        column number
	 * @return float
	 */
	public float getFloat(String rowName, int column) {
		return Float.parseFloat(getString(rowName, column));
	}
	
	
	/**
	 * getFloat
	 * get the float of a specific row and column.
	 *
	 * @param rowIndex
	 *        row number
	 * @param column
	 *        column number
	 * @return string
	 */
	public float getFloat(int rowIndex, int column) {
		return Float.parseFloat(getString(rowIndex, column));
	}
	
	
	/**
	 * setRowName
	 * set a specific row name to a new value.
	 *
	 * @param row Row number
	 * @param what New row String
	 */
	public void setRowName(int row, String what) {
		data[row][0] = what;
	}
	
	
	/**
	 * setString
	 * set a specific String to a new value.
	 *
	 * @param rowIndex Row number
	 * @param column Column number
	 * @param what New row String
	 */
	public void setString(int rowIndex, int column, String what) {
		data[rowIndex][column] = what;
	}
	
	
	/**
	 * setString
	 * set a specific String to a new value.
	 *
	 * @param rowName Name of the row
	 * @param column Column number
	 * @param what New row String
	 */
	public void setString(String rowName, int column, String what) {
		int rowIndex = getRowIndex(rowName);
		data[rowIndex][column] = what;
	}
	
	
	/**
	 * setInt
	 * set a specific integer to a new value.
	 *
	 * @param rowIndex Row number
	 * @param column Column number
	 * @param what New row integer
	 */
	public void setInt(int rowIndex, int column, int what) {
		data[rowIndex][column] = PApplet.str(what);
	}
	
	
	/**
	 * setInt
	 * set a specific integer to a new value.
	 *
	 * @param rowName Name of the row
	 * @param column Column number
	 * @param what New row String
	 */
	public void setInt(String rowName, int column, int what) {
		int rowIndex = getRowIndex(rowName);
		data[rowIndex][column] = PApplet.str(what);
	}
	
	
	/**
	 * setFloat
	 * set a specific float to a new value.
	 *
	 * @param rowIndex Row number
	 * @param column Column number
	 * @param what New row float
	 */
	public void setFloat(int rowIndex, int column, float what) {
		data[rowIndex][column] = PApplet.str(what);
	}
	
	
	/**
	 * setFloat
	 * set a specific float to a new value.
	 *
	 * @param rowName Name of the row
	 * @param column Column number
	 * @param what New row float
	 */
	public void setFloat(String rowName, int column, float what) {
		int rowIndex = getRowIndex(rowName);
		data[rowIndex][column] = PApplet.str(what);
	}
	
	
}
