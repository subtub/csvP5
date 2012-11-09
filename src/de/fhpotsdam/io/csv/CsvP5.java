package de.fhpotsdam.io.csv;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.*;

/**
 * CsvP5
 * 
 * based on Ben Fry's Visualizing Data Book
 * @author Tim Pulver, Paul Vollmer
 * 
 */
public class CsvP5 {

	// default separator and comment chars
	public static final String DEFAULT_SEPARATOR = ",";
	public static final String DEFAULT_COMMENT = "#";
	public static final String QUOTATION_MARK = "\"";
	public static final Class STRING_CLASS = new String().getClass();

	// needed for csv-file processing
	PApplet p5;
	private String filename;
	private String separator;
	private String comment;
	private boolean hasEnclosingQuotationMarks;
	private boolean hasHeadline = true;

	// flags - will be set while processing
	private boolean isValid = false;
	private boolean isComplete = false;
	private int firstIncompleteLine = -1;

	// Class Variables
	public int columnCount, rowCount;
	public String[][] data;
	// console boolean to print out
	public boolean console = false;
	// user can set the formats for each row (auto-parsing)
	//HashMap<Integer, ElementFormat> formats;
	ArrayList[] data2;

	// Constructors ----------------------------------------------------------

	private CsvP5() {
	} // don't use this

	public CsvP5(PApplet p, String filename) {
		init(p, filename, DEFAULT_SEPARATOR, DEFAULT_COMMENT, false);
	}

	public CsvP5(PApplet p, String filename, String separator) {
		init(p, filename, separator, DEFAULT_COMMENT, false);
	}

	public CsvP5(PApplet p, String filename, String separator,
			boolean hasEnclosingQuotationMarks) {
		init(p, filename, separator, DEFAULT_COMMENT,
				hasEnclosingQuotationMarks);
	}

	public CsvP5(PApplet p, String filename, String separator, String comment,
			boolean hasEnclosingQuotationMarks) {
		init(p, filename, separator, comment, hasEnclosingQuotationMarks);
	}

	/*
	 * Get's called by every constructor to init the variables
	 */
	public void init(PApplet p, String filename, String separator,
			String comment, boolean hasEnclosingQuotationMarks) {
		this.p5 = p;
		this.filename = filename;
		this.separator = separator;
		this.comment = comment;
		this.hasEnclosingQuotationMarks = hasEnclosingQuotationMarks;
		//formats = new HashMap<Integer, ElementFormat>();
	}

	/*
	 * Starts the actual csv-processing
	 */
	public void load() {
		resetFlags();
		loadFile(filename, separator, comment, hasEnclosingQuotationMarks);
	}

	private void resetFlags() {
		isComplete = false;
		firstIncompleteLine = -1;
	}

	/**
	 * loadFile load a csv File, skip empty and comments lines.
	 * 
	 * @param filename
	 *            Set the filename
	 * @param separator
	 *            Set the separator to split csv file
	 * @param comment
	 *            Set the comments sign
	 */
	public void loadFile(String filename, String separator, String comment,
			boolean hasEnclosingQuotationMarks) {
		String[] rows = p5.loadStrings(filename);
		if (console == true) {
			System.out.println("### Load File: " + filename);
			System.out.println(rows);
		}

		data = new String[rows.length][];
		int nFirstLineElements = getNumberOfElements(rows);

		for (int i = 0; i < rows.length; i++) {
			isComplete = true;
			// skip empty rows
			if (PApplet.trim(rows[i]).length() == 0) {
				continue;
			}
			// skip comment lines
			if (rows[i].startsWith(comment)) {
				continue;
			}

			String[] pieces = PApplet.split(rows[i], separator);
			if (hasEnclosingQuotationMarks) {
				removeEnclosingQuotationMarks(pieces);
			}
			// Get rid of unnecessary leading and ending spaces
			data[rowCount] = PApplet.trim(pieces);
			// check if the number of elements is the same as in the first line
			if (data[rowCount].length != nFirstLineElements) {
				// set flag if not
				isComplete = false;
				if (firstIncompleteLine != -1) {
					firstIncompleteLine = rowCount;
				}
			}
			rowCount++;
		}
		// resize the 'data' array as necessary
		data = (String[][]) PApplet.subset(data, 0, rowCount);
		// Store the number of columns (data entries per line)
		if (data.length >= 1) {
			columnCount = data[0].length;
		}
	}

