package de.fhpotsdam.io.csv.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.text.StyledEditorKit.BoldAction;
import javax.xml.crypto.Data;

import org.xml.sax.HandlerBase;

import de.fhpotsdam.util.map.MapUtil;

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
	// guessing: how many elements should be checked to guess the type
	public final static int N_ELEMENTS_FOR_AUTO_GUESS = 4;
	
	public StructureAnalyzer(){
		
	}
	
	/**
	 * Checks if the String can be parsed to float, int or boolean.
	 * @param s string containing an int, float, boolean or just string
	 * @return an ArrayList of possible data types, there can be multiple because 
	 * e.g. 0 or 1 can either be boolean, float or int 
	 */
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
	
	public static DataType getRecommendedType(String[] s){
		if(s == null){
			throw new IllegalArgumentException("String s is null!");
		}
		HashMap<DataType, Integer> appearances = new HashMap<DataType, Integer>();
		ArrayList<DataType> dt;
		for(int i=0; i<N_ELEMENTS_FOR_AUTO_GUESS && i<s.length; i++){
			dt = getPossibleDataTypes(s[i]);
			addListToMap(dt, appearances);
		}
		return getMaxValue(appearances);
	}
	
	/**
	 * TODO: 
	 * - currently picks a data type and ignores string.... needs tuning
	 * - check if there is ANY string with a comma -> don't use int, but float instead 
	 * 
	 * Sorts a HashMap containing <DataType, Integer> pairs (e.g. "INT", 2) by value 
	 * and returns the DataType with biggest integer. 
	 * This is used to guess which data type is probably being used in a row / column.  
	 * @param map HashMap to look for biggest value
	 * @return DataType with biggest "occurance"
	 */
	public static DataType getMaxValue(Map<DataType, Integer> map){
		if(map == null){
			throw new NullPointerException("Map is null!");
		}
		else if(map.size() == 0){
			throw new IllegalArgumentException("Map size is 0!");
		}
		// below works but is not very smooth... :)
		/*
		// sort the map from smallest to biggest
		Map<DataType, Integer> sortedMap = MapUtil.sortByValue(map);
		for(Entry entry: sortedMap.entrySet()){
			System.out.println(entry.toString());
		}
		DataType ret = null;
		Iterator<DataType> it = sortedMap.keySet().iterator();
		while(it.hasNext()){
			ret = it.next(); 
		}
		return ret;
		*/
		int nInt = map.containsKey(DataType.INT) ? map.get(DataType.INT) : 0;
		int nFloat = map.containsKey(DataType.FLOAT) ? map.get(DataType.FLOAT) : 0;
		int nBool = map.containsKey(DataType.BOOLEAN) ? map.get(DataType.BOOLEAN) : 0;
		if(nBool >= nInt) return DataType.BOOLEAN;
		else if(nInt >= nFloat) return DataType.INT;
		else return DataType.FLOAT;
		
	}
	
	/**
	 * Adds all elements in list to map.  
	 * If an element already exists, the value in the map is incremented. 
	 * If the element does not exists, the value in the map is set to "1".
	 * @param list a List containing the data to add
	 * @param map a map, to add the list to
	 */
	private static <T> void addListToMap(List<T> list, Map<T, Integer> map){
		for(T entry: list){
			if(map.containsKey(entry)){ // check if map already contains this data type
				map.put(entry, map.get(entry)+1);	// increment appearance
			}
			else{
				map.put(entry, 1);
			}
		}
	} 
}
