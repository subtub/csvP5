/**
 * csvP5simpleWithHeadline
 *
 * csvP5 http://www.github.com/wrongentertainment/csvP5
 */

import de.fhpotsdam.io.csv.*;

CsvP5 csv;


void setup() {
  size(500, 500);
  
  csv = new CsvP5(this, "someCsv.csv");
  csv.hasColumnHeaders(true);
  csv.load();
  
  PFont font = createFont("", 12);
  textFont(font);
}


void draw() {
  background(255);
  
  for(int col=0; col<csv.getTotalColumns(); col++) {
    int xPos = 25+(col*80);
    fill(0);
    text(csv.getColumnHeader(col), xPos, 40);
    
    fill(100);
    for(int row=0; row<csv.getTotalRows(); row++) {
      text(csv.getString(row, col), xPos, 70+(row*15));
    }
  }
}