	/**
	 * Returns the number of elements in the first non-comment line
	 * 
	 * @param rows
	 * @return
	 */
	public int getNumberOfElements(String[] rows) {
		// find first data line, skip empty lines
		for (int i = 0; i < rows.length; i++) {
			// skip empty rows
			if (PApplet.trim(rows[i]).length() == 0) {
				continue;
			}
			// skip comment lines
			if (rows[i].startsWith(comment)) {
				continue;
			}

			String[] pieces = PApplet.split(rows[i], separator);
			if (hasEnclosingQuotationMarks) {
				removeEnclosingQuotationMarks(pieces);
			}
			// Get rid of unnecessary leading and ending spaces
			pieces = PApplet.trim(pieces);
			return pieces.length;
		}
		return 0;
	}

	public void removeEnclosingQuotationMarks(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].startsWith(QUOTATION_MARK)
					&& arr[i].endsWith(QUOTATION_MARK) && arr[i].length() > 1) {
				// remove starting and ending quotation marks
				arr[i] = arr[i].substring(1, arr[i].length() - 1);
			}
		}
	}

	/**
	 * loadFile load a csv File, skip empty and comment lines (beginning with
	 * "#").
	 * 
	 * @param filename
	 *            Set the filename
	 * @param separator
	 *            Set the separator to split csv file
	 */
	public void loadFile(String filename, String separator) {
		loadFile(filename, separator, DEFAULT_COMMENT, false);
	}

	/**
	 * loadFile load a csv File, skip empty and comments lines (beginning with
	 * "#").
	 * 
	 * @param filename
	 *            set the filename
	 */
	public void loadFile(String filename) {
		loadFile(filename, DEFAULT_SEPARATOR, DEFAULT_COMMENT, false);
	}

	/**
	 * getRowCount Return the number of rows.
	 * 
	 * @return rowCount
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * getColumnCount Return the number of columns.
	 * 
	 * @return columnCount
	 */
	public int getColumnCount() {
		return columnCount;
	}

	/**
	 * getColumnIndex Find a column by its name, returns -1 if no column was
	 * found. You can use this to search for a csv-heading.
	 * 
	 * @param name
	 *            Name of the column
	 * @return integer
	 */
	public int getColumnIndex(String name) {
		for (int i = 0; i < columnCount; i++) {
			if (data[0][i].equals(name)) {
				return i;
			}
		}
		System.err.println("No column named '" + name + "' was found");
		return -1;
	}

	/**
	 * getColumnName Returns the name of a specific column.
	 * 
	 * @param column
	 *            Column number
	 * @return String
	 */
	public String getColumnName(int column) {
		return getString(0, column);
	}

	/**
	 * getString get the String of a specific row and column.
	 * 
	 * @param rowIndex
	 *            Row number
	 * @param columnIndex
	 *            Column number
	 * @return String
	 */
	public String getString(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	/**
	 * getString get the String of a specific row and column.
	 * 
	 * @param columnName
	 *            name of the column
	 * @param rowIndex
	 *            row number
	 * @return String
	 */
	public String getString(String columnName, int rowIndex) {
		return getString(rowIndex, getColumnIndex(columnName));
	}

	/**
	 * getInt get the integer of a specific row and column.
	 * 
	 * @param columnName name of the column
	 * @param rowIndex row number
	 * @return integer
	 */
	public int getInt(String columnName, int rowIndex) {
		return getInt(rowIndex, getColumnIndex(columnName));
	}

	/**
	 * getInt get the integer of a specific row and column.
	 * 
	 * @param rowIndex row number
	 * @param columnIndex column number
	 * @return integer
	 */
	public int getInt(int rowIndex, int columnIndex) {
		String sElement = getString(rowIndex, columnIndex);
		int ret = -1;
		// return -1 when string is empty
		if(sElement == ""){
			return ret;
		}
		try {
			ret = Integer.parseInt(sElement);
		} catch (NumberFormatException e) {
			System.err.println("Could not parse "
							+ sElement
							+ " to int! "
							+ "Did you try to cast the headline or called setFormat() with a wrong format?");
		}
		return ret;
	}

	/**
	 * getFloat get the float of a specific row and column.
	 * 
	 * @param columnName name of the column
	 * @param rowIndex row number
	 * @return
	 */
	public float getFloat(String columnName, int rowIndex) {
		return getFloat(rowIndex, getColumnIndex(columnName));
	}

	/**
	 * getFloat get the float of a specific row and column.
	 * 
	 * @param rowIndex row number
	 * @param column column number
	 * @return 
	 */
	public float getFloat(int rowIndex, int columnIndex) {
		String sElement = getString(rowIndex, columnIndex);
		float ret = -1;
		try {
			ret = Float.parseFloat(sElement);
		} catch (NumberFormatException e) {
			System.err.println("Could not parse "
							+ sElement
							+ " to float! "
							+ "Did you try to cast the headline or called setFormat() with a wrong format?");
		}
		return ret;
	}
	
	/**
	 * TODO store min i in global var!?
	 * @param columnIndex
	 * @return
	 */
	public int minInt(int columnIndex){
		int iStart = hasHeadline ? 1 : 0;		
		int min = Integer.MAX_VALUE;
		int minI = -1;	// index of smallest value
		int tmp = Integer.MAX_VALUE;
		for(int i=iStart; i<rowCount; i++){
			try{
				tmp = Integer.parseInt(data[i][columnIndex]);
			}catch(NumberFormatException e){
				e.printStackTrace();
				System.err.println("minInt(): Parse error...");
			}			
			if(tmp < min){
				min = tmp;
				minI = i;
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}
	
	/**
	 * TODO store max i in global var!?
	 * @param columnIndex
	 * @return
	 */
	public int maxInt(int columnIndex){
		int iStart = hasHeadline ? 1 : 0;		
		int max = -1;
		int maxI = -1;	// index of biggest value
		int tmp = -1;
		for(int i=iStart; i<rowCount; i++){
			try{
				tmp = Integer.parseInt(data[i][columnIndex]);
			}catch(NumberFormatException e){
				e.printStackTrace();
				System.err.println("maxInt(): Parse error...");
			}			
			if(tmp > max){
				max = tmp;
				maxI = i;
			}
		}
		return max == Integer.MAX_VALUE ? -1 : max;
	}

	/**
	 * setColumnName Set a specific column name to a new value.
	 * 
	 * @param row
	 *            Row number
	 * @param name
	 *            New name for the column
	 */
	public void setRowName(int column, String name) {
		data[0][column] = name;
	}

	/**
	 * setString set a specific String to a new value.
	 * 
	 * @param rowIndex
	 *            Row number
	 * @param columnIndex
	 *            Column number
	 * @param value
	 *            New column String
	 */
	public void setString(int rowIndex, int columnIndex, String value) {
		data[rowIndex][columnIndex] = value;
	}

	/**
	 * setString set a specific String to a new value.
	 * 
	 * @param columnName
	 *            Name of the row
	 * @param row
	 *            Column number
	 * @param value
	 *            New row String
	 */
	public void setString(String columnName, int row, String value) {
		int columnIndex = getColumnIndex(columnName);
		data[row][columnIndex] = value;
	}

	/**
	 * setInt set a specific integer to a new value.
	 * 
	 * @param rowIndex
	 *            Row number
	 * @param columnIndex
	 *            Column number
	 * @param value
	 *            New integer value
	 */
	public void setInt(int rowIndex, int columnIndex, int value) {
		data[rowIndex][columnIndex] = PApplet.str(value);
	}

	/**
	 * setInt set a specific integer to a new value.
	 * 
	 * @param columnName
	 *            Name of the row
	 * @param rowIndex
	 *            Column number
	 * @param value
	 *            New row String
	 */
	public void setInt(String columnName, int rowIndex, int value) {
		int columnIndex = getColumnIndex(columnName);
		data[rowIndex][columnIndex] = PApplet.str(value);
	}

	/**
	 * setFloat set a specific float to a new value.
	 * 
	 * @param rowIndex
	 *            Row number
	 * @param columnIndex
	 *            Column number
	 * @param value
	 *            New row float
	 */
	public void setFloat(int rowIndex, int columnIndex, float value) {
		data[rowIndex][columnIndex] = PApplet.str(value);
	}

	/**
	 * setFloat set a specific float to a new value.
	 * 
	 * @param columnName
	 *            Name of the column
	 * @param rowIndex
	 *            Row number
	 * @param value
	 *            New value
	 */
	public void setFloat(String columnName, int rowIndex, float value) {
		int columnIndex = getColumnIndex(columnName);
		data[rowIndex][columnIndex] = PApplet.str(value);
	}

	/*
	public Object get(int rowIndex, int columnIndex) {
		if (formats.containsKey(rowIndex)) {
			switch (formats.get(rowIndex)) {
			case INT:
				return getInt(rowIndex, columnIndex);
			case FLOAT:
				return getFloat(rowIndex, columnIndex);
			case STRING:
				return getString(rowIndex, columnIndex);
			//case BOOLEAN: return getBoolean(rowIndex, columnIndex);
			//break;
			}
		}
		// if there is no format mapping, return String
		return getString(rowIndex, columnIndex);
	}
	*/
	
	public void hasHeadline(boolean b){
		this.hasHeadline = b;
	}

	/**
	 * For auto-parsing, the format for each column can be set
	 * 
	 * @param column
	 * @param format
	 */
	/*
	public void setFormat(int column, ElementFormat format) {
		formats.put(column, format);
	}
	*/

	public boolean isComplete() {
		return isComplete;
	}

	public int firstIncompleteLine() {
		return firstIncompleteLine;
	}
}
