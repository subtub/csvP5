package de.fhpotsdam.io.csv.analyzer;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * This class is intended to guess the orientation of a CSV file (horizontal or vertical 
 * and to guess the used file types of a row/column.  
 * This class is <b>not</b> intended to remove whitespace or quotation marks from strings.
 * @author Tim Pulver
 *
 */
public class StructureAnalyzer {
	public static final Pattern floatPattern = Pattern.compile("^[0-9]+$"); // 1
	public static final Pattern intPattern = Pattern.compile("^[0-9]+\\.?[0-9]*$"); // 1 || 1.1 NOT .1 <- fix later!?
	public static final Pattern booleanPattern = Pattern.compile("^([Tt]rue|[Yy]es|0|1|[Ff]alse|[Nn]o)?$"); // true || false || 0 || 1
	
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
