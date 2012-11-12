/**
 * Simple csvP5 example
 */

import de.fhpotsdam.io.csv.*;

CsvP5 csv;


void setup() {
  size(500, 800);
  
  csv = new CsvP5(this);
  csv.loadFile("sample.csv");
  println("csv total columns = " + csv.getColumnCount());
  println("csv total rows = " + csv.getRowCount());
  
  PFont font = createFont("", 12);
  textFont(font);
}


void draw() {
  background(0);  
  for(int i=0; i<csv.getColumnCount(); i++) {
    for(int j=0; j<50; j++) {
      fill(255);
      text(csv.getString(j, i), 40+(i*45), 40+(j*15));
    }
  }
}

