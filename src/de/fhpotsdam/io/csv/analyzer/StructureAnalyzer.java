package de.fhpotsdam.io.csv.analyzer;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * This class is intended to guess the orientation of a CSV file (horizontal or vertical 
 * and to guess the used file types of a row/column.  
 * This class is <b>not</b> intended to remove whitespace or quotation marks from strings.
 * Checks for float does only include '.', not ','.
 * @author Tim Pulver
 *
 */
public class StructureAnalyzer {
	// 1
	// old: 	//^[0-9]+\\.?[0-9]*$");
	public static final Pattern floatPattern = Pattern.compile("(^[0-9]+$)|(^[0-9]+\\.{1}[0-9]+$)"); 
	// 1 || || 0. || 1.1 NOT .1 <- fix later!?
	public static final Pattern intPattern = Pattern.compile("^[0-9]+$"); 
	// true || false || 0 || 1 || yes || no || True || False || Yes || No || FALSE || TRUE || YES || NO
	public static final Pattern booleanPattern = Pattern.compile("^([Tt]rue|TRUE|[Yy]es|YES|0|1|[Ff]alse||FALSE|[Nn]o|NO)?$"); 
	
	public StructureAnalyzer(){
		
	}
	
	public static ArrayList<DataType> getPossibleDataTypes(String s){
		ArrayList<DataType> possibleDataTypes = new ArrayList<DataType>();
		Matcher matcher = floatPattern.matcher(s);
		if(matcher.find()){
			possibleDataTypes.add(DataType.FLOAT);
		}
		matcher = intPattern.matcher(s);
		if(matcher.find()){
			possibleDataTypes.add(DataType.INT);
		}
		matcher = booleanPattern.matcher(s);
		if(matcher.find()){
			possibleDataTypes.add(DataType.BOOLEAN);
		}
		return possibleDataTypes;
	}
}
