//package de.fhpotsdam.io.csv.test;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.concurrent.ArrayBlockingQueue;
//
//import org.junit.Test;
//
//import de.fhpotsdam.io.csv.CsvP5;
//import de.fhpotsdam.io.csv.analyzer.DataType;
//import de.fhpotsdam.io.csv.analyzer.StructureAnalyzer;
//
//import processing.core.PApplet;
//
//public class CsvP5Test extends PApplet{
//
//	@Test
//	public void testAll() {
//		CsvP5 csv = new CsvP5(this, "test1.csv");
//		csv.load();
//	}
//
//	@Test
//	public void testGetPossibleDataTypes(){
//		String s1 = "False";
//		StructureAnalyzer a = new StructureAnalyzer();
//		ArrayList<DataType> al = a.getPossibleDataTypes(s1);
//		for(DataType dt: al){
//			println(dt.toString());
//		}
//		
//	}
//}
