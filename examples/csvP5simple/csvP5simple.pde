/**
 * csvP5 SimpleExample
 *
 * csvP5 http://www.github.com/wrongentertainment/csvP5
 */

import de.fhpotsdam.io.csv.*;

CsvP5 csv;


void setup() {
  size(600, 600);
  
  csv = new CsvP5(this, "simple.csv");
  csv.load();
  println("csv total columns   =   " + csv.getColumnCount());
  println("csv total rows      =   " + csv.getRowCount());
  
  PFont font = createFont("", 12);
  textFont(font);
}


void draw() {
  background(255);
  
  for(int i=0; i<csv.getColumnCount(); i++) {
    for(int j=0; j<csv.getRowCount(); j++) {
      int xPos = 50+(i*101);
      int yPos = 50+(j*101);
      int width = 100;
      int height = 100;
      
      fill(230);
      noStroke();
      rect(xPos, yPos, width, height);
      
      fill(70, 140, 210);
      noStroke();
      float mappedValue = map(csv.getInt(j, i), 0,width, 0, 90);
      rect(xPos, yPos+(height-mappedValue), width, mappedValue);
      
      fill(255);
      text(csv.getString(j, i), xPos+5, yPos+90);
    }
  }
}

