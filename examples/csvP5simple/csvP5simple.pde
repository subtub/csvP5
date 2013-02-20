/**
 * csvP5simple
 *
 * csvP5 http://www.github.com/subtub/csvP5
 */

import de.fhpotsdam.io.csv.*;

CsvP5 csv;


void setup() {
  size(500, 500);
  
  csv = new CsvP5(this, "sample.csv");
  csv.load();
  println("csv total columns   =   " + csv.getTotalColumns());
  println("csv total rows      =   " + csv.getTotalRows());
  
  PFont font = createFont("", 12);
  textFont(font);
}


void draw() {
  background(255);
  
  for(int col=0; col<csv.getTotalColumns(); col++) {
    for(int row=0; row<csv.getTotalRows(); row++) {
      int xPos = 25+(col*46);
      int yPos = 25+(row*46);
      int w = 45;
      int h = 45;
      
      fill(230);
      noStroke();
      rect(xPos, yPos, w, h);
      
      fill(180, 200, 230);
      noStroke();
      int biggestValue = 100;
      float mappedValue = map(csv.getInt(row, col), 0,biggestValue, 0,w);
      rect(xPos, yPos+(h-mappedValue), w, mappedValue);
      
      fill(0);
      text(csv.getString(row, col), xPos+4, yPos+14);
    }
  }
}

