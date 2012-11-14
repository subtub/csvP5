/**
 * csvP5simple
 *
 * csvP5 http://www.github.com/wrongentertainment/csvP5
 */

import de.fhpotsdam.io.csv.*;

CsvP5 csv;


void setup() {
  size(600, 600);
  
  csv = new CsvP5(this, "sample.csv");
  csv.load();
  println("csv total columns   =   " + csv.getColumnCount());
  println("csv total rows      =   " + csv.getRowCount());
  
  PFont font = createFont("", 12);
  textFont(font);
}


void draw() {
  background(255);
  
  for(int col=0; col<csv.getColumnCount(); col++) {
    for(int row=0; row<csv.getRowCount(); row++) {
      int xPos = 50+(col*101);
      int yPos = 50+(row*101);
      int width = 100;
      int height = 100;
      
      fill(230);
      noStroke();
      rect(xPos, yPos, width, height);
      
      fill(70, 140, 210);
      noStroke();
      float mappedValue = map(csv.getInt(row, col), 0,width, 0, 90);
      rect(xPos, yPos+(height-mappedValue), width, mappedValue);
      
      fill(255);
      text(csv.getString(row, col), xPos+5, yPos+90);
    }
  }
}

