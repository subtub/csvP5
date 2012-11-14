/**
 * csvP5 SimpleExampleWithHeadline
 *
 * csvP5 http://www.github.com/wrongentertainment/csvP5
 */

import de.fhpotsdam.io.csv.*;

CsvP5 csv;


void setup() {
  size(700, 300);
  
  csv = new CsvP5(this, "someCsv.csv");
  csv.hasHeadline(true);
  csv.load();
  println("csv total columns = " + csv.getColumnCount());
  println("csv total rows = " + csv.getRowCount());
  
  PFont font = createFont("", 12);
  textFont(font);
}


void draw() {
  background(255);
  
  fill(0);
  for(int i=0; i<csv.getColumnCount(); i++) {
    int xPos = 40+(i*110);
    text(csv.getHeadlineName(i), xPos, 40);
    for(int j=0; j<csv.getRowCount(); j++) {
      text(csv.getString(j, i), xPos, 70+(j*15));
    }
  }
}

